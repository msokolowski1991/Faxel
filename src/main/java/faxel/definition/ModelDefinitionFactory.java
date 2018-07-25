package faxel.definition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

import faxel.annotation.Column;
import faxel.annotation.Sheet;

public class ModelDefinitionFactory {

    public static ModelDefinitionFactory get() {
        return new ModelDefinitionFactory();
    }

    public <T> ModelDefinition<T> create(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class argument can not be null");
        }
        final List<SheetDefinition> sheetDefinitions = Arrays.stream(clazz.getDeclaredFields())
                .peek(sheetField -> sheetField.setAccessible(true))
                .map(this::tryToCreateSheetDefinition)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new ModelDefinitionImpl<>(sheetDefinitions);
    }

    private SheetDefinition tryToCreateSheetDefinition(Field sheetField) {
        final Sheet sheetAnnotation = sheetField.getAnnotation(Sheet.class);
        if (sheetAnnotation != null) {
            final Class<?> fieldType = sheetField.getType();
            if (fieldType.isAssignableFrom(Collection.class)) {
                final Class<?> collectionType = (Class<?>) ((ParameterizedType) sheetField.getGenericType()).getActualTypeArguments()[0];
                final List<ColumnDefinition> columnDefinitions = Arrays.stream(collectionType.getDeclaredFields())
                        .peek(columnField -> columnField.setAccessible(true))
                        .map(this::tryToCreateColumnDefinition)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                return new SheetDefinition(sheetAnnotation, sheetField, columnDefinitions);
            } else {
                throw new IllegalArgumentException("Sheet annotation should be placed on Collection");
            }
        }
        return null;
    }

    private ColumnDefinition tryToCreateColumnDefinition(Field columnField) {
        final Column columnAnnotation = columnField.getAnnotation(Column.class);
        if (columnAnnotation != null) {
            return new ColumnDefinition(columnAnnotation, columnField);
        } else {
            return null;
        }
    }

}
