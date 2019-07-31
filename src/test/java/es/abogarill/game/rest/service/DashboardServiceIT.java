package es.abogarill.game.rest.service;

import es.abogarill.game.Result;
import static es.abogarill.game.Result.DRAW;
import static es.abogarill.game.Result.PLAYER1_WIN;
import static es.abogarill.game.Result.PLAYER2_WIN;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Dashboard Service Integration Test
 * @author abogarill
 */
@RunWith(Arquillian.class)
public class DashboardServiceIT {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(DashboardService.class, DashboardServiceLocal.class, Result.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private DashboardServiceLocal dashboardService;
    
    @Test
    public void testInitialized() {
        assertNotNull(dashboardService.showAllRoundsPlayed());
        assertEquals(0, dashboardService.showAllRoundsPlayed().get(DRAW).intValue());
        assertEquals(0, dashboardService.showAllRoundsPlayed().get(PLAYER1_WIN).intValue());
        assertEquals(0, dashboardService.showAllRoundsPlayed().get(PLAYER2_WIN).intValue());        
    }

    @Test
    public void testAddRoundDrawResult() {
        dashboardService.addRoundResult(DRAW);
        assertNotNull(dashboardService.showAllRoundsPlayed());
        assertNotNull(dashboardService.showAllRoundsPlayed().get(DRAW));
        assertEquals(1, dashboardService.showAllRoundsPlayed().get(DRAW).intValue());
        assertEquals(0, dashboardService.showAllRoundsPlayed().get(PLAYER1_WIN).intValue());
        assertEquals(0, dashboardService.showAllRoundsPlayed().get(PLAYER2_WIN).intValue());
    }    
    
}
