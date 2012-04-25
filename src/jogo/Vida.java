package jogo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import javaPlay.GameEngine;

import javaPlay.Keyboard;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import javaPlayExtras.ObjetoComGravidade;
import jogo.Molusco.*;
public class Vida extends GameObject {

    private Imagem img;
    private int valor;
    boolean active;

    public Vida(int x, int y) {
        this.active = true;
        this.x = x;
        this.y = y;
        this.valor = 3;
        try {
            this.img = new Imagem("resources/vida.gif");
        } catch (Exception ex) {
            Logger.getLogger(Vida.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.altura = this.img.pegaAltura();
        this.largura = this.img.pegaLargura();
    }

    public void step(long timeElapsed) {
        Keyboard keyboard = GameEngine.getInstance().getKeyboard();
        if (keyboard.keyDown(Keys.DIREITA)) {

            this.x = this.x - 20;
        }

        if (keyboard.keyDown(Keys.ESQUERDA)) {
            this.x = this.x + 20;
        }
    }

    public void draw(Graphics g) {
        this.img.drawFlipped(g, this.x, this.y);
    }
    
       public void setActive(boolean isActive) {
        this.active = isActive;
    }

   public boolean isActive() {
        return this.active;
    }
    
    }
       
    

