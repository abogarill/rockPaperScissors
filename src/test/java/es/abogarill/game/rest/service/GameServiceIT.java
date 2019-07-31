package es.abogarill.game.rest.service;

import es.abogarill.game.Choice;
import es.abogarill.game.Game;
import es.abogarill.game.Result;
import es.abogarill.game.Round;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Game Service Integration Test
 * @author abogarill
 */
@RunWith(Arquillian.class)
public class GameServiceIT {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(GameService.class, GameServiceLocal.class, 
                        DashboardService.class, DashboardServiceLocal.class, 
                        Game.class, Choice.class, Round.class, Result.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private DashboardServiceLocal dashboardService;   
    
    @Inject
    private GameServiceLocal gameService;    

    @Inject
    private GameServiceLocal gameService2;   
    
    @Test
    public void testInitialized() {
        assertNotNull(gameService.showRoundsPlayed());
    }   
    
    @Test
    public void testPlayRound() {
        gameService.playRound();
        assertNotNull(gameService.showRoundsPlayed());
        assertNotNull(dashboardService.showAllRoundsPlayed());
    }      

    @Test
    public void testRestart() {
        gameService.playRound();
        gameService.restartGame();
        assertNotNull(gameService.showRoundsPlayed());
        assertTrue(gameService.showRoundsPlayed().isEmpty());
        assertNotNull(dashboardService.showAllRoundsPlayed());
        assertFalse(dashboardService.showAllRoundsPlayed().isEmpty());
    }    
    
}
