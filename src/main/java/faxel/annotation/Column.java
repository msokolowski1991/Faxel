package faxel.annotation;

import java.lang.annotation.*;

import org.apache.poi.ss.usermodel.Cell;

import faxel.converter.ColumnConverter;

/**
 * Annotation used to mark a field of a class as column representation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Column {

    /**
     * @return index of column in row
     */
    int index();

    /**
     * Custom value converter class.
     * Implementing own Converter might help you extract values to your domain specific objects.
     * @return Class<? extends ColumnConverter>
     */
    Class<? extends ColumnConverter> converter() default ThrowingConverter.class;

    class ThrowingConverter implements ColumnConverter<Object> {

        @Override
        public Object convert(Cell cell) {
            throw new IllegalArgumentException(
                    String.format("Could not extract value from %s. Unknown Type. Try to implement own Converter", cell)
            );
        }
    }
}
