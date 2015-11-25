package kg.jarkyn;

import kg.jarkyn.doubles.InputDouble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameMakerTest {
    private HumanInput input;

    @Before
    public void setUp() throws Exception {
        input = new InputDouble(new int[]{});
    }

    @Test
    public void givenAiFirstOptionAiIsPlayerXHumanIsPlayerO() {
        GameMaker.makeGame(GameOption.AI_FIRST, input);

        assertTrue(GameMaker.getPlayerX() instanceof AiPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenAiSecondOptionHumanAsPlayerXAiAsPlayerO() {
        GameMaker.makeGame(GameOption.AI_SECOND, input);

        assertTrue(GameMaker.getPlayerX() instanceof HumanPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof AiPlayer);
    }

    @Test
    public void givenHumanOnlyOptionOnlyHumanPlayers() {
        GameMaker.makeGame(GameOption.HUMAN_ONLY, input);

        assertTrue(GameMaker.getPlayerX() instanceof HumanPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenAiOnlyOptionOnlyAiPlayers() {
        GameMaker.makeGame(GameOption.AI_ONLY, input);

        assertTrue(GameMaker.getPlayerX() instanceof AiPlayer);
        assertTrue(GameMaker.getPlayerO() instanceof AiPlayer);
    }
}