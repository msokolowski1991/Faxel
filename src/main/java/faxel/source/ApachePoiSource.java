package faxel.source;

import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import faxel.annotation.SheetRows;

class ApachePoiSource implements SourceExcel {

    private final Workbook workbook;

    ApachePoiSource(Workbook workbook) {this.workbook = workbook;}

    @Override
    public SourceSheet sheetOf(SheetRows rows) {
        final Sheet sheet = workbook.getSheet(rows.sheetName());
        return new ApachePoiSheet(sheet);
    }

    private class ApachePoiSheet implements SourceSheet {

        private final Sheet sheet;

        private ApachePoiSheet(Sheet sheet) {this.sheet = sheet;}

        @Override
        public Iterator<SourceRow> rowsIterator() {
            return new RowIterator(sheet.rowIterator());
        }

        private class RowIterator implements Iterator<SourceRow> {

            private final Iterator<Row> it;

            private RowIterator(Iterator<Row> it) {this.it = it;}

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public SourceRow next() {
                // CONSIDER reusing ApachePoiRow
                return new ApachePoiRow(it.next());
            }
        }
    }

    private class ApachePoiRow implements SourceRow {

        private final Row row;

        private ApachePoiRow(Row row) {this.row = row;}

        @Override
        public SourceCell cellAt(int index) {
            return new ApachePoiCell(row.getCell(index));
        }
    }

    private class ApachePoiCell implements SourceCell {

        private final Cell cell;

        private ApachePoiCell(Cell cell) {this.cell = cell;}

        @Override
        public String stringValue() {
            return cell.getStringCellValue();
        }

        @Override
        public double numericValue() {
            return cell.getNumericCellValue();
        }

        @Override
        public boolean boolValue() {
            return cell.getBooleanCellValue();
        }

        @Override
        public Date dateValue() {
            return cell.getDateCellValue();
        }
    }

}
