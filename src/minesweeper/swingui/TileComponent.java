package minesweeper.swingui;

import minesweeper.core.Clue;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Tile GUI component.
 */
public class TileComponent extends JLabel {

    /**
     * Icons.
     */
    private static final ImageIcon MINE_ICON =
            new ImageIcon(TileComponent.class.getResource("/img/mine.gif"));
    private static final ImageIcon MARK_ICON =
            new ImageIcon(TileComponent.class.getResource("/img/mark.gif"));

    /**
     * Colors of clue labels.
     */
    private static final Color foregroundColors[] =
            {
                    Color.BLUE,
                    Color.RED,
                    Color.GREEN,
                    Color.MAGENTA,
                    Color.ORANGE,
                    Color.CYAN,
                    Color.PINK,
                    Color.YELLOW
            };

    /**
     * Number of row in the field.
     */
    private final int row;

    /**
     * Number of column in the field.
     */
    private final int column;

    /**
     * Tile in the field which represents the GUI component.
     */
    private final Tile tile;

    /**
     * Constructor.
     *
     * @param tile   tile
     * @param row    row
     * @param column column
     */
    public TileComponent(Tile tile, int row, int column) {
        this.tile = tile;
        this.row = row;
        this.column = column;

        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(
                BevelBorder.RAISED));
        setPreferredSize(new Dimension(30, 30));
        setFont(new Font("Dialog", Font.BOLD, 11));
    }

    /**
     * Returns row number of a tile.
     *
     * @return row number of a tile
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns column number of a tile.
     *
     * @return column number of a tile
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns associated tile.
     *
     * @return associated tile
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Updates style of the component according to tile.
     */
    public void updateStyle() {
        switch (tile.getState()) {
            case OPEN:
                setBorder(BorderFactory.createBevelBorder(
                        BevelBorder.LOWERED));

                if (tile instanceof Clue) {
                    Clue clue = (Clue) tile;
                    if (clue.getValue() > 0) {
                        setForeground(foregroundColors[clue.getValue() - 1]);
                        setText(String.valueOf(clue.getValue()));
                    }
                }
                else if (tile instanceof Mine) {
                    setBackground(Color.RED);
                    setIcon(MINE_ICON);
                }
                break;

            case MARKED:
                setIcon(MARK_ICON);
                break;

            case CLOSED:
                setIcon(null);
                break;
        }
    }

    /**
     * Updates playing field after victory.
     */
    public void updateSolvedStyle() {
        if (tile instanceof Mine) {
            switch (tile.getState()) {
                case MARKED:
                    setBackground(Color.GREEN);
                    break;

                case CLOSED:
                    setBackground(Color.ORANGE);
                    break;
            }
        }
    }

    /**
     * Updates playing field after defeat.
     */
    public void updateFailedStyle() {
        if (tile instanceof Mine) {
            switch (tile.getState()) {
                case MARKED:
                    setBackground(Color.GREEN);
                    break;

                case CLOSED:
                    setBorder(BorderFactory.createBevelBorder(
                            BevelBorder.LOWERED));
                    setBackground(Color.ORANGE);
                    setIcon(MINE_ICON);
                    break;
            }
        }
    }

}
