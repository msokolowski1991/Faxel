package faxel.source.docx4j.v6;

import faxel.FaxelException;
import faxel.source.SourceCell;
import org.xlsx4j.model.CellUtils;
import org.xlsx4j.org.apache.poi.ss.usermodel.DataFormatter;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.STCellType;

import java.util.Date;

class Docx4jCell implements SourceCell {
    private static final DataFormatter dataFormatter = new DataFormatter();

    private final Cell cell;
    private final STCellType cellType;
    private String stringValue;

    Docx4jCell(Cell cell) {
        this.cell = cell;
        this.stringValue = cell.getV();
        this.cellType = cell.getT();
    }

    @Override
    public String stringValue() {
        if (stringValue == null) {
            return null;
        }
        if (cellType != STCellType.S) {
            throw new FaxelException("Illegal cell type %s. Could not get string value", getCellTypeName());
        }
        return dataFormatter.formatCellValue(cell);
    }

    @Override
    public double numericValue() {
        if (cellType != STCellType.N) {
            throw new FaxelException("Illegal cell type %s. Could not get numeric value", getCellTypeName());
        }
        if (stringValue == null) {
            return 0;
        }
        return CellUtils.getNumericCellValue(cell);
    }

    @Override
    public boolean boolValue() {
        if (cellType != STCellType.B && cellType != STCellType.N) {
            throw new FaxelException("Illegal cell type %s. Could not get boolean value", getCellTypeName());
        }
        return CellUtils.getBooleanCellValue(cell);
    }

    @Override
    public Date dateValue() {
        if (cellType != STCellType.N) {
            throw new FaxelException("Illegal cell type %s. Could not get date value", getCellTypeName());
        }
        if (stringValue == null) {
            return null;
        }
        return CellUtils.getDateCellValue(cell);
    }

    private String getCellTypeName() {
        int cellType = CellUtils.getCellType(cell);
        switch (cellType) {
            case CellUtils.CELL_TYPE_NUMERIC:
                return "numeric";
            case CellUtils.CELL_TYPE_STRING:
                return "string";
            case CellUtils.CELL_TYPE_BOOLEAN:
                return "boolean";
            case CellUtils.CELL_TYPE_BLANK:
                return "blank";
            case CellUtils.CELL_TYPE_ERROR:
                return "error";
            case CellUtils.CELL_TYPE_FORMULA:
                return "formula";
            default:
                return "unknown";
        }
    }
}
