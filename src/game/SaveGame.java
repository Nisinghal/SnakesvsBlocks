package game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class SaveGame.
 */
public class SaveGame implements Serializable {
    
    /** The current coins. */
    String currentCoins = "0";
    
    /** The current score. */
    String currentScore = "0";
    
    /** The snake length. */
    String snakeLength = "0";
    
    /** The last score. */
    String lastScore = "0";
    
    /** The all scores. */
    ArrayList<Integer> allScores = new ArrayList<>();
}
