package faxel.source.apache.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import faxel.FaxelException;
import faxel.source.SourceCell;
import faxel.source.SourceCells;

class ApachePoiColumnCells implements SourceCells {

    private final Sheet sheet;
    private int columnIndex;

    ApachePoiColumnCells(Sheet sheet, int columnIndex) {
        this.sheet = sheet;
        this.columnIndex = columnIndex;
    }

    @Override
    public SourceCell cellAt(int rowIndex) {
        final Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
        if (cell == null) {
            throw new FaxelException("Cell at index %d does not exists", columnIndex);
        }
        return new ApachePoiCell(cell);
    }
}
