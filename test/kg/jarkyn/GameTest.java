package kg.jarkyn;

import kg.jarkyn.doubles.InputDouble;
import org.junit.Before;
import org.junit.Test;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.*;

public class GameTest {
    private HumanInput input;

    private Game setupGame() {
        return new Game(new Board(), new HumanPlayer(X, input), new HumanPlayer(O, input));
    }

    @Before
    public void setup() {
        input = new InputDouble(new int[]{});
    }

    @Test
    public void itIsNotOverAtTheStart() {
        Game game = setupGame();

        assertFalse(game.isOver());
    }

    @Test
    public void itIsOverWhenDrawn() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, X, O};
        Game game = new Game(new Board(moves), new HumanPlayer(X, input), new HumanPlayer(O, input));

        assertTrue(game.isOver());
    }

    @Test
    public void itIsOverWhenWon() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game;
        game = new Game(new Board(moves), new HumanPlayer(X, input), new HumanPlayer(O, input));

        assertTrue(game.isOver());
    }

    @Test
    public void noWinnerAtTheStart() {
        Game game = setupGame();

        assertEquals(NONE, game.winnerMark());
    }

    @Test
    public void knowsWinner() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Game game = new Game(new Board(moves), new HumanPlayer(X, input), new HumanPlayer(O, input));

        assertEquals(X, game.winnerMark());
    }

    @Test
    public void addsMoveToTheBoard() {
        input = new InputDouble(new int[]{1});
        Game game = new Game(new Board(), new HumanPlayer(X, input), new HumanPlayer(O, input));

        game.playTurn();

        assertEquals(X, game.getBoard().markAt(0));
    }

    @Test
    public void swapsPlayers() {
        input = new InputDouble(new int[]{1, 2});
        Game game = new Game(new Board(), new HumanPlayer(X, input), new HumanPlayer(O, input));

        game.playTurn();
        game.playTurn();

        assertNotEquals(game.getBoard().markAt(0), game.getBoard().markAt(1));
    }

    @Test
    public void setsCurrentPlayerCorrectlyWhenPlayerXHasMove() {
        Mark[] moves = new Mark[]{  X,  NONE, NONE,
                                  NONE, NONE, NONE,
                                  NONE, NONE, NONE};
        Board board = new Board(moves);
        Game game = new Game(board, new HumanPlayer(X, input), new AiPlayer(O));

        assertEquals(game.getPlayerO(), game.getCurrentPlayer());
    }

    @Test
    public void setsCurrentPlayerCorrectlyWhenBothPlayersHaveAMove() {
        Mark[] moves = new Mark[]{  X,    O,  NONE,
                                  NONE, NONE, NONE,
                                  NONE, NONE, NONE};
        Board board = new Board(moves);
        Game game = new Game(board, new HumanPlayer(X, input), new AiPlayer(O));

        assertEquals(game.getPlayerX(), game.getCurrentPlayer());
    }

    @Test
    public void doesNotPlayIfNoMove() {
        Player playerX = new HumanPlayerWithoutMoveStub(X);
        Game game = new Game(new Board(), playerX, new HumanPlayer(O, input));

        game.play();
        Board board = game.getBoard();

        assertTrue(board.isEmpty());
    }

    @Test
    public void playsTillWon() {
        HumanInput input = new InputDouble(new int[]{1, 4, 2, 5, 3});
        Game game = new Game(new Board(), new HumanPlayer(X, input), new HumanPlayer(O, input));

        game.play();

        assertTrue(game.isWon());
    }

    @Test
    public void playsTillDrawn() {
        HumanInput input = new InputDouble(new int[]{1, 2, 3, 5, 4, 6, 8, 7, 9});
        Game game = new Game(new Board(), new HumanPlayer(X, input), new HumanPlayer(O, input));

        game.play();

        assertTrue(isDrawn(game));
    }

    private boolean isDrawn(Game game) {
        return !game.isWon() && game.isOver();
    }

    private class HumanPlayerWithoutMoveStub extends Player {
        public HumanPlayerWithoutMoveStub(Mark mark) {
            super(mark);
        }

        @Override
        public int pickPosition(Board board) {
            return 0;
        }

        @Override
        public boolean hasNextMove() {
            return false;
        }
    }
}