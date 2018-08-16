package faxel.source;

import java.util.Iterator;

import faxel.annotation.DataArrangementType;

/**
 * Abstraction over excel's sheet implementation
 */
public interface SourceSheet {

    /**
     * @return Iterator over rows
     */
    Iterator<SourceCells> rowsIterator();

    /**
     * @return Iterator over columns
     */
    Iterator<SourceCells> columnsIterator();

    default Iterator<SourceCells> cellsIterator(DataArrangementType arrangement) {
        switch (arrangement) {
            case ROW:
                return rowsIterator();
            case COLUMN:
                return columnsIterator();
            default:
                throw new IllegalArgumentException("Unknown data arrangement type");
        }
    }
}
