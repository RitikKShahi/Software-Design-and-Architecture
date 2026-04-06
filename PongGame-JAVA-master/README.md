# PongGame-JAVA
The classic Ping Pong game written in Java.

### How to Run

1.  **Project Structure**: Ensure all `.java` files are located within a folder named `PongGame` (this matches the package declaration in the source files).
2.  **Compile**: From the root directory (one level above `PongGame`), run:
    ```bash
    javac PongGame/*.java
    ```
3.  **Run**: Execute the application using:
    ```bash
    java PongGame.Game
    ```

### Game Play Snapshot

![](https://user-images.githubusercontent.com/47673815/79040898-718f7b80-7c09-11ea-807a-0bf8e1587dbb.png)

This is a simple GUI based game with the objective to hit the ball with the paddle and not let it leave the game screen.

### Controls

| Player | Up | Down |
| :--- | :--- | :--- |
| **Player 1 (Left)** | `W` | `S` |
| **Player 2 (Right)** | `Up Arrow` | `Down Arrow` |


# Correct compilation command
javac PongGame/*.java
# Correct execution command (from project root)
java PongGame.Game