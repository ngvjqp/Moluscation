package jogo;

import javaPlay.GameEngine;

public class Main {

    public static void main(String[] args) {
        GameEngine.getInstance().addGameStateController(1, new Fase1());
        GameEngine.getInstance().addGameStateController(2, new Fase2());

        GameEngine.getInstance().setStartingGameStateController(1);

        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
