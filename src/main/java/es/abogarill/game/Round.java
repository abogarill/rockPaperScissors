package es.abogarill.game;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import es.abogarill.game.serializer.RoundSerializer;

/**
 * Represent a round of the game with players choice and the result 
 * @author abogarill
 */
@JsonSerialize(using = RoundSerializer.class)
public class Round {
    
    private Choice player1;
    private Choice player2;
    private Result result;

    public Round(){
    }
    
    public Round(Choice player1, Choice player2, Result result) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
    }

    /**
     * @return the player1
     */
    public Choice getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(Choice player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public Choice getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(Choice player2) {
        this.player2 = player2;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Round{" + "player1=" + player1 + ", player2=" + player2 + ", result=" + result + '}';
    }
    
}
