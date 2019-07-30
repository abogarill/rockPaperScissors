package es.abogarill.game.rest.service;

import es.abogarill.game.Choice;
import static es.abogarill.game.Choice.ROCK;
import es.abogarill.game.Game;
import es.abogarill.game.Result;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test GameService
 * @author abogarill
 */
public class GameServiceTest {
    
    private DashboardServiceLocal dashboardService;
    private GameService gameService;
    private Game game;
    
    public GameServiceTest() {
    }
    
    /**
     * Initialize the service and de mock game
     */
    @Before
    public void setUp() {
        dashboardService = spy(DashboardServiceLocal.class);
        gameService = new GameService();
        gameService.initialize();
        gameService.setDashboardService(dashboardService);
        game = mock(Game.class);
        gameService.setGame(game);
    }
    
    /**
     * Test that game core is invoke by game service and return DRAW
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testDrawTurn() throws Exception {
        Result expResult = DRAW;
        prepareGameMockForDrawResult();
    
        Result result = gameService.playRound();
        
        assertEquals(expResult, result);
        assertEquals(1, gameService.showRoundsPlayed().size());
        verify(dashboardService).addRoundResult(expResult);
    }
    
    /**
     * Test that game core is invoke by game service and return PLAYER1_WIN
     * @throws java.lang.Exception if something wrong happened
     */    
    @Test
    public void testPlayer1WinTurn() throws Exception {
        Result expResult = PLAYER1_WIN;
        prepareGameMockForPlayer1WinResult();
    
        Result result = gameService.playRound();
        
        assertEquals(expResult, result);
        verify(dashboardService).addRoundResult(expResult);
    }    
    
    /**
     * Test that game core is invoke by game service and return PLAYER2_WIN
     * @throws java.lang.Exception if something wrong happened
     */       
    @Test
    public void testPlayer2WinTurn() throws Exception {
        Result expResult = PLAYER2_WIN;
        prepareGameMockForPlayer2WinResult();
        
        Result result = gameService.playRound();
        
        assertEquals(expResult, result);
        verify(dashboardService).addRoundResult(expResult);
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
     * Test that round counter for user is zero and also the table is cleaned.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testRestartGame() throws Exception {
        prepareGameMockForDrawResult(); //the result does not matter here 
        Integer expCounterAfterRestart = 0;
        Result playResult = gameService.playRound();

        gameService.restartGame();
        final List<Round> roundsAfterRestart = gameService.showRoundsPlayed();
        
        assertEquals(expCounterAfterRestart.intValue(), roundsAfterRestart.size());
        verify(dashboardService).addRoundResult(playResult);
    }
    
    /**
     * Test that showing the rounds after two rounds.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testShowTwoRoundsPlayed() throws Exception {
        prepareGameMockForDrawAndPlayer1ResultsAfterTwoRounds();
        Integer expCounterAfterRestart = 2;
        Result play1 = gameService.playRound();
        Result play2 = gameService.playRound();
        
        final List<Round> roundsAfterRestart = gameService.showRoundsPlayed();
        
        assertEquals(expCounterAfterRestart.intValue(), roundsAfterRestart.size());
            Round roundOne = roundsAfterRestart.get(0);
        assertEquals(ROCK, roundOne.getPlayer1());
        assertNotNull(roundOne.getPlayer2()); // because the choice is random
        assertEquals(DRAW, roundOne.getResult());
            Round roundTwo = roundsAfterRestart.get(1);
        assertEquals(ROCK, roundTwo.getPlayer1());
        assertNotNull(roundTwo.getPlayer2()); // because the choice is random
        assertEquals(PLAYER1_WIN, roundTwo.getResult());   
        verify(dashboardService, times(2)).addRoundResult(any(Result.class));
    }    

    /**
     * Test of setDashboardService method, of class GameService.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testSetDashboardService() throws Exception {
        DashboardServiceLocal dashboardService = new DashboardService();
        GameService instance = new GameService();
        instance.setDashboardService(dashboardService);
        assertEquals(dashboardService, instance.getDashboardService());
    }

    /**
     * Test of setGame method, of class GameService.
     * @throws java.lang.Exception if something wrong happened
     */
    @Test
    public void testSetGame() throws Exception {
        Game game = new Game();
        GameService instance = new GameService();
        instance.setGame(game);
        assertEquals(game, instance.getGame());
    }

    private void prepareGameMockForDrawAndPlayer1ResultsAfterTwoRounds(){
        when(game.turn(any(Choice.class), any(Choice.class)))
            .thenReturn(DRAW).thenReturn(PLAYER1_WIN);
    }        
    
}
