package PongGame.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JOptionPane;
import PongGame.interfaces.IRenderer;
import PongGame.models.BallModel;
import PongGame.models.GameState;
import PongGame.models.PaddleModel;

public class SwingGamePanel extends Canvas implements IRenderer {
    
    public SwingGamePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setFocusable(true);
    }

    @Override
    public void renderFrame(GameState state) {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();
        
        // draw background
        g.setColor(Color.black);
        g.fillRect(0, 0, state.getLogicalWidth(), state.getLogicalHeight());
        
        // draw ball
        BallModel ball = state.getBall();
        g.setColor(Color.white);
        g.fillOval(ball.getX(), ball.getY(), BallModel.SIZE, BallModel.SIZE);
        
        // draw paddles and score
        drawPaddle(g, state.getLeftPaddle(), Color.CYAN, state.getLogicalWidth());
        drawPaddle(g, state.getRightPaddle(), Color.ORANGE, state.getLogicalWidth());
        
        g.dispose();
        buffer.show();
    }
    
    private void drawPaddle(Graphics g, PaddleModel paddle, Color color, int gameWidth) {
        // draw paddle
        g.setColor(color);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        
        // draw score
        String scoreText = Integer.toString(paddle.getScore());
        int sx;
        Font font = new Font("ROBOTO", Font.PLAIN, 50);
        int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
        int padding = 25;
        
        if(paddle.isLeft()) {
            sx = gameWidth/2 - strWidth - padding;
        } else {
            sx = gameWidth/2 + padding;
        }
        
        g.setFont(font);
        g.drawString(scoreText, sx, 50);
    }

    @Override
    public String promptInput(String message, String defaultVal) {
        String input = JOptionPane.showInputDialog(this, message, defaultVal);
        return (input == null || input.isEmpty()) ? defaultVal : input;
    }

    @Override
    public void showWinnerMsg(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
