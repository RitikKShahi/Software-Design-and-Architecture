package PongGame.models;

public class PaddleModel {
    private int x, y;
    private int speed = 10;
    private int width = 25, height = 100;
    private int score;
    private boolean left;
    
    public PaddleModel(boolean left, int logicWidth, int logicHeight) {
        this.left = left;
        if (this.left) {
            x = 0;
        } else {
            x = logicWidth - width;
        }
        y = logicHeight / 2 - height / 2;
    }
    
    public void addPoint() {
        score++;
    }
    
    public void reset(int logicHeight) {
        y = logicHeight / 2 - height / 2;
        score = 0;
    }
    
    public void updatePosition(int dy, int logicHeight) {
        int vel = speed * dy;
        y += vel;
        
        if (y > logicHeight - height) { y = logicHeight - height; }
        else if (y < 0) { y = 0; }
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getScore() { return score; }
    public boolean isLeft() { return left; }
}
