package faxel.source.docx4j.v6;

import faxel.FaxelException;
import faxel.source.SourceCell;
import org.xlsx4j.org.apache.poi.ss.usermodel.DateUtil;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.STCellType;

import java.util.Date;

class Docx4jCell implements SourceCell {

    private final Cell cell;
    private final STCellType cellType;
    private String stringValue;

    Docx4jCell(Cell cell) {
        this.cell = cell;
        this.stringValue = cell.getV();
    }

    @Override
    public String stringValue() {
        if (cellType != STCellType.S) {
            throw new FaxelException("Illegal cell type %s. Could not get string value", cellType.value());
        }
        return stringValue;
    }

    @Override
    public double numericValue() {
        if (cellType != STCellType.N) {
            throw new FaxelException("Illegal cell type %s. Could not get numeric value", cellType.value());
        }
        if (stringValue == null) {
            return 0;
        }
        return Double.valueOf(stringValue);
    }

    @Override
    public boolean boolValue() {
        if (cellType != STCellType.B && cellType != STCellType.N) {
            throw new FaxelException("Illegal cell type %s. Could not get boolean value", cellType.value());
        }
        return "1".equals(stringValue) || Boolean.valueOf(stringValue);
    }

    @Override
    public Date dateValue() {
        if (cellType != STCellType.N) {
            throw new FaxelException("Illegal cell type %s. Could not get date value", cellType.value());
        }
        if (stringValue == null) {
            return null;
        }
        return DateUtil.getJavaDate(Double.valueOf(stringValue));
    }
}
