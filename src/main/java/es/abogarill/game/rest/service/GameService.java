package es.abogarill.game.rest.service;

import es.abogarill.game.Choice;
import static es.abogarill.game.Choice.ROCK;
import es.abogarill.game.Game;
import es.abogarill.game.Result;
import es.abogarill.game.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.PostActivate;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@SessionScoped
@Stateful(name = "GameService", mappedName = "GameService")
@DependsOn({"DashboardService"})
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class GameService implements GameServiceLocal {

    /**
     * Needed for keep all the rounds
     */
    private DashboardServiceLocal dashboardService;

    /**
     * dashboardService setter
     * @param dashboardService the dashboardService to inject
     */
    @Inject
    public void setDashboardService(DashboardServiceLocal dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * dashboardService getter
     * @return the dashboardService injected
     */
    public DashboardServiceLocal getDashboardService() {
        return dashboardService;
    }

    
    /**
     * Needed for Stateful EJB
     */
    public GameService() {
    }        
    
    /**
     * Keep the rounds for each player.
     */
    private List<Round> playerRounds;
    
    /**
     * Initialize the EJB
     */
    @PostConstruct
    @PostActivate
    protected void initialize() {
        if(playerRounds == null) {
            playerRounds = new ArrayList<>();
        }
    }
    
    /**
     * Core game
     */    
    private Game game;
    
    /**
     * game core setter
     * @param game the game to inject
     */
    @Inject
    public void setGame(Game game) {
        this.game = game;
    }
    
    /**
     * game core getter
     * @return game core injected
     */
    public Game getGame() {
        return game;
    }
    
    @GET
    @Path("playRound")    
    @Override
    public Result playRound() {
        Choice player1 = ROCK;
        Choice player2 = Choice.RANDOM();
        Result result = game.turn(player1, player2);
        Round round = new Round(player1, player2, result);
        addRound(round);
        return result;
    }

    @GET
    @Path("showRoundsPlayed")    
    @Override    
    public List<Round> showRoundsPlayed() {
        return Collections.unmodifiableList(playerRounds);
    }
    
    /**
     * will set round count for that user to 0 and also clean the table.
     */
    @GET
    @Path("restartGame")    
    @Override    
    public void restartGame() {
        playerRounds.clear();
    }
    
    /**
     * Add a new round
     * @param round the new round
     */
    private void addRound(final Round round) {
        playerRounds.add(round);
        dashboardService.addRoundResult(round.getResult());
    } 
    
}
