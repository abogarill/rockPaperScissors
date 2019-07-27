package es.abogarill.game.rest.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Rest configuration in J2EE6 specification
 * @author abogarill
 */
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {
    // nothing to configure here
}
