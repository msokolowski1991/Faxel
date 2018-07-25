package faxel.definition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;

import faxel.annotation.Column;
import faxel.annotation.Sheet;

public class ModelDefinitionFactory {

    public static ModelDefinitionFactory get() {
        return new ModelDefinitionFactory();
    }

    public <T> ModelDefinition<T> create(Class<T> clazz) {
        final List<SheetDefinition> sheetDefinitions = Arrays.stream(clazz.getDeclaredFields())
                .peek(sheetField -> sheetField.setAccessible(true))
                .map(sheetField -> {
                    final Sheet sheetAnnotation = sheetField.getAnnotation(Sheet.class);
                    if (sheetAnnotation != null) {
                        final Class<?> fieldType = sheetField.getType();
                        if (fieldType.isAssignableFrom(Collection.class)) {
                            final Class<?> collectionType = (Class<?>) ((ParameterizedType) sheetField.getGenericType()).getActualTypeArguments()[0];
                            final List<ColumnDefinition> columnDefinitions = Arrays.stream(collectionType.getDeclaredFields())
                                    .peek(columnField -> columnField.setAccessible(true))
                                    .map(columnField -> {
                                        final Column columnAnnotation = columnField.getAnnotation(Column.class);
                                        if (columnAnnotation != null) {
                                            return new ColumnDefinition(columnAnnotation, columnField);
                                        } else {
                                            return null;
                                        }
                                    }).filter(Objects::nonNull).collect(Collectors.toList());
                            return new SheetDefinition(sheetAnnotation, sheetField, columnDefinitions);
                        } else {
                            throw new IllegalArgumentException("Sheet annotation should be placed on Collection");
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new ModelDefinitionImpl<>(sheetDefinitions);
    }

    final class ModelDefinitionImpl<DEST> implements ModelDefinition<DEST> {

        private final Collection<SheetDefinition> sheetDefinitions;

        ModelDefinitionImpl(Collection<SheetDefinition> sheetDefinitions) {
            this.sheetDefinitions = sheetDefinitions;
        }

        @Override
        public DEST fill(Workbook workbook, DEST destination) {
            return destination;
        }
    }

    final class SheetDefinition {
        private final faxel.annotation.Sheet sheet;
        private final Field containingField;
        private final Collection<ColumnDefinition> columnDefinitions;

        SheetDefinition(Sheet sheet, Field containingField, Collection<ColumnDefinition> columnDefinitions) {
            this.sheet = sheet;
            this.containingField = containingField;
            this.columnDefinitions = columnDefinitions;
        }
    }

    final class ColumnDefinition {
        private final faxel.annotation.Column column;
        private final Field containingField;

        ColumnDefinition(Column column, Field containingField) {
            this.column = column;
            this.containingField = containingField;
        }
    }
}
