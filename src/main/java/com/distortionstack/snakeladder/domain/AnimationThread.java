package com.distortionstack.snakeladder.domain;
import com.distortionstack.snakeladder.ui.GamePanel;

public abstract class AnimationThread extends Thread{
    protected PlayerData playerData;
    protected GamePanel gamePanel;
    protected GameLogicalManeger gamelogical;
    protected boolean running = true;
    protected long sleepDuration = 200;
    protected int currentVisual;
    protected int targetIndex;
    public AnimationThread(PlayerData playerData , GamePanel gamePanel){
        this.playerData = playerData;
        this.gamePanel = gamePanel;
    }
}

