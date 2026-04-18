package com.distortionstack.snakeladder.domain.online;
import com.distortionstack.snakeladder.domain.GameLogicalManager;

public class OnlineModeManagement {
    
}

class GameServer extends GameLogicalManager{
    GameServer(){

    }
}

class GameClient {
    OnlineModeManagement online;
    GameClient(OnlineModeManagement online) {
        this.online = online;
    }
}
