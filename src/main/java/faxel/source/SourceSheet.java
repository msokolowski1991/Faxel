package faxel.source;

import java.util.Iterator;

public interface SourceSheet {
    Iterator<SourceCells> rowsIterator();
    Iterator<SourceCells> columnsIterator();
}
