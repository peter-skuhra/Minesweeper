package minesweeper.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    static final int ROWS = 24;
    static final int COLUMNS = 30;
    static final int MINES = (ROWS - 1) * (COLUMNS - 1);

    @Test
    public void isSolved() {
        Field field = new Field(ROWS, COLUMNS, MINES);

        assertEquals(GameState.PLAYING, field.getState());

        int open = 0;
        for (int row = 0; row < field.getRowCount(); ++row) {
            for (int column = 0; column < field.getColumnCount(); ++column) {
                if (field.getTile(row, column) instanceof Clue) {
                    field.openTile(row, column);
                    open++;
                }

                if (field.getRowCount() * field.getColumnCount() - open ==
                        field.getMineCount()) {
                    assertEquals(GameState.SOLVED, field.getState());
                }
                else {
                    assertNotSame(GameState.FAILED, field.getState());
                }
            }
        }

        assertEquals(GameState.SOLVED, field.getState());
    }

    @Test
    public void generate() {
        Field field = new Field(ROWS, COLUMNS, MINES);

        assertEquals(ROWS, field.getRowCount());
        assertEquals(COLUMNS, field.getColumnCount());
        assertEquals(MINES, field.getMineCount());

        int mineCount = 0;
        int clueCount = 0;
        for (int i = 0; i < field.getRowCount(); ++i) {
            for (int j = 0; j < field.getColumnCount(); ++j) {
                assertNotNull(field.getTile(i, j));

                if (field.getTile(i, j) instanceof Mine) {
                    ++mineCount;
                }

                if (field.getTile(i, j) instanceof Clue) {
                    ++clueCount;
                }
            }
        }

        assertEquals(MINES, mineCount);
        assertEquals(ROWS * COLUMNS - MINES, clueCount);
    }
}