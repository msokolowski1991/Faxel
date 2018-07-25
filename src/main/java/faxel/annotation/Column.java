package faxel.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to mark a field of a class as an column representation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Column {
    int index();
}
