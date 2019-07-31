package es.abogarill.game.rest.service;

import es.abogarill.game.Result;
import static es.abogarill.game.Result.DRAW;
import static es.abogarill.game.Result.PLAYER1_WIN;
import static es.abogarill.game.Result.PLAYER2_WIN;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test DashboadService
 * @author abogarill
 */
public class DashboardServiceTest {
        
    private DashboardService dashboardService;

    public DashboardServiceTest() {
    }
    
    /**
     * Initialize the service
     */
    @Before
    public void setUp() {
        dashboardService = new DashboardService();
    }
    
    @Test
    public void testDashboardInit(){
        Map<Result, Integer> expected = getResultInitialized();
        
        dashboardService.initialize();
        
        assertEquals(expected, dashboardService.getAllRounds());
    }

    @Test
    public void testAddRoundDrawResult(){
        Map<Result, Integer> expected = getResultInitialized();
        expected.put(DRAW, 1);
        dashboardService.initialize();
        
        dashboardService.addRoundResult(DRAW);
        
        assertEquals(expected.get(DRAW), dashboardService.getAllRounds().get(DRAW));
    }
    
    @Test
    public void testShowAllRoundsPlayedAfterOnePlayDrawResult(){
        Map<Result, Integer> expected = getResultInitialized();
        expected.put(DRAW, 1);
        dashboardService.initialize();
        dashboardService.addRoundResult(DRAW);        

        Map<Result, Integer> result = dashboardService.showAllRoundsPlayed();
        
        assertEquals(expected.get(PLAYER1_WIN), result.get(PLAYER1_WIN));
        assertEquals(expected.get(PLAYER2_WIN), result.get(PLAYER2_WIN));
        assertEquals(expected.get(DRAW), result.get(DRAW));
    }    
    
    private Map<Result, Integer> getResultInitialized() {
        Map<Result, Integer> result = new HashMap<>();
        result.put(PLAYER1_WIN, 0);
        result.put(PLAYER2_WIN, 0);
        result.put(DRAW, 0);
        return result;
    }
}
