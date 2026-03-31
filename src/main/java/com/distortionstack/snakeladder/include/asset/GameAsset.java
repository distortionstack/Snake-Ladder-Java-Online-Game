package com.distortionstack.snakeladder.include.asset;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.distortionstack.snakeladder.include.AssetManager;
import com.distortionstack.snakeladder.include.config.GameLogical;


public class GameAsset {
    //
    private static String slash = AssetManager.getSlash();
    private static String UserDir = AssetManager.getUserDir();
    //String Path
    int skinamount;
    //Asset File Object
    private ImageIcon arrow_up;
    private ImageIcon arrow_down;
    private ImageIcon [] playerSkin;
    private ImageIcon Gamebackground;
    private ImageIcon Menubackground;
    private ImageIcon BasediceButtonIcon;
    private ImageIcon diceButtonUnBlock;
    private ImageIcon diceButtonBlcoked;
    private ImageIcon ufoDown;
    private ImageIcon ufoUp;
    public GameAsset(){
        skinamount = GameLogical.SKINCODE_ARRAY.length;
        playerSkin = new ImageIcon[skinamount];
        
        Menubackground = ImageIconLoader("bg_image.png");
        Gamebackground = ImageIconLoader("newmap.png");

        arrow_up = ImageIconLoader( "up.gif");
        arrow_down = ImageIconLoader( "down.gif");

        ufoUp = ImageIconLoader("UFO_UP(resize).gif");
        ufoDown = ImageIconLoader("UFO_DOWN(resize).gif");

        if(arrow_down != null){
            System.out.println("por tai" + arrow_down.getIconWidth());
        }

        BasediceButtonIcon = ImageIconLoader("but_dice.png");

        for (int i = 0; i < skinamount; i++) {
            playerSkin[i] = ImageIconLoader("player_" + GameLogical.SKINCODE_ARRAY[i] + ".png"); 
        }


        diceButtonUnBlock = AssetManager.cropImage(BasediceButtonIcon, 0, 0, 84, 87);
        diceButtonBlcoked = AssetManager.cropImage(BasediceButtonIcon, 84, 0, 84, 87);
    }
    private ImageIcon ImageIconLoader(String FileName){
        try {
            String pathLoad = UserDir + slash +"assets" + slash + FileName;
            System.out.println(pathLoad);
            File file = new File(pathLoad);
            if (!file.exists()) {
                System.out.println("File not found: " + pathLoad);
                return null;
            }
            ImageIcon icon = new ImageIcon(pathLoad);     
            return icon;
        } catch (Exception e) {
            System.out.println( FileName + " : " + e.getMessage());
            return null;
        }
    }
    public ImageIcon getArrow_down() {
        return arrow_down;
    }
    public ImageIcon getArrow_up() {
        return arrow_up;
    }
    public ImageIcon getPlayerSkin(String skin) {
        if(Arrays.asList( GameLogical.SKINCODE_ARRAY).indexOf(skin) == -1){
            System.out.println("Can't Find The Skin");
        }
        return playerSkin[Arrays.asList( GameLogical.SKINCODE_ARRAY).indexOf(skin)];
    }
    public ImageIcon getGameBackGround() {
        return Gamebackground;
    }
    public ImageIcon getMenubackground() {
        return Menubackground;
    }
    public ImageIcon getDiceButtonBlcoked() {
        return diceButtonBlcoked;
    }
    public ImageIcon getDiceButtonUnBlock() {
        return diceButtonUnBlock;
    }
    
}
