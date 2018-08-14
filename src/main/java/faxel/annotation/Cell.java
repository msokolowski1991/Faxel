package faxel.annotation;

import java.lang.annotation.*;

import faxel.FaxelException;
import faxel.converter.ColumnConverter;
import faxel.source.SourceCell;

/**
 * Annotation used to mark a field of a class as column representation. <br>
 * Example usage:
 * <pre>
 * {@code @Cell(index = 0) }
 * {@code private String firstName;}
 * </pre>
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
            throw new FaxelException(
                    "Could not extract value from %s. Unknown Type. Please implement your own %s", cell, ColumnConverter.class.getName()
            );
        }
    }
}
