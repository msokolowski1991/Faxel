package faxel.model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.Cell;
import faxel.converter.ColumnConverter;
import faxel.source.SourceCell;
import faxel.source.SourceCells;

abstract class CellDefinition {
    private static Logger LOG = LoggerFactory.getLogger(CellDefinition.class);

    private final Cell cellMetadata;
    private final Field modelFieldDefinition;

    static CellDefinition create(Cell cell, Field modelFieldDefinition) {
        final Class<?> columnType = modelFieldDefinition.getType();
        if (columnType.isAssignableFrom(String.class)) {
            return new StringCellDefinition(cell, modelFieldDefinition);
        } else if (Integer.TYPE == columnType || columnType.isAssignableFrom(Integer.class)) {
            return new IntegerCellDefinition(cell, modelFieldDefinition);
        } else if (Long.TYPE == columnType || columnType.isAssignableFrom(Long.class)) {
            return new LongCellDefinition(cell, modelFieldDefinition);
        } else if (Short.TYPE == columnType || columnType.isAssignableFrom(Short.class)) {
            return new ShortCellDefinition(cell, modelFieldDefinition);
        } else if (Double.TYPE == columnType || columnType.isAssignableFrom(Double.class)) {
            return new DoubleCellDefinition(cell, modelFieldDefinition);
        } else if (Float.TYPE == columnType || columnType.isAssignableFrom(Float.class)) {
            return new FloatCellDefinition(cell, modelFieldDefinition);
        } else if (Boolean.TYPE == columnType || columnType.isAssignableFrom(Boolean.class)) {
            return new BooleanCellDefinition(cell, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(LocalDate.class)) {
            return new LocalDateCellDefinition(cell, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(LocalDateTime.class)) {
            return new LocalDateTimeCellDefinition(cell, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(LocalTime.class)) {
            return new LocalTimeCellDefinition(cell, modelFieldDefinition);
        } else if (columnType.isAssignableFrom(Date.class)) {
            return new DateCellDefinition(cell, modelFieldDefinition);
        } else {
            final Class<? extends ColumnConverter> customConverterType = cell.converter();
            return new CustomCellDefinition(cell, modelFieldDefinition, ClassInitializer.createSilently(customConverterType));
        }
    }

    private CellDefinition(Cell cellMetadata, Field modelFieldDefinition) {
        this.cellMetadata = cellMetadata;
        this.modelFieldDefinition = modelFieldDefinition;
    }

    void fill(Object SourceRowModel, SourceCells sourceCellsData) {
        LOG.trace("Filling {} with column {}", SourceRowModel, cellMetadata);
        final SourceCell cell = sourceCellsData.cellAt(cellMetadata.index());
        final Object value = getValue(cell);
        Try.silently(() -> modelFieldDefinition.set(SourceRowModel, value));
    }

    protected abstract Object getValue(SourceCell cell);

    // ---------------------------------------------------------------------------

    static final class StringCellDefinition extends CellDefinition {

        StringCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return cell.stringValue();
        }
    }

    static final class DoubleCellDefinition extends CellDefinition {

        DoubleCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return cell.numericValue();
        }
    }

    static final class IntegerCellDefinition extends CellDefinition {

        IntegerCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return (int) cell.numericValue();
        }
    }

    static final class FloatCellDefinition extends CellDefinition {

        FloatCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return (float) cell.numericValue();
        }
    }

    static final class LongCellDefinition extends CellDefinition {

        LongCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return (long) cell.numericValue();
        }
    }

    static final class ShortCellDefinition extends CellDefinition {

        ShortCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return (short) cell.numericValue();
        }
    }

    static final class BooleanCellDefinition extends CellDefinition {

        BooleanCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return cell.boolValue();
        }
    }

    static final class LocalDateCellDefinition extends CellDefinition {

        LocalDateCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            final Date value = cell.dateValue();
            return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    static final class LocalDateTimeCellDefinition extends CellDefinition {

        LocalDateTimeCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            final Date value = cell.dateValue();
            return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    static final class LocalTimeCellDefinition extends CellDefinition {

        LocalTimeCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            final Date value = cell.dateValue();
            return value.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        }
    }

    static final class DateCellDefinition extends CellDefinition {

        DateCellDefinition(Cell cell, Field modelFieldDefinition) {
            super(cell, modelFieldDefinition);
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return cell.dateValue();
        }
    }

    static final class CustomCellDefinition extends CellDefinition {

        private final ColumnConverter<?> customConverter;

        CustomCellDefinition(Cell cell, Field modelFieldDefinition, ColumnConverter<?> customConverter) {
            super(cell, modelFieldDefinition);
            this.customConverter = customConverter;
        }

        @Override
        protected Object getValue(SourceCell cell) {
            return customConverter.convert(cell);
        }
    }
}
