package faxel.annotation;

import java.lang.annotation.*;

import faxel.converter.ColumnConverter;
import faxel.source.SourceCell;

/**
 * Annotation used to mark a field of a class as column representation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Cell {

    /**
     * @return 0 based index of column or row
     */
    int index();

    /**
     * Custom value converter class.
     * Implementing own Converter might help you extract values to your domain specific objects.
     * @return ColumnConverter class
     */
    Class<? extends ColumnConverter> converter() default ThrowingConverter.class;

    class ThrowingConverter implements ColumnConverter<Object> {

        @Override
        public Object convert(SourceCell cell) {
            throw new IllegalArgumentException(
                    String.format("Could not extract value from %s. Unknown Type. Try to implement own Converter", cell)
            );
        }
    }
}
