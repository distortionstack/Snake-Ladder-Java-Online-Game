package com.distortionstack.snakeladder;

// ถ้าจะใช้ของใน domain (เช่น PlayerData)
import com.distortionstack.snakeladder.domain.GuiDisplay;

// ถ้าจะใช้ของใน include (เช่น ConstantConfig หรือ AssetManager)
import com.distortionstack.snakeladder.include.AssetManager;

public class Main {
    public static GuiDisplay guiDisplay;
    
    public static void main(String[] args) {
        AssetManager assetManager = new AssetManager(); 
        guiDisplay = new GuiDisplay(assetManager){{
            setVisible(true);
        }};
    }

    public static GuiDisplay getGuiDisplay() {
        return guiDisplay;
    }
}


