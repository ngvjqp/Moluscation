
package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;


public class Fase1 implements GameStateController {
    
    
    private Molusco molusco;
    
    private ArrayList<Bomba> bombas;
    
    private Buraco buraco;
    
    private CenarioComColisao cenario;
    
    private long contadorTempo;

  
    public void load() {
   
     this.molusco = new Molusco();
     this.bombas = new ArrayList<Bomba>();
     this.buraco = new Buraco();

     try {
        this.cenario = new CenarioComColisao("resources/cenario2.scn");
        this.cenario.adicionaObjeto(molusco); //Aqui, o controle de colisão ´é transferido para o cenario
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
    }

   
    public void unload() {
        
    }

    
    public void start() {
        
    }

   
    public void step(long timeElapsed) {
       if(this.molusco.estaMorto()){
         JOptionPane.showMessageDialog(null, "Game Over.");
         System.exit(0);
     }

     this.molusco.step(timeElapsed);
     for(Bomba bombas : this.bombas){
         if(this.molusco.temColisao( bombas)){
             this.molusco.perdeVida();
             this.molusco.setX(50);
         }         
         bombas.step(timeElapsed);   
         
         this.buraco.step(timeElapsed);
     }   
     
     if(this.molusco.temColisao( buraco)){
         //AQUI TEM QUE BOTAR PRA TROCAR A IMAGEM PRA MORTO 
         this.molusco.estaMorto();
        
     }
     //CASO encontre a TILE com o antídoto
      if(this.cenario.temColisaoComTile(molusco, 4)){
         JOptionPane.showMessageDialog(null, "Parabéns, você venceu.");
         System.exit(0);
     }
      
      
     this.cenario.step(timeElapsed);

     contadorTempo += timeElapsed;
     if(contadorTempo > 3000){ //tres segundos
         Bomba novo = new Bomba();
         this.bombas.add( novo );
         
         contadorTempo -= 3000;
     }
    }

    public void draw(Graphics g) {
         g.setColor(Color.CYAN);
      g.fillRect(0, 0, 800, 600);

      
      this.cenario.draw(g);
      this.molusco.draw(g);
      for(Bomba bombas : this.bombas){
          bombas.draw(g);
      }
      
      this.buraco.draw(g);
    }

   
    public void stop() {
        
    }
}
