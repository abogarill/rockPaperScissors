package es.abogarill.game;

import static es.abogarill.game.Choice.PAPER;
import static es.abogarill.game.Choice.ROCK;
import static es.abogarill.game.Choice.SCISSORS;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Choice
 * @author abogarill
 */
public class ChoiceTest {
    
    public ChoiceTest() {
    }

    /**
     * Test of values method, of class Choice.
     */
    @Test
    public void testValues() {
        Choice[] expResult = {ROCK, PAPER, SCISSORS};
        Choice[] result = Choice.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Choice.
     */
    @Test
    public void testValueOf() {
        String name = "ROCK";
        Choice expResult = ROCK;
        Choice result = Choice.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of beats method: Rock can't beat Rock. It's a draw.
     * Expected: false
     */
    @Test
    public void testRockNotBeatsRock() {
        Choice otherPlayer = ROCK;
        Choice instance = ROCK;
        boolean expResult = false;
        boolean result = instance.beats(otherPlayer);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of beats method: Rock can't beat Paper
     * Expected: false
     */
    @Test
    public void testRockNotBeatsPaper() {
        Choice otherPlayer = PAPER;
        Choice instance = ROCK;
        boolean expResult = false;
        boolean result = instance.beats(otherPlayer);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of beats method: Rock beats Scissors
     * Expected: true
     */
    @Test
    public void testRockBeatsScissors() {
        Choice otherPlayer = SCISSORS;
        Choice instance = ROCK;
        boolean expResult = true;
        boolean result = instance.beats(otherPlayer);
        assertEquals(expResult, result);
    }
}
