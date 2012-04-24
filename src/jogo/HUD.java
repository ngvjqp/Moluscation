/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import javaPlayExtras.Imagem;

/*
 *
 * @author nelito_vieira-junior
 */
public class HUD extends GameObject {

    public Imagem vidas;
    boolean active;
    private int valor;
    private Imagem img;
    public int vidaCont;

    public HUD() {
        this.active = true;
        this.x = 50;
        this.y = 50;
        this.valor = 3;
        this.vidaCont = 3;

        try {
            this.img = new Imagem("resources/vidas.png");
        } catch (Exception ex) {
            Logger.getLogger(Vida.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.altura = this.img.pegaAltura();
        this.largura = this.img.pegaLargura();
    }

    public void step(long timeElapsed) {
        this.vidaCont = Molusco.vidaHUD;
    }

    public void draw(Graphics g) {
        int contL = this.vidaCont;
        while (contL > 0) {
            int lado = 50 * contL;
            this.img.draw(g, (this.x + lado), this.y);
            contL--;
        }


    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public boolean isActive() {
        return this.active;
    }
}
