package com.distortionstack.snakeladder.ui;
import javax.swing.JFrame;

import com.distortionstack.snakeladder.domain.offline.OffineModeManager;
import com.distortionstack.snakeladder.include.AssetManager;
import com.distortionstack.snakeladder.include.config.DisplayUI;

public class GuiDisplay extends JFrame {
    MenuPanel menuPanel;
    public GuiDisplay(AssetManager assetManager){
        setSize(DisplayUI.WINDOW_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        
        menuPanel = new MenuPanel(assetManager);
        add(menuPanel);

        menuPanel.addMenuActionListener("Offline Mode", e -> {
            remove(menuPanel);
            add(new OffineModeManager(assetManager).getComponentManager().getOfflineLobby());
            repaint();
            revalidate();
        });

        menuPanel.addMenuActionListener("JOIN",e -> {
            
        });
        menuPanel.addMenuActionListener("HOST",e -> {
            
        });
        menuPanel.addMenuActionListener("Setting",e -> {
            
        });
        menuPanel.addMenuActionListener("Quit",e -> {
            
        });
    }
}

