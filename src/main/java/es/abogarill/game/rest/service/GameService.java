package es.abogarill.game.rest.service;

import es.abogarill.game.Choice;
import static es.abogarill.game.Choice.ROCK;
import es.abogarill.game.Game;
import es.abogarill.game.Result;
import javax.ejb.Stateless;
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
@Stateless(name = "GameService", mappedName = "GameService")
@Path("game")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GameService {

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
    @Path("turn")
    public String turn() {
        Choice player1 = ROCK;
        System.out.println("Player1 choose ROCK");
        Choice player2 = Choice.RANDOM();
        System.out.println("Player2 choose "+player2);
        Result result = game.turn(player1, player2);
        System.out.println("Result: "+result);
        return result.toString();
    }
}
