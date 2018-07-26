package faxel.definition;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.Column;

final class ColumnDefinition {
    private static Logger LOG = LoggerFactory.getLogger(ColumnDefinition.class);

    private final Column column;
    private final Field modelFieldDefinition;

    ColumnDefinition(Column column, Field modelFieldDefinition) {
        this.column = column;
        this.modelFieldDefinition = modelFieldDefinition;
    }

    void fill(Object rowModel, Row rowData) {
        LOG.trace("Filling {} with column {}", rowModel, column);
        final Cell cell = rowData.getCell(column.index());
        Try.silently(() -> modelFieldDefinition.set(rowModel, cell.getStringCellValue()));
    }
}
