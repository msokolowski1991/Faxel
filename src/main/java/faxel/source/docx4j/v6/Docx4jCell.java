package faxel.source.docx4j.v6;

import faxel.source.SourceCell;
import org.xlsx4j.sml.Cell;

import java.util.Date;

class Docx4jCell implements SourceCell {

    private String stringValue;

    Docx4jCell(Cell cell) {
        this.stringValue = cell.getV();
    }

    @Override
    public String stringValue() {
        return stringValue;
    }

    @Override
    public double numericValue() {
        return Double.valueOf(stringValue);
    }

    @Override
    public boolean boolValue() {
        return Boolean.valueOf(stringValue);
    }

    @Override
    public Date dateValue() {
        // TODO find better way to create date
        return new Date(stringValue);
    }
}
