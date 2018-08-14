package faxel.source.apache.poi3;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

import faxel.source.SourceCell;

class ApachePoiCell implements SourceCell {

    private Cell cell;

    ApachePoiCell(Cell cell) {this.cell = cell;}

    ApachePoiCell with(Cell cell) {
        this.cell = cell;
        return this;
    }

    @Override
    public String stringValue() {
        if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return cell.getStringCellValue();
        } else {
            return null;
        }
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
