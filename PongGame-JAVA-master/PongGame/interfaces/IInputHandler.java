package PongGame.interfaces;

public interface IInputHandler {
    /**
     * Returns the movement direction for player 1 (-1 for up, 1 for down, 0 for stop)
     */
    int getPlayer1Direction();

    /**
     * Returns the movement direction for player 2 (-1 for up, 1 for down, 0 for stop)
     */
    int getPlayer2Direction();

    /**
     * Returns if the restart key/action was triggered.
     */
    boolean isRestartPressed();
}
