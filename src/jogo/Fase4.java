package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javaPlayExtras.Keys;
import javaPlay.Keyboard;
import javax.swing.JOptionPane;

public class Fase4 implements GameStateController {

    private Molusco molusco;
    private ArrayList<Bomba> bombas;
    private CenarioComColisao cenario;
    private long contadorTempo;
    private Vida vida;
    private HUD hud;
    private int contGO;
    int controlePerdeVida;
    boolean first;

    public Fase4() {
        controlePerdeVida = 1;
        this.molusco = new Molusco();
        this.first = false;
                this.molusco.fase = 3;
    }

    public void load() {
        this.contGO = 1;


        try {
            //this.bombax = 20;
            //this.bombay = 200;
            this.bombas = new ArrayList<Bomba>();            
        } catch (Exception ex) {
            Logger.getLogger(Fase1.class.getName()).log(Level.SEVERE, null, ex);
        }



        this.vida = new Vida(1800, 400);
        this.hud = new HUD();
        try {
            this.cenario = new CenarioComColisao("resources/cenario4.scn");
            this.cenario.adicionaObjeto(molusco); //Aqui, o controle de colisão ´é transferido para o cenario

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        Bomba novo = null;
        try {
            novo = new Bomba(800, 400);
        } catch (Exception ex) {
            Logger.getLogger(Fase1.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bombas.add(novo);
        this.cenario.adicionaObjeto(novo);

        try {
            novo = new Bomba(1000, 400);
        } catch (Exception ex) {
            Logger.getLogger(Fase1.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bombas.add(novo);
        this.cenario.adicionaObjeto(novo);

    }

    public void unload() {
    }

    public void step(long timeElapsed) {
        if (this.molusco.vida <= 0) {
            this.molusco.imgMorre();
            int controle = 1;
            controle = controle - 30;
            this.molusco.setY(this.molusco.getY() + controle);
            JOptionPane.showMessageDialog(null, "Game Over.");
            System.exit(0);

        }

        this.molusco.step(timeElapsed);

        for (Bomba nitros : this.bombas) {
            if (this.molusco.temColisao(nitros)) {

                this.molusco.imgMorre();

                this.controlePerdeVida = this.controlePerdeVida - 5;
                this.molusco.setY(this.molusco.getY() + controlePerdeVida);

                this.contGO++;
                if (contGO >= 17) {
                    this.contGO = 1;
                    this.molusco.perdeVida();
                    this.molusco.setX(100);
                    this.molusco.setY(100);
                    this.cenario.setInicio();
                    this.molusco.alteraImagem(this.molusco.imgNormal);
                    this.first = true;
                }
                if (this.first == true) {
                    this.hud.vidaCont -= 1;
                    this.first = false;
                    this.controlePerdeVida = 1;
                }
            }
            nitros.step(timeElapsed);
        }


        //CASO encontre a TILE com o antídoto
        if (this.cenario.temColisaoComTile(molusco, 4)) {
            GameEngine.getInstance().setStartingGameStateController(5);
        }


        if (this.cenario.temColisaoComTile(molusco, 3)) {
            this.molusco.imgMorre();



            this.controlePerdeVida = this.controlePerdeVida - 5;
            this.molusco.setY(this.molusco.getY() + controlePerdeVida);

            this.contGO++;
            if (contGO >= 17) {
                this.contGO = 1;
                this.molusco.perdeVida();
                this.molusco.setX(100);
                this.molusco.setY(100);
                this.cenario.setInicio();
                this.molusco.alteraImagem(this.molusco.imgNormal);
                this.first = true;
            }
            if (this.first == true) {
                this.hud.vidaCont -= 1;
                this.first = false;
                this.controlePerdeVida = 1;
            }
        }


        if (this.molusco.temColisao(vida)) {
            this.molusco.ganhaVida();
            this.vida.setY(500000000);
            this.hud.vidaCont++;


        }
        this.cenario.step(timeElapsed);
        this.vida.step(timeElapsed);

        contadorTempo += timeElapsed;
        if (contadorTempo > 3000000) { //tres segundos
            Bomba novo = null;
            try {
                novo = new Bomba(1000, 400);
            } catch (Exception ex) {
                Logger.getLogger(Fase1.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.bombas.add(novo);
            this.cenario.adicionaObjeto(novo);
            contadorTempo -= 3000;

        }

        Keyboard keyboard = GameEngine.getInstance().getKeyboard();
        if (keyboard.keyDown(Keys.DIREITA)) {
            this.cenario.moveCenarioTras(20);
            //this.bombax -= 20;
            //this.bombas.setX(-20);
            for (Bomba nitros : bombas) {
                nitros.setX(nitros.getX() - 20);

            }


        }

        if (keyboard.keyDown(Keys.ESQUERDA)) {
            this.cenario.moveCenarioTras(-20);
            //this.bombax += 20;
            //this.bombas.setX(+20);

            for (Bomba nitros : bombas) {

                nitros.setX(nitros.getX() + 20);

            }
        }




    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);


        this.cenario.draw(g);
        this.molusco.draw(g);
        for (Bomba bombas : this.bombas) {
            bombas.draw(g);
        }
        this.vida.draw(g);
        this.hud.draw(g);
    }

    public void stop() {
    }

    public void start() {

    }
}
