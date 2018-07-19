
package modelo.logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class MiJTextField extends JTextField{
    
    MiJTextField(){
        this.requestFocus();
        this.setHorizontalAlignment(LEFT);
        this.addKeyListener(l);
    }
    
    KeyListener l = new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if(Character.isLetter(c)){
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            if(Character.isLetter(c)){
                e.consume();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            char c = e.getKeyChar();
            if(Character.isLetter(c)){
                e.consume();
            }
        }
        
    };
}
