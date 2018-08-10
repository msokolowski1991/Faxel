package faxel.source;

import java.util.Iterator;

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
}
