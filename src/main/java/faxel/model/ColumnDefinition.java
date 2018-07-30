package faxel.model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.Column;
import faxel.converter.ColumnConverter;

abstract class ColumnDefinition {
    private static Logger LOG = LoggerFactory.getLogger(ColumnDefinition.class);

    private final Column column;
    private final Field modelFieldDefinition;

    static ColumnDefinition create(Column column, Field modelFieldDefinition) {
        final Class<?> columnType = modelFieldDefinition.getType();
        if (columnType.isAssignableFrom(String.class)) {
            return new StringColumnDefinition(column, modelFieldDefinition);
        } else if (Integer.TYPE == columnType || columnType.isAssignableFrom(Integer.class)) {
            return new IntegerColumnDefinition(column, modelFieldDefinition);
        } else if (Long.TYPE == columnType || columnType.isAssignableFrom(Long.class)) {
            return new LongColumnDefinition(column, modelFieldDefinition);
        } else if (Short.TYPE == columnType || columnType.isAssignableFrom(Short.class)) {
            return new ShortColumnDefinition(column, modelFieldDefinition);
        } else if (Double.TYPE == columnType || columnType.isAssignableFrom(Double.class)) {
            return new DoubleColumnDefinition(column, modelFieldDefinition);
        } else if (Float.TYPE == columnType || columnType.isAssignableFrom(Float.class)) {
            return new FloatColumnDefinition(column, modelFieldDefinition);
        } else if (Boolean.TYPE == columnType || columnType.isAssignableFrom(Boolean.class)) {
            return new BooleanColumnDefinition(column, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(LocalDate.class)) {
            return new LocalDateColumnDefinition(column, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(LocalDateTime.class)) {
            return new LocalDateTimeColumnDefinition(column, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(LocalTime.class)) {
            return new LocalTimeColumnDefinition(column, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(Date.class)) {
            return new DateColumnDefinition(column, modelFieldDefinition);
        } else {
            final Class<? extends ColumnConverter> customConverterType = column.converter();
            return new CustomColumnDefinition(column, modelFieldDefinition, ClassInitializer.createSilently(customConverterType));
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

    // ---------------------------------------------------------------------------

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

    static final class FloatColumnDefinition extends ColumnDefinition {

        FloatColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return (float) cell.getNumericCellValue();
        }
    }

    static final class LongColumnDefinition extends ColumnDefinition {

        LongColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return (long) cell.getNumericCellValue();
        }
    }

    static final class ShortColumnDefinition extends ColumnDefinition {

        ShortColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return (short) cell.getNumericCellValue();
        }
    }

    static final class BooleanColumnDefinition extends ColumnDefinition {

        BooleanColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return cell.getBooleanCellValue();
        }
    }

    static final class LocalDateColumnDefinition extends ColumnDefinition {

        LocalDateColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            final Date value = cell.getDateCellValue();
            return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    static final class LocalDateTimeColumnDefinition extends ColumnDefinition {

        LocalDateTimeColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            final Date value = cell.getDateCellValue();
            return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    static final class LocalTimeColumnDefinition extends ColumnDefinition {

        LocalTimeColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            final Date value = cell.getDateCellValue();
            return value.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        }
    }

    static final class DateColumnDefinition extends ColumnDefinition {

        DateColumnDefinition(Column column, Field modelFieldDefinition) {
            super(column, modelFieldDefinition);
        }

        @Override
        protected Object getValue(Cell cell) {
            return cell.getDateCellValue();
        }
    }

    static final class CustomColumnDefinition extends ColumnDefinition {

        private final ColumnConverter<?> customConverter;

        CustomColumnDefinition(Column column, Field modelFieldDefinition, ColumnConverter<?> customConverter) {
            super(column, modelFieldDefinition);
            this.customConverter = customConverter;
        }

        @Override
        protected Object getValue(Cell cell) {
            return customConverter.convert(cell);
        }
    }
}
