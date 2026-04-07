package PongGame.models;

public class BallModel {
    public static final int SIZE = 16;
    
    private int x, y;
    private int xVel, yVel;
    private int speed = 5;
    
    public BallModel(int startX, int startY) {
        reset(startX, startY);
    }
    
    public void reset(int startX, int startY) {
        x = startX - SIZE / 2;
        y = startY - SIZE / 2;
        
        xVel = sign(Math.random() * 2 - 1);
        yVel = sign(Math.random() * 2 - 1);
    }
    
    private int sign(double d) {
        if (d >= 0) { return 1; }
        else { return -1; }
    }
    
    public void changeXDir() {
        xVel *= -1;
    }
    
    public void changeYDir() {
        yVel *= -1;
    }
    
    public void update() {
        x += xVel * speed;
        y += yVel * speed;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
}
