package faxel.source;

/**
 * Abstraction over excel implementation
 */
public interface SourceExcel {

    /**
     * @param sheetName name of the excel sheet
     * @return Sheet of given name
     */
    SourceSheet sheetOf(String sheetName);
}
