package faxel.definition;

import java.lang.reflect.Field;
import java.util.Collection;

import faxel.annotation.Sheet;

final class SheetDefinition {
    private final Sheet sheet;
    private final Field field;
    private final Class<?> rowType;
    private final Collection<ColumnDefinition> columnDefinitions;

    SheetDefinition(Sheet sheet, Field field, Class<?> rowType, Collection<ColumnDefinition> columnDefinitions) {
        this.sheet = sheet;
        this.field = field;
        this.rowType = rowType;
        this.columnDefinitions = columnDefinitions;
    }
}
