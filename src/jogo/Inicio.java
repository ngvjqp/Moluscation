package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.CenarioComColisao;
import javaPlayExtras.Keys;

public class Inicio implements GameStateController {
        private CenarioComColisao cenario;

    public Inicio() {
        // AudioPlayer.play("resources/sounds/herp.wav");
        }

    public void load() {
        try {
            this.cenario = new CenarioComColisao("resources/inicio.scn");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void step(long timeElapsed) {
        Keyboard keyboard = GameEngine.getInstance().getKeyboard();

        if (keyboard.keyDown(Keys.ENTER)) {
        GameEngine.getInstance().setStartingGameStateController(1);    
        }
    }
    
    public void draw(Graphics g) {
this.cenario.draw(g);
    }

    public void stop() {
    }

    public void start() {
            }

    @Override
    public void unload() {
     
    }
}