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
            private ApachePoiRow current;

            private RowIterator(Iterator<Row> it) {this.it = it;}

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public SourceRow next() {
                final Row next = it.next();
                if (current == null) {
                    return current = new ApachePoiRow(next);
                } else {
                    return current.with(next);
                }
            }
        }
    }

    private class ApachePoiRow implements SourceRow {

        private Row row;
        private ApachePoiCell cellTemplate;

        private ApachePoiRow(Row row) {this.row = row;}

        private ApachePoiRow with(Row row) {
            this.row = row;
            return this;
        }

        @Override
        public SourceCell cellAt(int index) {
            final Cell cell = row.getCell(index);
            if (cellTemplate == null) {
                return cellTemplate = new ApachePoiCell(cell);
            } else {
                return cellTemplate.with(cell);
            }
        }
    }

    private class ApachePoiCell implements SourceCell {

        private Cell cell;

        private ApachePoiCell(Cell cell) {this.cell = cell;}

        private ApachePoiCell with(Cell cell) {
            this.cell = cell;
            return this;
        }

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
