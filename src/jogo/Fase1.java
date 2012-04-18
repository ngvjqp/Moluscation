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

public class Fase1 implements GameStateController {

    private Molusco molusco;
    private ArrayList<Bomba> bombas;
    private CenarioComColisao cenario;
    private long contadorTempo;
    private int bombax;
    private int bombay;

    public Fase1() {
        this.molusco = new Molusco();
    }

    public void load() {

        this.bombax = 20;
        this.bombay = 200;
        this.bombas = new ArrayList<Bomba>();


        try {
            this.cenario = new CenarioComColisao("resources/cenario1.scn");
            this.cenario.adicionaObjeto(molusco); //Aqui, o controle de colisão ´é transferido para o cenario

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
        this.molusco.setY(200);
        this.molusco.setX(100);

    }

    public void step(long timeElapsed) {
        if (this.molusco.vida <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over.");
            System.exit(0);
        }

        this.molusco.step(timeElapsed);

        for (Bomba bombas : this.bombas) {
            if (this.molusco.temColisao(bombas)) {
                this.molusco.perdeVida();
                this.molusco.setX(400);
            }
            bombas.step(timeElapsed);


        }


        //CASO encontre a TILE com o antídoto
        if (this.cenario.temColisaoComTile(molusco, 5)) {
            //     AQUI TEM QUE BOTAR PRA TROCAR A IMAGEM PRA MORTO 
            JOptionPane.showMessageDialog(null, "Parabéns, você venceu.");
            System.exit(0);
        }

        if (this.cenario.temColisaoComTile(molusco, 4)) {
            this.molusco.vida += 1;
        }

        if (this.cenario.temColisaoComTile(molusco, 3)) {
            this.molusco.vida = 0;
        }

        this.cenario.step(timeElapsed);

        contadorTempo += timeElapsed;
        if (contadorTempo > 3000) { //tres segundos
            Bomba novo = null;
            try {
                novo = new Bomba(bombax, bombay);
            } catch (Exception ex) {
                Logger.getLogger(Fase1.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.bombas.add(novo);

            contadorTempo -= 3000;
        }
        Keyboard keyboard = GameEngine.getInstance().getKeyboard();
        if (keyboard.keyDown(Keys.DIREITA)) {
            this.cenario.moveCenarioTras(20);
            this.bombax -= 20;
        }

        if (keyboard.keyDown(Keys.ESQUERDA)) {
            this.cenario.moveCenarioTras(-20);
            this.bombax += 20;
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


    }

    public void stop() {
    }
}
