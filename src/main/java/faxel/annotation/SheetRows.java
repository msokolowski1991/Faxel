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

    /**
     * Name of sheet - source of data of this Collection
     */
    String sheetName();

    /**
     * Number of row to start from parsing. Default is first row.
     */
    int firstDataRow() default 1;
}
