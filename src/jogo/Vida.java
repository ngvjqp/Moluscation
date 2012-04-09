package jogo;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import javaPlay.GameEngine;

import javaPlayExtras.Imagem;
import javaPlayExtras.ObjetoComGravidade;

public class Vida extends ObjetoComGravidade {

    private Imagem img;
    private int valor;

    public Vida(int x, int y) {
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
        super.step(timeElapsed);
    }

    public void draw(Graphics g) {
        this.img.drawFlipped(g, this.x, this.y);
    }
}
