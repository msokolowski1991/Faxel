package faxel.source.docx4j.v6;

import faxel.FaxelException;
import faxel.source.SourceCell;
import faxel.source.SourceCells;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;

import java.util.List;

class Docx4jColumnCells implements SourceCells {

    private final List<Row> rows;
    private int columnIndex;
    //private Docx4jCell cellTemplate;

    Docx4jColumnCells(List<Row> rows, int columnIndex) {
        this.rows = rows;
        this.columnIndex = columnIndex;
    }

    Docx4jColumnCells with(int columnIndex) {
        this.columnIndex = columnIndex;
        return this;
    }

    @Override
    public SourceCell cellAt(int rowIndex) {
        if (rows.size() < rowIndex) {
            throw new FaxelException("Row at index %d does not exists", rowIndex);
        }
        final Row row = rows.get(rowIndex);
        if (row.getC().size() < columnIndex) {
            throw new FaxelException("Cell at index %d does not exists", columnIndex);
        }
        final Cell cell = row.getC().get(columnIndex);
        return new Docx4jCell(cell);
    }
}
