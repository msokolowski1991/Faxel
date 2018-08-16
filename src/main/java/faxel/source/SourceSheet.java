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

    /**
     * Determine SourceCell iterator based on given {@link DataArrangementType}.
     * @param arrangement a data arrangement type, must not be null.
     * @return rows or columns based {@link SourceCells} iterator.
     */
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
