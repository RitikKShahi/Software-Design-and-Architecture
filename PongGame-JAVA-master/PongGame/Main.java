package PongGame;

import PongGame.data.CsvScoreRepository;
import PongGame.models.GameState;
import PongGame.ui.GameWindow;
import PongGame.ui.SwingGamePanel;
import PongGame.ui.SwingKeyInput;

public class Main {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * 9/16;

    public static void main(String[] args) {
        // 1. Initialize logic layer
        GameState state = new GameState(WIDTH, HEIGHT);
        
        // 2. Initialize UI layer
        SwingGamePanel panel = new SwingGamePanel(WIDTH, HEIGHT);
        SwingKeyInput keyInput = new SwingKeyInput();
        panel.addKeyListener(keyInput);
        
        new GameWindow("Meghna's Pong (Refactored)", panel);
        
        // 3. Initialize Data layer
        CsvScoreRepository repo = new CsvScoreRepository("match_results.csv");
        
        // 4. Initialize Controller
        GameEngine engine = new GameEngine(state, panel, keyInput, repo);
        
        // Setup initial config and start game
        engine.setupConfig();
        engine.start();
    }
}
