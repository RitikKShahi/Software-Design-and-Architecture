package PongGame.interfaces;

import PongGame.models.GameState;

public interface IRenderer {
    /**
     * Renders the current state of the game.
     * @param state The current model state
     */
    void renderFrame(GameState state);

    /**
     * Prompts the user for a string configuration (e.g. names)
     */
    String promptInput(String message, String defaultVal);

    /**
     * Shows a modal or message stating who won.
     */
    void showWinnerMsg(String message);
}
