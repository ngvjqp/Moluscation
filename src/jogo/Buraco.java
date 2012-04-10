
package jogo;

import java.awt.Graphics;
import javaPlay.GameObject;


public class Buraco extends GameObject {
    
       
     protected boolean active;

     public Buraco(){
     
         this.active = true;
     }
 
    public void step(long timeElapsed) {
        
    }

   
    public void draw(Graphics g) {
        
    }
        private void setActive(boolean isActive) {
        this.active = isActive;
    }

    private boolean isActive() {
        return this.active;
    }
    
    
}
