package faxel.definition;

import java.lang.reflect.Field;

import faxel.annotation.Column;

final class ColumnDefinition {
    private final faxel.annotation.Column column;
    private final Field containingField;

    ColumnDefinition(Column column, Field containingField) {
        this.column = column;
        this.containingField = containingField;
    }
}
