package kg.jarkyn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void hasDefaultDimension() {
        assertEquals(3, new Board().getDimension());
    }

    @Test
    public void knowsItsSize() {
        assertEquals(9, new Board().getSize());
    }

    @Test
    public void knowsAvailablePositionsWhenNoMovesMade() {
        Board board = new Board();
        ArrayList<Integer> expectedAvailable = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

        assertEquals(expectedAvailable, board.getAvailable());
    }

    @Test
    public void knowsAvailablePositionsWhenMovesWereMade() {
        ArrayList<Integer> expectedAvailable = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Board board = new Board().addMove(0, X);

        assertEquals(expectedAvailable, board.getAvailable());
    }

    @Test
    public void knowsMarkForGivenPosition() {
        Board board = new Board().addMove(0, X);

        assertEquals(X, board.markAt(0));
    }

    @Test
    public void knowsVacantMoveIsValid() {
        Board board = new Board();

        assertTrue(board.isValidMove(0));
    }

    @Test
    public void knowsOccupiedPositionIsNotValidMove() {
        Board board = new Board().addMove(0, X);

        assertFalse(board.isValidMove(0));
    }

    @Test
    public void knowsMoveOutsideTheBoardIsNotValid() {
        Board board = new Board();

        assertFalse(board.isValidMove(10));
    }

    @Test
    public void knowsWhenNotFull() {
        Board board = new Board();

        assertFalse(board.isFull());
    }

    @Test
    public void knowsWhenFull() {
        Mark[] moves = {X, O, X,
                        X, X, O,
                        O, O, X};
        Board board = new Board(moves);

        assertTrue(board.isFull());
    }

    @Test
    public void knowsWhenEmpty() {
        Board board = new Board();

        assertTrue(board.isEmpty());
    }

    @Test
    public void knowsWhenNotEmpty() {
        Mark[] moves = {   X, NONE, NONE,
                        NONE, NONE, NONE,
                        NONE, NONE, NONE};
        Board board = new Board(moves);

        assertFalse(board.isEmpty());
    }

    @Test
    public void returnsWinningRow() {
        Mark[] moves = { X,    X,    X,
                        NONE,  O,    O,
                        NONE, NONE, NONE};
        Board board = new Board(moves);
        int[] expectedWinLine = {0, 1, 2};

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsWinningColumn() {
        Mark[] moves = {X, NONE, NONE,
                        X,  O,    O,
                        X, NONE, NONE};
        Board board = new Board(moves);
        int[] expectedWinLine = {0, 3, 6};

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsWinningDiagonal() {
        Mark[] moves = {X,    O,    O,
                        NONE, X,    NONE,
                        NONE, NONE, X   };
        Board board = new Board(moves);
        int[] expectedWinLine = {0, 4, 8};

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsNonWinLine() {
        Mark[] moves = {X, O, O,
                        O, X, X,
                        O, X, O};
        Board board = new Board(moves);
        int[] expectedWinLine = new int[board.getDimension()];
        Arrays.fill(expectedWinLine, -1);

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsWinnerMark() {
        Mark[] moves = { X,    X,    X,
                        NONE,  O,    O,
                        NONE, NONE, NONE};
        Board board = new Board(moves);

        assertEquals(X, board.winnerMark());
    }

    @Test
    public void winIsFinalState() {
        Mark[] moves = {X,    X,    X,
                        NONE, O,    O,
                        NONE, NONE, NONE};
        Board board = new Board(moves);

        assertTrue(board.isFinalState());
    }

    @Test
    public void drawIsFinalState() {
        Mark[] moves = {X, O, O,
                        O, X, X,
                        O, X, O};
        Board board = new Board(moves);

        assertTrue(board.isFinalState());
    }
}