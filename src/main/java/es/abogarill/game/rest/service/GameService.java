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
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Service
 * @author abogarill
 */
@Path("game")
@Startup
@Singleton(name = "GameService", mappedName = "GameService")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class GameService implements GameServiceLocal {

    /**
     * Keep the rounds for each player.
     */
    private static List<Round> playerRounds;
    
    @PostConstruct
    public void initialize() {
        playerRounds = new ArrayList<>();
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
     * @return {@code Result}
     */
    @GET
    @Path("playRound")    
    @Lock(LockType.READ)
    @Override
    public String playRound() {
        Choice player1 = ROCK;
        Choice player2 = Choice.RANDOM();
        Result result = game.turn(player1, player2);
        addRound(new Round(player1, player2, result));
        return result.toString();
    }
    
    /**
     * Showing the number of rounds played by that user so far.
     * @return number of round played by user
     */
    @GET
    @Path("showNumberOfRounds")    
    @Lock(LockType.READ)
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
    @Lock(LockType.READ)
    @Override    
    public List<Round> showRoundsPlayed() {
        return Collections.unmodifiableList(playerRounds);
    }
    
    /**
     * will set round count for that user to 0 and also clean the table.
     */
    @GET
    @Path("restartGame")    
    @Lock(LockType.WRITE)
    @Override    
    public void restartGame() {
        playerRounds.clear();
    }
    
    @Lock(LockType.WRITE)
    protected void addRound(Round round) {
        System.out.println(round);
        playerRounds.add(round);
    } 
    
}
