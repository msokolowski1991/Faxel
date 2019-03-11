package faxel.annotation;

/**
 * Determine the way data is arranged in a sheet. Used on collection field of rows/columns in destination bean.<br>
 * Example usage:
 * <pre>
 * {@code @ExcelSheet(sheetName = "Person"arrangement = DataArrangementType.ROW) }
 * {@code private Collection<Person> people;}
 * </pre>
 */
public enum DataArrangementType {
    /**
     * Data is arranged in rows.
     * Each rows represents a single set of data
     */
    ROW,
    /**
     * Data is arranged in columns.
     * Each column represents a single set of data
     */
    COLUMN
}
