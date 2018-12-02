package faxel.source.docx4j.v6;

import faxel.source.SourceCells;
import faxel.source.SourceSheet;
import org.apache.commons.lang3.NotImplementedException;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.xlsx4j.sml.Row;
import org.xlsx4j.sml.SheetData;

import java.util.Iterator;

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
        throw new NotImplementedException("Not yet implemented");
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

}
