package es.abogarill.game;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import es.abogarill.game.serializer.ResultSerializer;

/**
 * Represent the result of the game. Values: PLAYER1_WIN, PLAYER2_WIN, DRAW.
 * @author abogarill
 */
@JsonSerialize(using = ResultSerializer.class)
public enum Result {
    PLAYER1_WIN, PLAYER2_WIN, DRAW;
}
