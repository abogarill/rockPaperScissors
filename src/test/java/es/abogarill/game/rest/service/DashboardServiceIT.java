package es.abogarill.game.rest.service;

import es.abogarill.game.Result;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
    }

}
