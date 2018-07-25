package faxel.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to mark a Collection as Sheet of Workbook.
 * Collection will be then populated with sheet rows.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Sheet {
    String name();
    int firstDataRow() default 1;
}
