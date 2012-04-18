package jogo;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlayExtras.Imagem;
import javaPlay.Keyboard;
import javaPlayExtras.Keys;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import javaPlayExtras.ObjetoComGravidade;

public class Bomba extends ObjetoComGravidade {

    private int velocidade;
    protected Imagem imgNormal;
    protected Imagem imgAtual;



    Bomba(int x, int y) throws Exception {
                this.x = x;
        this.y = y;
        this.largura = 32;
        this.altura = 32;
        this.velocidade = 5;

            this.imgNormal = new Imagem("resources/NITRO.png");

        this.imgAtual = this.imgNormal;
        this.altura = this.imgAtual.pegaAltura();
        this.largura = this.imgAtual.pegaLargura();
    }

    public void step(long timeElapsed) {
        super.step(timeElapsed);
        this.x -= this.velocidade;
                Keyboard keyboard = GameEngine.getInstance().getKeyboard();
        
        if( keyboard.keyDown(Keys.DIREITA) ){
        this.x -= 10;}
        
         if( keyboard.keyDown(Keys.ESQUERDA) ){
        this.x += 5;}
    }

    public void draw(Graphics g) {
        this.imgAtual.drawFlipped(g, this.x, this.y);
    }
}
