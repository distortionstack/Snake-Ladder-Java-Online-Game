package com.distortionstack.snakeladder.include;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.distortionstack.snakeladder.include.asset.game.GameAsset;
import com.distortionstack.snakeladder.include.asset.menu.MenuAsset;

public class AssetManager {

    private static final String BASE_DIR = System.getProperty("user.dir");
    private static final String SEP = File.separator;
    private static final String IMAGE_DIR = BASE_DIR + SEP + "assets" + SEP + "images" + SEP;
    private static final String MANIFEST_DIR = BASE_DIR + SEP + "assets" + SEP + "manifests" + SEP;

    private final GameAsset gameAsset = new GameAsset();
    private final MenuAsset menuAsset = new MenuAsset();

    public void loadGameAsset() {
        loadManifest("game.xml", gameAsset::put);
    }

    public void loadMenuAsset() {
        loadManifest("menu.xml", menuAsset::put);
    }

    // ──────────────────────────────────────────────
    // โหลด XML แล้ว put ลง target ที่ส่งมา
    // ──────────────────────────────────────────────

    private void loadManifest(String xmlFileName, AssetReceiver target) {
        try {
            File xmlFile = new File(MANIFEST_DIR + xmlFileName);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            String namespace = root.getAttribute("namespace");

            parseElement(root, namespace, target);

            System.out.println("[AssetManager] " + xmlFileName + " done.");
        } catch (Exception e) {
            System.err.println("[AssetManager] Failed: " + xmlFileName + " → " + e.getMessage());
        }
    }

    // ──────────────────────────────────────────────
    // Recursive parser — เหมือนเดิม
    // ──────────────────────────────────────────────

    private void parseElement(Element element, String currentPath, AssetReceiver target) {
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;

            Element child = (Element) node;
            String tag = child.getTagName();
            String key = child.getAttribute("key");
            String newPath = currentPath.isEmpty() ? key : currentPath + "." + key;

            switch (tag) {
                case "group":
                    parseElement(child, newPath, target);
                    break;

                case "image":
                    target.put(newPath, loadImage(child.getAttribute("file")));
                    System.out.println("  Loaded : " + newPath);
                    break;

                case "crop":
                    ImageIcon full = loadImage(child.getAttribute("file"));
                    int x = Integer.parseInt(child.getAttribute("x"));
                    int y = Integer.parseInt(child.getAttribute("y"));
                    int w = Integer.parseInt(child.getAttribute("width"));
                    int h = Integer.parseInt(child.getAttribute("height"));
                    target.put(newPath, cropImage(full, x, y, w, h));
                    System.out.println("  Cropped: " + newPath);
                    break;
            }
        }
    }

    // ──────────────────────────────────────────────
    // Helpers
    // ──────────────────────────────────────────────

    private ImageIcon loadImage(String fileName) {
        File file = new File(IMAGE_DIR + fileName);
        if (!file.exists()) {
            System.err.println("  Missing: " + IMAGE_DIR + fileName);
            return null;
        }
        return new ImageIcon(file.getAbsolutePath());
    }

    public static ImageIcon cropImage(ImageIcon src, int x, int y, int w, int h) {
        if (src == null)
            return null;

        Image img = src.getImage();
        // สร้าง BufferedImage ตามขนาดจริงของ ImageIcon
        BufferedImage buf = new BufferedImage(
                src.getIconWidth(),
                src.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics g = buf.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        // ป้องกันพิกัดเกินขนาดภาพ (Out of bounds)
        int finalW = Math.min(w, buf.getWidth() - x);
        int finalH = Math.min(h, buf.getHeight() - y);

        return new ImageIcon(buf.getSubimage(x, y, finalW, finalH));
    }

    // ──────────────────────────────────────────────
    // Getters
    // ──────────────────────────────────────────────

    public GameAsset getGameAsset() {
        return gameAsset;
    }

    public MenuAsset getMenuAsset() {
        return menuAsset;
    }

    // ──────────────────────────────────────────────
    // Functional interface สำหรับส่ง target เข้า parser
    // ──────────────────────────────────────────────

    @FunctionalInterface
    private interface AssetReceiver {
        void put(String key, ImageIcon icon);
    }
}