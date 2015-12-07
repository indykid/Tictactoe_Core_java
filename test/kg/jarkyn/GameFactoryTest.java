package kg.jarkyn;

import kg.jarkyn.doubles.InputDouble;
import org.junit.Before;
import org.junit.Test;

import static kg.jarkyn.Mark.*;
import static kg.jarkyn.Mark.NONE;
import static org.junit.Assert.assertTrue;

public class GameFactoryTest {
    private HumanInput input;
    private Game game;

    @Before
    public void setUp() throws Exception {
        input = new InputDouble(new int[]{});
    }

    private void setupGame(GameOption aiFirst, HumanInput input) {
        game = GameFactory.makeGame(new Board(), aiFirst, input);
    }

    @Test
    public void givenAiFirstOptionAiIsPlayerXHumanIsPlayerO() {
        setupGame(GameOption.AI_FIRST, input);

        assertTrue(game.getPlayerX() instanceof AiPlayer);
        assertTrue(game.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenAiSecondOptionHumanAsPlayerXAiAsPlayerO() {
        setupGame(GameOption.AI_SECOND, input);

        assertTrue(game.getPlayerX() instanceof HumanPlayer);
        assertTrue(game.getPlayerO() instanceof AiPlayer);
    }

    @Test
    public void givenHumanOnlyOptionOnlyHumanPlayers() {
        setupGame(GameOption.HUMAN_ONLY, input);

        assertTrue(game.getPlayerX() instanceof HumanPlayer);
        assertTrue(game.getPlayerO() instanceof HumanPlayer);
    }

    @Test
    public void givenAiOnlyOptionOnlyAiPlayers() {
        setupGame(GameOption.AI_ONLY, input);

        assertTrue(game.getPlayerX() instanceof AiPlayer);
        assertTrue(game.getPlayerO() instanceof AiPlayer);
    }

    @Test
    public void makesGameWithGivenBoard() {
        Mark[] moves = new Mark[]{  X,  NONE, NONE,
                                  NONE, NONE, NONE,
                                  NONE, NONE, NONE};
        Board board = new Board(moves);
        assertTrue(GameFactory.makeGame(board, GameOption.AI_FIRST, input) instanceof Game);
    }
}