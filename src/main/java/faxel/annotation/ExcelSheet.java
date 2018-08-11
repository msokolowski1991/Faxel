package faxel.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to mark a Collection as ExcelSheet of Workbook.
 * Collection will be then populated with sheet rows.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelSheet {

    /**
     * Name of sheet - source of data of this Collection
     * @return Name of the sheet
     */
    String sheetName();

    /**
     * Indicates how to interpret data arrangement in this sheet.
     * Possible values are ROW or COLUMN
     * @return Desired data arrangement type. Default is ROW
     */
    DataArrangementType arrangement() default DataArrangementType.ROW;

    /**
     * Number of row or column to startIndex from parsing. Default is first row.
     * @return index of first row or column to startIndex processing from.
     */
    int startIndex() default 1;

    /**
     * Number of maxIndex positioned row or column to parse. Default is Integer.MAX_VALUE.
     * If the actual length of data set is greater than this value - it will be ignored.
     * @return index of last row or column to process.
     */
    int maxIndex() default Integer.MAX_VALUE;
}
