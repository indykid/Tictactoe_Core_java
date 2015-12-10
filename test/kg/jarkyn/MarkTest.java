package kg.jarkyn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkTest {
    @Test
    public void opponentOfXIsO() {
        assertEquals(Mark.O, Mark.X.opponent());
    }

    @Test
    public void opponentOfOIsX() {
        assertEquals(Mark.X, Mark.O.opponent());
    }

    @Test
    public void parsesXToMark() {
        assertEquals(Mark.X, Mark.toMark("X"));
    }

    @Test
    public void parsesOToMark() {
        assertEquals(Mark.O, Mark.toMark("O"));
    }

    @Test
    public void parsesNONEToMark() {
        assertEquals(Mark.NONE, Mark.toMark("NONE"));
    }

    @Test
    public void parsesToNONEByDefault() {
        assertEquals(Mark.NONE, Mark.toMark("a"));
    }
}
