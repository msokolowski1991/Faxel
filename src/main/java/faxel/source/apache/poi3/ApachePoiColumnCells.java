package faxel.source.apache.poi3;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import faxel.FaxelException;
import faxel.source.SourceCell;
import faxel.source.SourceCells;

class ApachePoiColumnCells implements SourceCells {

    private final Sheet sheet;
    private int columnIndex;
    private ApachePoiCell cellTemplate;

    ApachePoiColumnCells(Sheet sheet, int columnIndex) {
        this.sheet = sheet;
        this.columnIndex = columnIndex;
    }

    ApachePoiColumnCells with(int columnIndex) {
        this.columnIndex = columnIndex;
        return this;
    }

    @Override
    public SourceCell cellAt(int rowIndex) {
        final Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
        if (cell == null) {
            throw new FaxelException("Cell at %d index does not exists", columnIndex);
        }
        if (cellTemplate == null) {
            return cellTemplate = new ApachePoiCell(cell);
        } else {
            return cellTemplate.with(cell);
        }
    }
}
