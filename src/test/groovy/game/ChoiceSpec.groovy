package game

import spock.lang.*
import static es.abogarill.game.Choice.PAPER
import static es.abogarill.game.Choice.SCISSORS
import static es.abogarill.game.Choice.ROCK

/**
 *
 * @author abogarill
 */
class ChoiceSpec extends Specification {

    def "Who beats who"() {
        expect:
        choice1.beats(choice2) == result

        where:
        choice1 | choice2 || result
        //true
        ROCK | SCISSORS || true
        PAPER | ROCK || true
        SCISSORS | PAPER || true
        //false
        ROCK | PAPER || false        
        PAPER | SCISSORS || false
        SCISSORS | ROCK || false     
    }
  
}
