package faxel.model;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.Column;

abstract class ColumnDefinition {
    private static Logger LOG = LoggerFactory.getLogger(ColumnDefinition.class);

    private final Column column;
    private final Field modelFieldDefinition;

    static ColumnDefinition create(Column column, Field modelFieldDefinition) {
        final Class<?> columnType = modelFieldDefinition.getType();
        if (columnType.isAssignableFrom(String.class)) {
            return new StringColumnDefinition(column, modelFieldDefinition);
        } else if (Double.TYPE == columnType || columnType.isAssignableFrom(Double.class)) {
            return new DoubleColumnDefinition(column, modelFieldDefinition);
        } else if (Integer.TYPE == columnType || columnType.isAssignableFrom(Integer.class)) {
            return new IntegerColumnDefinition(column, modelFieldDefinition);
        } else {
            LOG.error("Unsupported type {}", columnType);
            throw new UnsupportedOperationException("Unsupported Type");
        }
    }

    private ColumnDefinition(Column column, Field modelFieldDefinition) {
        this.column = column;
        this.modelFieldDefinition = modelFieldDefinition;
    }

    void fill(Object rowModel, Row rowData) {
        LOG.trace("Filling {} with column {}", rowModel, column);
        final Cell cell = rowData.getCell(column.index());
        Try.silently(() -> modelFieldDefinition.set(rowModel, getValue(cell)));
    }

    protected abstract Object getValue(Cell cell);

    static final class StringColumnDefinition extends ColumnDefinition {

        StringColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return cell.getStringCellValue();
        }
    }

    static final class DoubleColumnDefinition extends ColumnDefinition {

        DoubleColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return cell.getNumericCellValue();
        }
    }

    static final class IntegerColumnDefinition extends ColumnDefinition {

        IntegerColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return (int) cell.getNumericCellValue();
        }
    }
}
