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

/**
 * REST Game Service. 
 * One Service is created for each Session.
 * The Service life is binding to the Session life. 
 * @author abogarill
 */
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

    @Inject
    public void setDashboardService(DashboardServiceLocal dashboardService) {
        this.dashboardService = dashboardService;
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
    public void initialize() {
        if(playerRounds == null) {
            playerRounds = new ArrayList<>();
        }
    }
    
    /**
     * Core game
     */    
    private Game game;
    
    /**
     * @param game the game to set
     */
    @Inject
    public void setGame(Game game) {
        this.game = game;
    }
    
    /**
     * By the definition of the turn in this use of the game, player1 always
     * choose ROCK and player2 a random choice.
     * @return {@code Round}
     */
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
    
    /**
     * Showing the number of rounds played by that user so far.
     * @return number of round played by user
     */
    @GET
    @Path("showNumberOfRounds")    
    @Override
    public Integer showNumberOfRounds() {
        return playerRounds.size();
    }

    /**
     * showing the rounds played, with 3 columns: what 1st player chose, 
     * what second chose, and the result of the round (that could be 
     * player 1 wins, player 2 wins or draw)
     * @return the result of the round (player 1 wins, player 2 wins or draw)
     */
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
