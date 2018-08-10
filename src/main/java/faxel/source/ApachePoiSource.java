package faxel.source;

import static java.util.stream.StreamSupport.stream;

import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

class ApachePoiSource implements SourceExcel {

    private final Workbook workbook;

    ApachePoiSource(Workbook workbook) {this.workbook = workbook;}

    @Override
    public SourceSheet sheetOf(String sheetName) {
        final Sheet sheet = workbook.getSheet(sheetName);
        return new ApachePoiSheet(sheet);
    }

    private class ApachePoiSheet implements SourceSheet {

        private final Sheet sheet;

        private ApachePoiSheet(Sheet sheet) {this.sheet = sheet;}

        @Override
        public Iterator<SourceCells> rowsIterator() {
            return new RowIterator(sheet);
        }

        @Override
        public Iterator<SourceCells> columnsIterator() {
            return new ColumnIterator(sheet);
        }

        private class RowIterator implements Iterator<SourceCells> {

            private final Iterator<Row> it;
            private ApachePoiRowCells current;

            private RowIterator(Sheet sheet) {this.it = sheet.rowIterator();}

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public SourceCells next() {
                final Row next = it.next();
                if (current == null) {
                    return current = new ApachePoiRowCells(next);
                } else {
                    return current.with(next);
                }
            }
        }

        private class ColumnIterator implements Iterator<SourceCells> {

            private int currentCell = 0;
            private final int lastCell;
            private final Sheet sheet;
            private ApachePoiColumnCells current;

            private ColumnIterator(Sheet sheet) {
                this.sheet = sheet;
                this.lastCell = stream(Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED), false)
                        .max(Comparator.comparing(Row::getLastCellNum))
                        .map(r -> (int) r.getLastCellNum()).orElse(0);
            }

            @Override
            public boolean hasNext() {
                return currentCell < lastCell;
            }

            @Override
            public SourceCells next() {
                if (current == null) {
                    return current = new ApachePoiColumnCells(sheet, currentCell++);
                } else {
                    return current.with(currentCell++);
                }
            }
        }
    }

    private class ApachePoiRowCells implements SourceCells {

        private Row row;
        private ApachePoiCell cellTemplate;

        private ApachePoiRowCells(Row row) {this.row = row;}

        private ApachePoiRowCells with(Row row) {
            this.row = row;
            return this;
        }

        @Override
        public SourceCell cellAt(int columnIndex) {
            final Cell cell = row.getCell(columnIndex);
            if (cellTemplate == null) {
                return cellTemplate = new ApachePoiCell(cell);
            } else {
                return cellTemplate.with(cell);
            }
        }
    }

    private class ApachePoiColumnCells implements SourceCells {

        private final Sheet sheet;
        private int columnIndex;
        private ApachePoiCell cellTemplate;

        private ApachePoiColumnCells(Sheet sheet, int columnIndex) {
            this.sheet = sheet;
            this.columnIndex = columnIndex;
        }

        private ApachePoiColumnCells with(int columnIndex) {
            this.columnIndex = columnIndex;
            return this;
        }

        @Override
        public SourceCell cellAt(int rowIndex) {
            final Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
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
