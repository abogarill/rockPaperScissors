package es.abogarill.game.rest.service;

import es.abogarill.game.Result;
import es.abogarill.game.Round;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Dashboard Service: Contains the all players statistics
 * The Service life is binding to the Application life. 
 * @author abogarill
 */
@Local
public interface DashboardServiceLocal {

    /**
     * Add a new round result
     * @param newResult the new round result
     */    
    void addRoundResult(final Result newResult);
    
    /**
     * Show the number of rounds played by winner or draw
     * @return the number of rounds played by winner or draw
     */
    @GET
    @Path("showAllRoundsPlayed")      
    Map<Result,Integer> showAllRoundsPlayed();    

}
