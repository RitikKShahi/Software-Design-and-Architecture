package PongGame;

import PongGame.controllers.AiInputHandler;
import PongGame.interfaces.IInputHandler;
import PongGame.interfaces.IRenderer;
import PongGame.interfaces.IScoreRepository;
import PongGame.models.GameState;

public class GameEngine implements Runnable {
    
    private GameState state;
    private IRenderer renderer;
    private IInputHandler baseInput;
    private IInputHandler activeInput;
    private IScoreRepository repo;
    
    private boolean running = false;
    private Thread gameThread;
    
    public GameEngine(GameState state, IRenderer renderer, IInputHandler input, IScoreRepository repo) {
        this.state = state;
        this.renderer = renderer;
        this.baseInput = input;
        this.activeInput = input;
        this.repo = repo;
    }
    
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }
    
    public void stop() {
        try {
            running = false;
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double maxFPS = 60.0;
        double frameTime = 1000000000 / maxFPS;
        double delta = 0;
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / frameTime;
            lastTime = now;
            
            if(delta >= 1) {
                updateTick();
                delta--;
                renderer.renderFrame(state);
            }
        }
        stop();
    }
    
    private void updateTick() {
        if (activeInput.isRestartPressed()) {
            state.reset();
        }
        
        int p1Dir = activeInput.getPlayer1Direction();
        int p2Dir = activeInput.getPlayer2Direction();
        
        state.update(p1Dir, p2Dir);
        
        String winner = state.checkWin();
        if (winner != null) {
            handleWin(winner);
        }
    }
    
    private void handleWin(String winner) {
        running = false;
        
        String message = winner + " wins the match!";
        renderer.showWinnerMsg(message);
        
        repo.saveMatch(state.getP1Name(), state.getLeftPaddle().getScore(), 
                       state.getP2Name(), state.getRightPaddle().getScore(), 
                       winner);
                       
        // Setup next game
        setupConfig();
        state.reset();
        
        running = true;
    }
    
    public void setupConfig() {
        String mode = renderer.promptInput("Play against Computer? (Yes/No):", "No");
        boolean isAi = mode != null && mode.equalsIgnoreCase("Yes");
        
        if (isAi) {
            this.activeInput = new AiInputHandler(baseInput, state);
        } else {
            this.activeInput = baseInput;
        }

        String p1Name = renderer.promptInput("Enter Player 1 Name:", state.getP1Name());
        String p2Name = isAi ? "Computer" : renderer.promptInput("Enter Player 2 Name:", state.getP2Name());
        String scoreStr = renderer.promptInput("Enter Maximum Score:", String.valueOf(state.getMaxScore()));
        
        int maxScore = 10;
        try {
            maxScore = Integer.parseInt(scoreStr);
        } catch (NumberFormatException e) {
            maxScore = 10;
        }
        
        state.setupConfig(p1Name, p2Name, maxScore);
    }
}
