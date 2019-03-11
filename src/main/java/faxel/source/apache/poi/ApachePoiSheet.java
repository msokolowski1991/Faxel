package faxel.source.apache.poi;

import faxel.source.SourceCells;
import faxel.source.SourceSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;

import static java.util.stream.StreamSupport.stream;

class ApachePoiSheet implements SourceSheet {

    private final Sheet sheet;

    ApachePoiSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public Iterator<SourceCells> rowsIterator() {
        return new RowIterator();
    }

    @Override
    public Iterator<SourceCells> columnsIterator() {
        return new ColumnIterator();
    }

    private class RowIterator implements Iterator<SourceCells> {

        private final Iterator<Row> it;

        private RowIterator() {
            this.it = sheet.rowIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public SourceCells next() {
            final Row next = it.next();
            return new ApachePoiRowCells(next);
        }
    }

    private class ColumnIterator implements Iterator<SourceCells> {

        private int currentCell = 0;
        private final int lastCell;

        private ColumnIterator() {
            this.lastCell = stream(Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED), false)
                    .max(Comparator.comparing(Row::getLastCellNum))
                    .map(r -> (int) r.getLastCellNum() - 1)
                    .orElse(-1);
        }

        @Override
        public boolean hasNext() {
            return currentCell <= lastCell;
        }

        @Override
        public SourceCells next() {
            return new ApachePoiColumnCells(sheet, currentCell++);
        }
    }
}
