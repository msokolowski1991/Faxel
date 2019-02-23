package faxel.source.docx4j.v6;

import faxel.source.SourceCells;
import faxel.source.SourceSheet;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.xlsx4j.sml.Row;
import org.xlsx4j.sml.SheetData;

import java.util.Iterator;
import java.util.List;

class Docx4jSheet implements SourceSheet {
    private final WorksheetPart workSheet;

    Docx4jSheet(WorksheetPart worksheet) {
        this.workSheet = worksheet;
    }

    @Override
    public Iterator<SourceCells> rowsIterator() {
        SheetData sheetData = workSheet.getJaxbElement().getSheetData();
        return new RowIterator(sheetData.getRow());
    }

    @Override
    public Iterator<SourceCells> columnsIterator() {
        SheetData sheetData = workSheet.getJaxbElement().getSheetData();
        return new ColumnIterator(sheetData.getRow());
    }

    private class RowIterator implements Iterator<SourceCells> {

        private final Iterator<Row> iterator;

        RowIterator(Iterable<Row> rows) {
            this.iterator = rows.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public SourceCells next() {
            final Row nextRow = iterator.next();
            return new Docx4jRowCells(nextRow);
        }
    }

    private class ColumnIterator implements Iterator<SourceCells> {

        private final List<Row> rows;
        private int currentColumn = 0;
        private final int lastColumn;

        ColumnIterator(List<Row> rows) {
            this.rows = rows;
            this.lastColumn = rows.isEmpty() ? 0 : rows.get(0).getC().size();
        }

        @Override
        public boolean hasNext() {
            return currentColumn < lastColumn;
        }

        @Override
        public SourceCells next() {
            return new Docx4jColumnCells(rows, currentColumn++);
        }
    }

}
