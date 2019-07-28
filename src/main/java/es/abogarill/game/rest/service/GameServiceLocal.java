package es.abogarill.game.rest.service;

import es.abogarill.game.Round;
import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Game Service local interface
 * @author abogarill
 */
@Local
public interface GameServiceLocal {

    /**
     * By the definition of the turn in this use of the game, player1 always
     * choose ROCK and player2 a random choice.
     * @return {@code Result}
     */
    @GET
    @Path(value = "playRound")
    String playRound();
    
    /**
     * Showing the number of rounds played by that user so far.
     * @return number of round played by user
     */
    @GET
    @Path("showNumberOfRounds")    
    Integer showNumberOfRounds();
    
    /**
     * showing the rounds played, with 3 columns: what 1st player chose, 
     * what second chose, and the result of the round (that could be 
     * player 1 wins, player 2 wins or draw)
     * @return the result of the round (player 1 wins, player 2 wins or draw)
     */
    @GET
    @Path("showRoundsPlayed")      
    List<Round> showRoundsPlayed();
        
    /**
     * will set round count for that user to 0 and also clean the table.
     */
    @GET
    @Path("restartGame")  
    void restartGame();
    
}
