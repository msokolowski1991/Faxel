package faxel.source.apache.poi;

import faxel.FaxelException;
import faxel.source.SourceCell;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

class ApachePoiCell implements SourceCell {

    private Cell cell;
    private int cellTypeCode;

    ApachePoiCell(Cell cell) {
        setupCell(cell);
    }

    ApachePoiCell with(Cell cell) {
        setupCell(cell);
        return this;
    }

    private void setupCell(Cell cell) {
        this.cell = cell;
        this.cellTypeCode = cell.getCellType();
    }

    @Override
    public String stringValue() {
        if (isCellOneOf(CELL_TYPE_STRING, CELL_TYPE_BLANK, CELL_TYPE_FORMULA)) {
            return cell.getStringCellValue();
        } else {
            throw new FaxelException("Illegal cell type %s. Could not get string value from cell", getCellTypeName());
        }
    }

    @Override
    public double numericValue() {
        if (isCellOneOf(CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_ERROR)) {
            return cell.getNumericCellValue();
        } else {
            throw new FaxelException("Illegal cell type %s. Could not get numeric value", getCellTypeName());
        }
    }

    @Override
    public boolean boolValue() {
        if (isCellOneOf(CELL_TYPE_BOOLEAN, CELL_TYPE_BLANK)) {
            return cell.getBooleanCellValue();
        } else {
            throw new FaxelException("Illegal cell type %s. Could not get boolean value", getCellTypeName());
        }
    }

    @Override
    public Date dateValue() {
        if (isCellOneOf(CELL_TYPE_NUMERIC, CELL_TYPE_BLANK)) {
            return cell.getDateCellValue();
        } else {
            throw new FaxelException("Illegal cell type %s. Could not get date value", getCellTypeName());
        }
    }

    private boolean isCellOneOf(int... types) {
        for (int type : types) {
            if (type == cellTypeCode) {
                return true;
            }
        }
        return false;
    }

    private String getCellTypeName() {
        switch (cellTypeCode) {
            case CELL_TYPE_BLANK:
                return "blank";
            case CELL_TYPE_STRING:
                return "text";
            case CELL_TYPE_BOOLEAN:
                return "boolean";
            case CELL_TYPE_ERROR:
                return "error";
            case CELL_TYPE_NUMERIC:
                return "numeric";
            case CELL_TYPE_FORMULA:
                return "formula";
        }
        return "Unknown cell type " + cellTypeCode;
    }
}
