package es.abogarill.game;

/**
 * The core game class
 * @author abogarill
 */
public class Game {
    
    /**
     * Turn game: Rock beat Scissors, Scissors beat Paper, Paper beat Rock, others is a draw 
     * @param player1 player1 choice
     * @param player2 player2 choice
     * @return the result of the game
     * @throws IllegalArgumentException if player1 or player2 don't choose anything 
     */
    public Result turn(Choice player1, Choice player2) throws IllegalArgumentException {
        if(player1 == null || player2 == null) {
            throw new IllegalArgumentException("Player choice cannot be null");
        }
        
        if(player1.beats(player2)) {
            return Result.PLAYER1_WIN;
        } else if(player2.beats(player1)) {
            return Result.PLAYER2_WIN;
        } else {
            return Result.DRAW;
        }
    }
    
}
