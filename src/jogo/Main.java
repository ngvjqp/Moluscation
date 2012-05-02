package jogo;

import javaPlay.GameEngine;

public class Main {

    public static void main(String[] args) {
        GameEngine.getInstance().addGameStateController(1, new Fase1());
        GameEngine.getInstance().addGameStateController(2, new Fase2());
        GameEngine.getInstance().addGameStateController(3, new Fase3());
        GameEngine.getInstance().addGameStateController(4, new Fase4());
        GameEngine.getInstance().addGameStateController(5, new Fase5());
        GameEngine.getInstance().addGameStateController(6, new Inicio());
        
        GameEngine.getInstance().setStartingGameStateController(6);

        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
