package es.abogarill.game.rest.service;

import es.abogarill.game.Result;
import static es.abogarill.game.Result.DRAW;
import static es.abogarill.game.Result.PLAYER1_WIN;
import static es.abogarill.game.Result.PLAYER2_WIN;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Startup
@Singleton(name = "DashboardService", mappedName = "DashboardService")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DashboardService implements DashboardServiceLocal {

    /**
     * Needed for Singleton EJB
     */
    public DashboardService() {
    }
    
    /**
     * Keep the rounds for all players.
     */
    private Map<Result, Integer> allRounds;

    /**
     * All rounds map getter
     * @return all round map
     */
    public Map<Result, Integer> getAllRounds() {
        return allRounds;
    }
    
    /**
     * Initialize the EJB
     */
    @PostConstruct
    protected final void initialize() {
        if(allRounds == null) {
            allRounds = new HashMap<>();
            allRounds.put(PLAYER1_WIN, 0);
            allRounds.put(PLAYER2_WIN, 0);
            allRounds.put(DRAW, 0);
        }
    }    

    @Lock(LockType.WRITE)
    @Override
    public void addRoundResult(final Result newResult) {
        if(allRounds.containsKey(newResult)) {
            allRounds.put(newResult, allRounds.get(newResult)+1);
        }
    } 
    
    @GET
    @Path("showAllRoundsPlayed")
    @Lock(LockType.READ)
    @Override
    public Map<Result, Integer> showAllRoundsPlayed() {
        return Collections.unmodifiableMap(allRounds);
    }

}
