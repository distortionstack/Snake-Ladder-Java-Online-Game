package com.distortionstack.snakeladder.include;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

import com.distortionstack.snakeladder.include.asset.game.GameAsset;

public class AssetManager {
    static String slash = File.separator;
    static String UserDir = 
    private MenuAsset menuAsset;
    private GameAsset gameAsset;

    public AssetManager(){
        gameAsset = new GameAsset();
        menuAsset = new MenuAsset();
    }

    public static String getSlash() {
        return slash;
    }
    public static String getUserDir() {
        return UserDir;
    }

    public GameAsset getGameAsset() {
        return gameAsset;
    }
    public MenuAsset getMenuAsset() {
        return menuAsset;
    }

    public static ImageIcon cropImage(ImageIcon sourceIcon, int x, int y, int width, int height) {
        if (sourceIcon == null) return null;

        // 1. ดึง Image ออกมาจาก ImageIcon
        Image img = sourceIcon.getImage();

        // 2. สร้าง BufferedImage (กระดาษวาดรูปเปล่าๆ) ขนาดเท่ารูปเดิม
        BufferedImage bufferedImage = new BufferedImage(
            img.getWidth(null), 
            img.getHeight(null),
            BufferedImage.TYPE_INT_ARGB // ใช้ ARGB เพื่อรองรับพื้นหลังใส (Transparency)
        );

        // 3. วาดรูปเดิมลงไปใน BufferedImage
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose(); // วาดเสร็จแล้วปล่อยแปรง

        // 4. สั่งตัด (Crop) เฉพาะส่วนที่ต้องการ
        // ระวัง! อย่าใส่ x, y, width, height เกินขอบรูป ไม่งั้น error
        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);

        // 5. ห่อกลับเป็น ImageIcon แล้วส่งคืน
        return new ImageIcon(croppedImage);
    }
    public ImageIcon scaleImage(ImageIcon sourceIcon, int width, int height) {
        try {
            Image sourceImage = sourceIcon.getImage();
            Image scaledImage = sourceImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon scaledGifIcon = new ImageIcon(scaledImage);
            return scaledGifIcon;
        } catch (Exception e) {
            System.out.println("explo pic eror");
            return null;
            // TODO: handle exception
        }
    }
}

class MenuAsset{

}
