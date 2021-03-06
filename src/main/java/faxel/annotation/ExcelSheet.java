package faxel.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to mark a Collection as ExcelSheet of Workbook.
 * Collection will be then populated with sheet rows.
 * One of  {@link #name() name} or {@link #index() index}  must be provided.<br>
 * Example usage:
 * <pre>
 * {@code @ExcelSheet(sheetName = "Person", startPosition = 1, maxPosition = 100, arrangement = DataArrangementType.ROW)}
 * {@code private Collection<Person> people;}
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelSheet {

    /**
     * Name of sheet - source of data of this Collection
     *
     * @return Name of the sheet
     */
    String name() default "";

    /**
     * 0 based index of sheet - source of data of this Collection
     *
     * @return Index of the sheet
     */
    int index() default -1;

    /**
     * Indicates how to interpret data arrangement in this sheet.
     * Possible values are ROW or COLUMN
     *
     * @return Desired data arrangement type. Default is ROW
     */
    DataArrangementType arrangement() default DataArrangementType.ROW;

    /**
     * 0 based index of row or column to startPosition from parsing. Default is first row.
     *
     * @return index of first row or column to start processing from.
     */
    int startPosition() default 1;

    /**
     * 0 based index of maxPosition positioned row or column to parse. Default is Integer.MAX_VALUE.
     * If the actual length of data set is greater than this value then maxPosition will be ignored.
     *
     * @return index of last row or column to process.
     */
    int maxPosition() default Integer.MAX_VALUE;

}
