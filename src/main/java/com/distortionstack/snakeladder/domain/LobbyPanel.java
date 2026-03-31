package com.distortionstack.snakeladder.domain;

import com.distortionstack.snakeladder.include.AssetManager;
import com.distortionstack.snakeladder.include.config.GameUI;
import com.distortionstack.snakeladder.include.config.GameLogical;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class LobbyPanel extends JPanel {
    JButton [] selectSkinButton = new JButton[GameLogical.SKINCODE_ARRAY_LENGTH];
    JPanel selectSkinPanel;
    JLabel LobbyLabel;
    JPanel operationPanel;
    AssetManager assetManager;
    LobbyPanel(AssetManager assetManager){
        this.assetManager = assetManager;
        
        for (int i = 0; i < selectSkinButton.length; i++) {
            selectSkinButton[i] = new JButton();
            selectSkinButton[i].setText(GameLogical.SKINCODE_ARRAY[i]);
        }

        //set up
        setForeground(Color.WHITE);

        selectSkinPanel = new JPanel(){{
            setOpaque(false);
            setLayout(new GridLayout(2, 3,40,40));
            for (int i = 0; i < selectSkinButton.length; i++) {
                add(selectSkinButton[i]);
            }
        }};

        LobbyLabel = new JLabel(){{
            setFont(new Font("Tohama",Font.BOLD,50));
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
            setText("Select Charector Room");
            setBackground(Color.RED);
            setForeground(Color.WHITE);
            
        }};
    }
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(assetManager.getGameAsset().getMenubackground().getImage(), 0, 0, this);
    }
    public JButton[] getSelectSkinButton() {
        return selectSkinButton;
    }
}

