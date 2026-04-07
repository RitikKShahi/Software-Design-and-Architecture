package PongGame.interfaces;

public interface IScoreRepository {
    /**
     * Saves the result of a match to persistent storage.
     */
    void saveMatch(String p1Name, int p1Score, String p2Name, int p2Score, String winnerName);
}
