package PongGame.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import PongGame.interfaces.IInputHandler;

public class SwingKeyInput extends KeyAdapter implements IInputHandler {
    
    private boolean up1 = false;
    private boolean down1 = false;
    private boolean up2 = false;
    private boolean down2 = false;
    private boolean restartPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_R) {
            restartPressed = true;
        }
        
        if(key == KeyEvent.VK_UP) { up2 = true; }
        if(key == KeyEvent.VK_DOWN) { down2 = true; }
        if(key == KeyEvent.VK_W) { up1 = true; }
        if(key == KeyEvent.VK_S) { down1 = true; }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP) { up2 = false; }
        if(key == KeyEvent.VK_DOWN) { down2 = false; }
        if(key == KeyEvent.VK_W) { up1 = false; }
        if(key == KeyEvent.VK_S) { down1 = false; }
    }

    @Override
    public int getPlayer1Direction() {
        if (up1 && !down1) return -1;
        if (down1 && !up1) return 1;
        return 0;
    }

    @Override
    public int getPlayer2Direction() {
        if (up2 && !down2) return -1;
        if (down2 && !up2) return 1;
        return 0;
    }

    @Override
    public boolean isRestartPressed() {
        if (restartPressed) {
            restartPressed = false; // consume it
            return true;
        }
        return false;
    }
}
