package faxel.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to mark a Collection as SheetRows of Workbook.
 * Collection will be then populated with sheet rows.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface SheetRows {
    String sheetName();
    int firstDataRow() default 1;
}
