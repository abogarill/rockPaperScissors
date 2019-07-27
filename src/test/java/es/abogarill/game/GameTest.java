package es.abogarill.game;

import static es.abogarill.game.Choice.PAPER;
import static es.abogarill.game.Choice.ROCK;
import static es.abogarill.game.Choice.SCISSORS;
import static es.abogarill.game.Result.DRAW;
import static es.abogarill.game.Result.PLAYER1_WIN;
import static es.abogarill.game.Result.PLAYER2_WIN;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Game
 * @author abogarill
 */
public class GameTest {
    
    private final Game test = new Game();
    
    public GameTest() {
    }

    /**
     * Player1 choose ROCK, Player2 choose ROCK
     * Expected: DRAW
     */
    @Test
    public void testPlayer1ChooseRockAndPlayer2ChooseRock() {
        Choice player1 = ROCK;
        Choice player2 = ROCK;
        Result expected = DRAW;
        
        Result result = test.turn(player1, player2);
        
        assertEquals("Expected "+DRAW, expected, result);
    }
    
    /**
     * Player1 choose ROCK, Player2 choose PAPER
     * Expected: Player2 wins
     */
    @Test
    public void testPlayer1ChooseRockAndPlayer2ChoosePaper() {
        Choice player1 = ROCK;
        Choice player2 = PAPER;
        Result expected = PLAYER2_WIN;
        
        Result result = test.turn(player1, player2);
        
        assertEquals("Expected "+PLAYER2_WIN, expected, result);
    }
    
    /**
     * Player1 choose ROCK, Player2 choose SCISSORS
     * Expected: Player1 wins
     */    
    @Test
    public void testPlayer1ChooseRockAndPlayer2ChooseScissors() {
        Choice player1 = ROCK;
        Choice player2 = SCISSORS;
        Result expected = PLAYER1_WIN;
        
        Result result = test.turn(player1, player2);
        
        assertEquals("Expected "+PLAYER1_WIN, expected, result);
    }

    /**
     * Player1 choose nothing, Player2 choose whatever
     * Expected: IllegalArgumentException
     */    
    @Test(expected=IllegalArgumentException.class)
    public void testPlayer1ChooseNothing() {
        Choice player1 = null;
        Choice player2 = Choice.RANDOM();
        
        test.turn(player1, player2);
        
        fail("Expected IllegalArgumentException");
    }
    
    /**
     * Player1 choose whatever, Player2 choose nothing
     * Expected: IllegalArgumentException
     */     
    @Test(expected=IllegalArgumentException.class)
    public void testPlayer2ChooseNothing() {
        Choice player1 = Choice.RANDOM();
        Choice player2 = null;
        
        test.turn(player1, player2);
        
        fail("Expected IllegalArgumentException");
    }
}
