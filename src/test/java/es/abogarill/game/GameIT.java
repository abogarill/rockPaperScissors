package es.abogarill.game;

import static es.abogarill.game.Choice.PAPER;
import static es.abogarill.game.Result.DRAW;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Game core Integration Test
 * @author abogarill
 */
@RunWith(Arquillian.class)
public class GameIT {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Game.class, Choice.class, Round.class, Result.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private Game game;
    
    @Test
    public void testTurnDraw() {
        assertTrue(DRAW.equals(game.turn(PAPER, PAPER)));
    }

}
