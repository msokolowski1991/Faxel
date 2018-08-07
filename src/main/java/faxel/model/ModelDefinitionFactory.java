package faxel.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.Column;
import faxel.annotation.SheetRows;

/**
 * ModelDefinition objects Factory
 */
public final class ModelDefinitionFactory {
    private static Logger LOG = LoggerFactory.getLogger(ModelDefinitionFactory.class);

    public static ModelDefinitionFactory get() {
        return new ModelDefinitionFactory();
    }

    private ModelDefinitionFactory() {}

    /**
     * Creates ModelDefinition from Class object. <br>
     * All collections annotated as {@link faxel.annotation.SheetRows} will be matched to excel sheets.  <br>
     * Which field within above collection generic type annotated as {@link faxel.annotation.Column} will be matched to excel column by index.
     * @param clazz The class of model destination object.
     * @param <T> Type of destination object
     * @return Created ModelDefinition for given class
     */
    public <T> ModelDefinition<T> create(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class argument can not be null");
        }
        final List<SheetDefinition> sheetDefinitions = Arrays.stream(clazz.getDeclaredFields())
                .peek(sheetField -> sheetField.setAccessible(true))
                .map(this::tryToCreateSheetDefinition)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (sheetDefinitions.isEmpty()) {
            LOG.warn("Did not found any sheet definition. Did you forgot to annotate @SheetRows?");
        }
        return new DefaultModelDefinition<>(sheetDefinitions);
    }

    private SheetDefinition tryToCreateSheetDefinition(Field sheetFieldCandidate) {
        if (sheetFieldCandidate.isAnnotationPresent(SheetRows.class)) {
            final SheetRows sheetMetadata = sheetFieldCandidate.getAnnotation(SheetRows.class);
            final Class<?> fieldType = sheetFieldCandidate.getType();
            if (fieldType.isAssignableFrom(Collection.class)) {
                return createSheetDefinition(sheetFieldCandidate, sheetMetadata);
            } else {
                throw new IllegalArgumentException("SheetRows annotation must be placed on Collection!");
            }
        } else {
            LOG.trace("Field {} is not recognized as sheet field. Skipping", sheetFieldCandidate);
            return null;
        }
    }

    private SheetDefinition createSheetDefinition(Field sheetField, SheetRows sheetMetadata) {
        final Class<?> rowType = (Class<?>) ((ParameterizedType) sheetField.getGenericType()).getActualTypeArguments()[0];
        final List<ColumnDefinition> columnDefinitions = Arrays.stream(rowType.getDeclaredFields())
                .peek(columnField -> columnField.setAccessible(true))
                .map(this::tryToCreateColumnDefinition)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (columnDefinitions.isEmpty()) {
            LOG.warn("Did not found any column definition in {}. Did you forgot to annotate @Column?", sheetMetadata);
        }
        return new SheetDefinition(sheetMetadata, sheetField, rowType, columnDefinitions);
    }

    private ColumnDefinition tryToCreateColumnDefinition(Field columnFieldCandidate) {
        if (columnFieldCandidate.isAnnotationPresent(Column.class)) {
            return createColumnDefinition(columnFieldCandidate);
        } else {
            LOG.trace("Field {} is not recognized as column field. Skipping", columnFieldCandidate);
            return null;
        }
    }

    private ColumnDefinition createColumnDefinition(Field columnField) {
        final Column columnAnnotation = columnField.getAnnotation(Column.class);
        return ColumnDefinition.create(columnAnnotation, columnField);
    }

}
