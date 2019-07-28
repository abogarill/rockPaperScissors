package es.abogarill.game.rest.service;

import es.abogarill.game.Choice;
import static es.abogarill.game.Choice.ROCK;
import es.abogarill.game.Game;
import static es.abogarill.game.Result.DRAW;
import static es.abogarill.game.Result.PLAYER1_WIN;
import static es.abogarill.game.Result.PLAYER2_WIN;
import es.abogarill.game.Round;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Teste GameService
 * @author abogarill
 */
public class GameServiceTest {
    
    private GameService service;
    private Game game;
    
    public GameServiceTest() {
    }
    
    /**
     * Initialize the service and de mock game
     */
    @Before
    public void setUp() {
        service = new GameService();
        service.initialize();
        game = mock(Game.class);
        service.setGame(game);
    }
    
    /**
     * Test that game core is invoke by game service and return DRAW
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testDrawTurn() throws Exception {
        String expResult = DRAW.toString();
        prepareGameMockForDrawResult();
        
        String result = service.playRound();
        
        assertEquals(expResult, result);
        assertEquals(new Integer(1), service.showNumberOfRounds());
    }
    
    /**
     * Test that game core is invoke by game service and return PLAYER1_WIN
     * @throws java.lang.Exception if something wrong happened
     */    
    @Test
    public void testPlayer1WinTurn() throws Exception {
        String expResult = PLAYER1_WIN.toString();
        prepareGameMockForPlayer1WinResult();
        
        String result = service.playRound();
        
        assertEquals(expResult, result);
    }    
    
    /**
     * Test that game core is invoke by game service and return PLAYER2_WIN
     * @throws java.lang.Exception if something wrong happened
     */       
    @Test
    public void testPlayer2WinTurn() throws Exception {
        String expResult = PLAYER2_WIN.toString();
        prepareGameMockForPlayer2WinResult();
        
        String result = service.playRound();
        
        assertEquals(expResult, result);
    }      
    
    private void prepareGameMockForPlayer1WinResult(){
        when(game.turn(any(Choice.class), any(Choice.class))).thenReturn(PLAYER1_WIN);
    }
    
    private void prepareGameMockForPlayer2WinResult(){
        when(game.turn(any(Choice.class), any(Choice.class))).thenReturn(PLAYER2_WIN);
    }
        
    private void prepareGameMockForDrawResult(){
        when(game.turn(any(Choice.class), any(Choice.class))).thenReturn(DRAW);
    }      
    
    /**
     * Test that the number is rounds at the beginning is zero.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testNumberOfRoundsInitialized() throws Exception {
        Integer expResult = 0;

        Integer result = service.showNumberOfRounds();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test that the number is one after play round.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testNumberOfRoundsOneAfterPlayRound() throws Exception {
        prepareGameMockForDrawResult(); //the result does not matter here 
        Integer expResult = 1;
        service.playRound();

        Integer result = service.showNumberOfRounds();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test that round counter for user is zero and also the table is cleaned.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testRestartGame() throws Exception {
        prepareGameMockForDrawResult(); //the result does not matter here 
        Integer expCounterAfterRestart = 0;
        service.playRound();

        service.restartGame();
        final List<Round> roundsAfterRestart = service.showRoundsPlayed();
        
        assertEquals(expCounterAfterRestart.intValue(), roundsAfterRestart.size());
    }
    
    /**
     * Test that showing the rounds after two rounds.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testShowTwoRoundsPlayed() throws Exception {
        prepareGameMockForDrawAndPlayer1ResultsAfterTwoRounds();
        Integer expCounterAfterRestart = 2;
        service.playRound();
        service.playRound();
        
        final List<Round> roundsAfterRestart = service.showRoundsPlayed();
        
        assertEquals(expCounterAfterRestart.intValue(), roundsAfterRestart.size());
            Round roundOne = roundsAfterRestart.get(0);
        assertEquals(ROCK, roundOne.getPlayer1());
        assertNotNull(roundOne.getPlayer2()); // because the choice is random
        assertEquals(DRAW, roundOne.getResult());
            Round roundTwo = roundsAfterRestart.get(1);
        assertEquals(ROCK, roundTwo.getPlayer1());
        assertNotNull(roundTwo.getPlayer2()); // because the choice is random
        assertEquals(PLAYER1_WIN, roundTwo.getResult());        
    }    

    private void prepareGameMockForDrawAndPlayer1ResultsAfterTwoRounds(){
        when(game.turn(any(Choice.class), any(Choice.class)))
            .thenReturn(DRAW).thenReturn(PLAYER1_WIN);
        
    }     
}
