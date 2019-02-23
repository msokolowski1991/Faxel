package faxel.source;

/**
 * Abstraction over collection of cells in row or column
 */
public interface SourceCells {

    /**
     * @param index index of cell
     * @return cell at given index
     */
    SourceCell cellAt(int index);
}
