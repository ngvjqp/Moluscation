
package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import javaPlayExtras.ObjetoComGravidade;
import javax.swing.JOptionPane;

public class Molusco extends ObjetoComGravidade{
    static int vidaHUD = 3;
    
    protected int vida = 3;
    protected int velocidade = 10;
    protected EstadoPersonagem estado;
    protected int virado;
    
    protected Imagem imgNormal;
    protected Imagem imgTras;
    protected Imagem imgFrente;
    protected Imagem imgPulo;
    protected Imagem imgAtual;
    protected Imagem imgMorre;
    
    
    public Molusco(){
    
        
      try {
            this.imgNormal = new Imagem("resources/moluscoAndando.gif");
            this.imgMorre = new Imagem("resources/moluscoMorre.gif");
            this.imgFrente = new Imagem("resources/moluscoAndando.gif");
            this.imgPulo = new Imagem("resources/moluscoPula.gif");
            
          
           
            
       } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exceção: "+ex.getMessage());
            System.exit(0);
    }
        this.imgAtual = imgFrente;
        
        //POSIÇÃO INICIAL
        this.x = 400;
        
        this.setAltura( this.imgAtual.pegaAltura() );
        this.setLargura(this.imgAtual.pegaLargura() );
    
        
    
    }
    
    public void step(long timeElapsed){
         super.step(timeElapsed);
         
         Keyboard keyboard = GameEngine.getInstance().getKeyboard();
         
            
       if(keyboard.keyDown(Keys.ESPACO)){
           this.superPulo();
       }
       
      if( keyboard.keyDown(Keys.ESQUERDA) ){
            this.virado = 0;
           this.traz();
        } else if( keyboard.keyDown(Keys.DIREITA) ){
            this.virado = 1;
          this.frente();
          
        } else if( keyboard.keyDown(Keys.CIMA) ){
          this.y -= this.velocidade;
        } else if( keyboard.keyDown(Keys.BAIXO) ){
          this.y += this.velocidade;
        } else {
         this.normal();
        }
         
    
    }

    
    public void draw(Graphics g) {
          g.setColor(Color.white);
       // g.drawString(this.vida + "", this.x + 20, this.y - 10);

      
         if (this.virado == 1){
        this.imgAtual.draw(g, this.x, this.y);
     }else{
        this.imgAtual.drawFlipped(g, this.x, this.y);
     }
        
    }
        
    
    
     public void alteraImagem(Imagem novaImagem){
        this.imgAtual = novaImagem;
        this.setAltura( this.imgAtual.pegaAltura() );
        this.setLargura( this.imgAtual.pegaLargura() );
    }
     
          public void imgMorre(){
        this.imgAtual = this.imgMorre;
        this.setAltura( this.imgAtual.pegaAltura() );
        this.setLargura( this.imgAtual.pegaLargura() );
    }

    private void superPulo() {
        if(this.estaNoChao){
            this.alteraImagem(this.imgPulo);
            this.impulso( 30 );
             }
    }
    
    private void pulo(){
        if(this.estaNoChao){
            this.alteraImagem(this.imgPulo);
            this.impulso(15);
        }
    }

    private void frente() {
          this.alteraImagem(this.imgFrente);
        this.x += this.velocidade;
    }

    

    private void traz() {
        this.x -= this.velocidade;
    }
    
     private void normal() {
         if (this.estaPulando()) {
            this.alteraImagem( this.imgPulo );
        } else {
            this.estado = EstadoPersonagem.NORMAL;
          //  this.alteraImagem( this.imgNormal );
        }
    }
    
    
        public void perdeVida(){
        this.vida -= 1;
        this.vidaHUD--;
    }
        
        public void ganhaVida(){
            this.vida += 1;
        }
    
    
public Rectangle getRectangle() {
       return new Rectangle(this.x, this.y, this.imgAtual.pegaLargura(), this.imgAtual.pegaAltura());
    }

public int vida(){
return this.vida;
}

}
