package com.distortionstack.snakeladder.domain;
public class OnlineModeManagement {
    
}

class GameServer extends GameLogicalProsessor{
    GameServer(){

    }
}

class GameClient {
    OnlineModeManagement online;
    GameClient(OnlineModeManagement online) {
        this.online = online;
    }
}
