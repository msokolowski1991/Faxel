package faxel.definition;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import faxel.annotation.Column;
import faxel.annotation.Sheet;

public class ModelDefinitionFactory {

    public static ModelDefinitionFactory get() {
        return new ModelDefinitionFactory();
    }

    public <T> ModelDefinition<T> create(Class<T> clazz) {
        Arrays.stream(clazz.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .peek(field -> {
                    final Sheet sheetAnnotation = field.getAnnotation(Sheet.class);
                    if (sheetAnnotation != null) {
                        final Class<?> fieldType = field.getType();
                        if (fieldType.isAssignableFrom(Collection.class)) {
                            final Class<?> collectionType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                            Arrays.stream(collectionType.getDeclaredFields())
                                    .peek(field2 -> field2.setAccessible(true))
                                    .peek(field2 -> {
                                        final Column columnAnnotation = field.getAnnotation(Column.class);

                                    }).collect(Collectors.toList());
                        } else {
                            throw new IllegalArgumentException("Sheet annotation should be placed on Collection");
                        }
                    }
                }).collect(Collectors.toList());

        return workbook -> ClassInitializer.createSilently(clazz);
    }
}
