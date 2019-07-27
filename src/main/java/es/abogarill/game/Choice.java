package es.abogarill.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represent any player choice in the game. Values: ROCK, PAPER, SCISSORS
 * @author abogarill
 */
public enum Choice {
    ROCK {
        @Override
        public boolean beats(Choice otherPlayer) {
            return otherPlayer == SCISSORS;
        }
    }, PAPER {
        @Override
        public boolean beats(Choice otherPlayer) {
            return otherPlayer == ROCK;
        }
    }, SCISSORS {
        @Override
        public boolean beats(Choice otherPlayer) {
            return otherPlayer == PAPER;
        }
    };
    
    /**
     * Each choice implements which other choice can beat.
     * @param otherPlayer the other player choice
     * @return if  {@code this} can beats {@code otherPlayer}
     */
    public abstract boolean beats(Choice otherPlayer);
    
    /**
     * A random choice is returned
     * @return random choice
     */
    public static Choice RANDOM(){
        List<Choice> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        return VALUES.get(new Random().nextInt(VALUES.size()));
    }
}
