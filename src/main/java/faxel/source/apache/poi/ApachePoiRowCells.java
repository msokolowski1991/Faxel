package faxel.source.apache.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import faxel.FaxelException;
import faxel.source.SourceCell;
import faxel.source.SourceCells;

class ApachePoiRowCells implements SourceCells {

    private Row row;
    private ApachePoiCell cellTemplate;

    ApachePoiRowCells(Row row) {this.row = row;}

    ApachePoiRowCells with(Row row) {
        this.row = row;
        return this;
    }

    @Override
    public SourceCell cellAt(int columnIndex) {
        final Cell cell = row.getCell(columnIndex);
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