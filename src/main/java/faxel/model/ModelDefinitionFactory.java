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
public class ModelDefinitionFactory {
    private static Logger LOG = LoggerFactory.getLogger(ModelDefinitionFactory.class);

    public static ModelDefinitionFactory get() {
        return new ModelDefinitionFactory();
    }

    /**
     * Creates ModelDefinition from Class object.
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
        return new DefaultModelDefinition<>(sheetDefinitions);
    }

    private SheetDefinition tryToCreateSheetDefinition(Field sheetField) {
        if (sheetField.isAnnotationPresent(SheetRows.class)) {
            final SheetRows sheetRowsAnnotation = sheetField.getAnnotation(SheetRows.class);
            final Class<?> fieldType = sheetField.getType();
            if (fieldType.isAssignableFrom(Collection.class)) {
                final Class<?> rowType = (Class<?>) ((ParameterizedType) sheetField.getGenericType()).getActualTypeArguments()[0];
                final List<ColumnDefinition> columnDefinitions = Arrays.stream(rowType.getDeclaredFields())
                        .peek(columnField -> columnField.setAccessible(true))
                        .map(this::tryToCreateColumnDefinition)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                return new SheetDefinition(sheetRowsAnnotation, sheetField, rowType, columnDefinitions);
            } else {
                throw new IllegalArgumentException("SheetRows annotation should be placed on Collection");
            }
        } else {
            LOG.trace("Field {} is not recognized as sheet field. Skipping", sheetField);
            return null;
        }
    }

    private ColumnDefinition tryToCreateColumnDefinition(Field columnField) {
        if (columnField.isAnnotationPresent(Column.class)) {
            final Column columnAnnotation = columnField.getAnnotation(Column.class);
            return ColumnDefinition.create(columnAnnotation, columnField);
        } else {
            LOG.trace("Field {} is not recognized as column field. Skipping", columnField);
            return null;
        }
    }

}
