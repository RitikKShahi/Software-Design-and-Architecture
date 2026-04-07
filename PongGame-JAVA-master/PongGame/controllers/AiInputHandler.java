package PongGame.controllers;

import PongGame.interfaces.IInputHandler;
import PongGame.models.GameState;
import PongGame.models.PaddleModel;

public class AiInputHandler implements IInputHandler {
    
    private IInputHandler humanInput;
    private GameState state;
    
    public AiInputHandler(IInputHandler humanInput, GameState state) {
        this.humanInput = humanInput;
        this.state = state;
    }

    @Override
    public int getPlayer1Direction() {
        // Player 1 remains human-controlled
        return humanInput.getPlayer1Direction();
    }

    @Override
    public int getPlayer2Direction() {
        // Player 2 is AI controlled based on ball position
        int ballY = state.getBall().getY();
        
        PaddleModel p2 = state.getRightPaddle();
        int paddleY = p2.getY();
        int paddleCenter = paddleY + (p2.getHeight() / 2);
        
        // Simple chasing algorithm
        if (ballY < paddleCenter - 10) {
            return -1; // Move Up
        } else if (ballY > paddleCenter + 10) {
            return 1;  // Move Down
        }
        
        return 0; // Stop
    }

    @Override
    public boolean isRestartPressed() {
        return humanInput.isRestartPressed();
    }
}
