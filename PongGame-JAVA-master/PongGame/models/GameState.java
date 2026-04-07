package PongGame.models;

public class GameState {
    private BallModel ball;
    private PaddleModel leftPaddle;
    private PaddleModel rightPaddle;
    
    private int logicalWidth;
    private int logicalHeight;
    private int maxScore = 10;
    
    private String p1Name = "Player 1";
    private String p2Name = "Player 2";
    
    public GameState(int width, int height) {
        this.logicalWidth = width;
        this.logicalHeight = height;
        
        ball = new BallModel(logicalWidth/2, logicalHeight/2);
        leftPaddle = new PaddleModel(true, logicalWidth, logicalHeight);
        rightPaddle = new PaddleModel(false, logicalWidth, logicalHeight);
    }
    
    public void setupConfig(String p1Name, String p2Name, int maxScore) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.maxScore = maxScore;
    }
    
    public void update(int p1Dir, int p2Dir) {
        leftPaddle.updatePosition(p1Dir, logicalHeight);
        rightPaddle.updatePosition(p2Dir, logicalHeight);
        
        ball.update();
        
        checkCollisions();
    }
    
    private void checkCollisions() {
        // Top and bottom walls
        if (ball.getY() >= logicalHeight - BallModel.SIZE || ball.getY() <= 0) {
            ball.changeYDir();
        }
        
        int ballX = ball.getX();
        int ballY = ball.getY();
        
        // Left paddle hit
        if (ballX <= leftPaddle.getWidth() && ballY >= leftPaddle.getY() - BallModel.SIZE && ballY <= leftPaddle.getY() + leftPaddle.getHeight()) {
            ball.changeXDir();
        }
        
        // Right paddle hit
        if (ballX >= rightPaddle.getX() - BallModel.SIZE && ballY >= rightPaddle.getY() - BallModel.SIZE && ballY <= rightPaddle.getY() + rightPaddle.getHeight()) {
            ball.changeXDir();
        }
        
        // Left side limit - right paddle gets point
        if (ballX <= 0) {
            rightPaddle.addPoint();
            ball.reset(logicalWidth/2, logicalHeight/2);
        }
        
        // Right side limit - left paddle gets point
        if (ballX >= logicalWidth - BallModel.SIZE) {
            leftPaddle.addPoint();
            ball.reset(logicalWidth/2, logicalHeight/2);
        }
    }
    
    public void reset() {
        ball.reset(logicalWidth/2, logicalHeight/2);
        leftPaddle.reset(logicalHeight);
        rightPaddle.reset(logicalHeight);
    }
    
    public String checkWin() {
        if (leftPaddle.getScore() >= maxScore) {
            return p1Name;
        } else if (rightPaddle.getScore() >= maxScore) {
            return p2Name;
        }
        return null;
    }
    
    public BallModel getBall() { return ball; }
    public PaddleModel getLeftPaddle() { return leftPaddle; }
    public PaddleModel getRightPaddle() { return rightPaddle; }
    
    public String getP1Name() { return p1Name; }
    public String getP2Name() { return p2Name; }
    public int getMaxScore() { return maxScore; }
    
    public int getLogicalWidth() { return logicalWidth; }
    public int getLogicalHeight() { return logicalHeight; }
}
