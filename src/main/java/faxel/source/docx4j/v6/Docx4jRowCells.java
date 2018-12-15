package faxel.source.docx4j.v6;

import faxel.FaxelException;
import faxel.source.SourceCell;
import faxel.source.SourceCells;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;

class Docx4jRowCells implements SourceCells {

    private Row row;

    Docx4jRowCells(Row row) {
        this.row = row;
    }

    @Override
    public SourceCell cellAt(int columnIndex) {
        if (columnIndex >= row.getC().size()) {
            throw new FaxelException("Cell at %d index does not exists", columnIndex);
        }
        final Cell cell = row.getC().get(columnIndex);
        return new Docx4jCell(cell);
    }
}
