
package jogo;

import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlayExtras.Keys;
import javaPlayExtras.ObjetoComGravidade;

public class Molusco extends ObjetoComGravidade{
    
    protected int vida = 3;
    protected int velocidade = 10;
    protected EstadoPersonagem estado;
    
    public Molusco(){
    
        
     // try {
           // this.imgNormal = new Imagem("resources/gohan/normal.png");
           // this.imgTras = new Imagem("resources/gohan/traz.png");
           // this.imgFrente = new Imagem("resources/gohan/frente.png");
           // this.imgPulo = new Imagem("resources/gohan/normal.png");
          
           
            
      // } catch (Exception ex) {
          //  JOptionPane.showMessageDialog(null, "Exceção: "+ex.getMessage());
           // System.exit(0);
        //}
        
        //this.imgAtual = imgFrente;
        
        //POSIÇÃO INICIAL
        this.x = 50;
        
         //this.setAltura( this.imgAtual.pegaAltura() );
        //this.setLargura(this.imgAtual.pegaLargura() );
    
        
    
    }
    
    public void step(long timeElapsed){
         super.step(timeElapsed);
         
         Keyboard keyboard = GameEngine.getInstance().getKeyboard();
         
            
       if(keyboard.keyDown(Keys.ESPACO)){
           this.superPulo();
       }
       
       if(keyboard.keyDown(Keys.DIREITA)){
           this.frente();
       }
       
          else if( keyboard.keyDown(Keys.ESQUERDA) ){
          this.traz();
        } else if( keyboard.keyDown(Keys.CIMA) ){
          this.y -= this.velocidade;
        } else if( keyboard.keyDown(Keys.BAIXO) ){
          this.y += this.velocidade;
        } else {
       //  this.normal();
        }
         
    
    }

    
    public void draw(Graphics g) {
        
    }
    
     //public void alteraImagem(Imagem novaImagem){
        //this.imgAtual = novaImagem;
        //this.setAltura( this.imgAtual.pegaAltura() );
       // this.setLargura( this.imgAtual.pegaLargura() );
   // }

    private void superPulo() {
        if(this.estaNoChao){
            //this.alteraImagem(this.imgPulo);
            this.impulso( 30 );
             }
    }

    private void frente() {
          // this.alteraImagem(this.imgFrente);
        this.x += this.velocidade;
    }

    

    private void traz() {
          //this.alteraImagem(this.imgTras);
        this.x -= this.velocidade;
    }
    
    // private void normal() {
         // if(this.estaSubindo() || this.estaDescendo()){
           // this.alteraImagem(this.imgPulo);
        //    this.alteraImagem( this.imgNormal );
       // }
  //  }
    
    
        public void perdeVida(){
        this.vida = 1;
    }
    
     public boolean estaMorto(){
        return (this.vida <= 0);
    }
    
//public Rectangle getRectangle() {
       // return new Rectangle(this.x, this.y, this.imgAtual.pegaLargura(), this.imgAtual.pegaAltura());
    //}
    
}
