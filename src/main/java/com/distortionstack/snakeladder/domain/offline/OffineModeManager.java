package com.distortionstack.snakeladder.domain.offline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.distortionstack.snakeladder.Main;
import com.distortionstack.snakeladder.domain.GameLogicalManeger;
import com.distortionstack.snakeladder.domain.PlayerData;
import com.distortionstack.snakeladder.include.AssetManager;
import com.distortionstack.snakeladder.include.config.GameUI;
import com.distortionstack.snakeladder.ui.LobbyPanel;
import com.distortionstack.snakeladder.ui.offline.OfflineGame;
import com.distortionstack.snakeladder.ui.offline.OfflineLobby;
import com.distortionstack.snakeladder.ui.GamePanel;

import java.util.List;

public class OffineModeManager {
    OfflineGameLogicalManeger gamelogical;
    GameOfflineComponetManager componentManage;

    public OffineModeManager(AssetManager assetManager) {
        gamelogical = new OfflineGameLogicalManeger();
        componentManage = new GameOfflineComponetManager(this, assetManager);
    }

    // getter
    public GameOfflineComponetManager getComponentManager() {
        return componentManage;
    }

    public OfflineGameLogicalManeger getGamelogical() {
        return gamelogical;
    }
}

class GameOfflineComponetManager {
    OffineModeManager offline;
    OfflineLobby offlineLobby;
    OfflineGame offlineGame;

    GameOfflineComponetManager(OffineModeManager offline, AssetManager assetManager) {
        this.offline = offline;

        offlineLobby = new OfflineLobby(assetManager);
        LobbyListenerSetUp();

        offlineGame = new OfflineGame(assetManager, offline.getLogicalProcess());
        GameListenerSetUp();
    }

    public void GameListenerSetUp() {
        offlineGame.addDiceButtonListener(e -> {
            offline.getLogicalProcess().diceRoll();
            offline.getLogicalProcess().updateIndex();

            System.out.println(
                    "Target Index is: " + offline.getLogicalProcess().getCurrentPlayer().getgStatus().getIndex());

            new OfflineAnimationThread(
                    offline.getLogicalProcess().getCurrentPlayer(),
                    offlineGame,
                    offline.getLogicalProcess());
        });
    }

    public void LobbyListenerSetUp() {
        for (JButton button : offlineLobby.getSelectSkinButton()) {
            button.addActionListener(e -> {
                button.setEnabled(false);
                offline.getLogicalProcess().addPlayer(button);
                System.out.println(offline.getLogicalProcess().playerList.getLast().Skincode);
            });
        }
        offlineLobby.getStartButton().addActionListener(e -> {
            Main.getGuiDisplay().remove(offlineLobby);
            Main.getGuiDisplay().add(offlineGame);
            Main.getGuiDisplay().repaint();
            Main.getGuiDisplay().revalidate();
        });
    }

    public OfflineGame getOfflineGame() {
        return offlineGame;
    }

    public OfflineLobby getOfflineLobby() {
        return offlineLobby;
    }

}


