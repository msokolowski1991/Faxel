package faxel.source;

import java.util.Iterator;

public interface SourceSheet {
    Iterator<SourceRow> rowsIterator();
}
