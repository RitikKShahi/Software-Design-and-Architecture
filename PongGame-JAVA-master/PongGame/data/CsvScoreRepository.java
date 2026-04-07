package PongGame.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import PongGame.interfaces.IScoreRepository;

public class CsvScoreRepository implements IScoreRepository {
    private String filepath;
    
    public CsvScoreRepository(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void saveMatch(String p1Name, int p1Score, String p2Name, int p2Score, String winnerName) {
        File file = new File(filepath);
        boolean exists = file.exists();
        try (FileWriter writer = new FileWriter(file, true)) {
            if (!exists) {
                writer.write("Player 1,Score 1,Player 2,Score 2,Winner\n");
            }
            writer.write(String.format("%s,%d,%s,%d,%s\n", 
                    p1Name, p1Score, 
                    p2Name, p2Score, 
                    winnerName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
