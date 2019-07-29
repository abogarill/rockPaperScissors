package es.abogarill.game.rest.config;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Rest configuration in J2EE6 specification
 * @author abogarill
 */
@ApplicationPath("/game")
public class JAXRSConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(es.abogarill.game.rest.service.DashboardService.class);
        resources.add(es.abogarill.game.rest.service.GameService.class);
    }
}
