package es.abogarill.game.rest.service;

import es.abogarill.game.Choice;
import es.abogarill.game.Game;
import static es.abogarill.game.Result.DRAW;
import static es.abogarill.game.Result.PLAYER1_WIN;
import static es.abogarill.game.Result.PLAYER2_WIN;
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
        
        String result = service.turn();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test that game core is invoke by game service and return PLAYER1_WIN
     * @throws java.lang.Exception if something wrong happened
     */    
    @Test
    public void testPlayer1WinTurn() throws Exception {
        String expResult = PLAYER1_WIN.toString();
        prepareGameMockForPlayer1WinResult();
        
        String result = service.turn();
        
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
        
        String result = service.turn();
        
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
}
