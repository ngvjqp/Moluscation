package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import javaPlayExtras.ObjetoComGravidade;
import javax.swing.JOptionPane;

public class Molusco extends ObjetoComGravidade {

    static int vidaHUD = 3;
    protected int vida = 3;
    protected int velocidade = 5;
    protected EstadoPersonagem estado;
    protected int virado;
    protected Sprite imgNormal;
    protected Imagem imgTras;
    protected Imagem imgFrente;
    protected Imagem imgPulo;
    protected Imagem imgAtual;
    protected Imagem imgMorre;
    protected int fase;
    protected int xCam;
    protected int timerAnim;

    public Molusco() {

        try {
            this.imgNormal = new Sprite("resources/panda.png", 3, 85, 154);
            this.imgMorre = new Imagem("resources/panda.png");
            this.imgFrente = new Imagem("resources/panda.png");
            this.imgPulo = new Imagem("resources/panda.png");




        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exceção: " + ex.getMessage());
            System.exit(0);
        }
        // this.imgAtual = imgNormal;

        //POSIÇÃO INICIAL
        this.x = 400;

        this.setAltura(154);
        this.setLargura(85);



    }

    public void step(long timeElapsed) {
        super.step(timeElapsed);




        this.xCam = this.x;
        Keyboard keyboard = GameEngine.getInstance().getKeyboard();
        if (keyboard.keyDown(Keys.ESPACO)) {
            if (this.fase >= 3) {
                this.superPulo();
            }
        }
        if (keyboard.keyDown(Keys.ESQUERDA)) {
            try {
                this.imgNormal = new Sprite("resources/pandaIn.png", 3, 85, 154);
            } catch (Exception ex) {
                Logger.getLogger(Molusco.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.timerAnim++;
        } else if (keyboard.keyDown(Keys.DIREITA)) {
            try {
                this.imgNormal = new Sprite("resources/panda.png", 3, 85, 154);
            } catch (Exception ex) {
                Logger.getLogger(Molusco.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.virado = 1;
            this.timerAnim++;
        } else if (keyboard.keyDown(Keys.CIMA)) {
            this.pulo();
            AudioPlayer.play("resources/sounds/pulo.wav");
        } else if (keyboard.keyDown(Keys.BAIXO)) {
            this.y += this.velocidade;
        }

        if (this.timerAnim < 10) {
            this.imgNormal.setCurrAnimFrame(1);
        }
        if (this.timerAnim < 20) {
            this.imgNormal.setCurrAnimFrame(2);
        } else {
            this.imgNormal.setCurrAnimFrame(3);
            this.timerAnim = 1;
        }

    }

    public void setX(int shiz) {
        this.x = shiz;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        // g.drawString(this.vida + "", this.x + 20, this.y - 10);


        //  if (this.virado == 1) {
        this.imgNormal.draw(g, this.x, this.y);
        //  } else {
        //     this.imgNormal.drawFlipped(g, this.x, this.y);
        //  }

    }

    /* public void alteraImagem(Imagem novaImagem) {
    this.imgAtual = novaImagem;
    this.setAltura(this.imgAtual.pegaAltura());
    this.setLargura(this.imgAtual.pegaLargura());
    }
     */
    public void imgMorre() {
        /*   this.imgAtual = this.imgMorre;
        this.setAltura(this.imgAtual.pegaAltura());
        this.setLargura(this.imgAtual.pegaLargura());*/
    }

    private void superPulo() {
        if (this.estaNoChao) {
            //  this.alteraImagem(this.imgPulo);

            this.impulso(50);
        }
    }

    private void pulo() {
        if (this.estaNoChao) {
            //   this.alteraImagem(this.imgPulo);
            this.impulso(30);
        }
    }

    public void frente() {
//        this.alteraImagem(this.imgFrente);
        this.x += this.velocidade;
    }

    public void traz() {
        this.x -= this.velocidade;
    }

    /* private void normal() {
    if (this.estaPulando()) {
    this.alteraImagem(this.imgPulo);
    } else {
    this.estado = EstadoPersonagem.NORMAL;
    //  this.alteraImagem( this.imgNormal );
    }
    }
     */
    public void perdeVida() {
        this.vida -= 1;
        this.vidaHUD--;
    }

    public void ganhaVida() {
        this.vida += 1;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, 85, 154);
    }

    public int vida() {
        return this.vida;
    }
}
