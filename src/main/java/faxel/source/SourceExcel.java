package faxel.source;

import faxel.FaxelException;
import faxel.annotation.ExcelSheet;

/**
 * Abstraction over excel implementation
 */
public interface SourceExcel {

    /**
     * @param sheetName name of the excel sheet
     * @return Sheet of given name
     */
    SourceSheet sheetOf(String sheetName);

    /**
     * @param index 0 based index of the excel sheet
     * @return Sheet of given index
     */
    SourceSheet sheetOf(int index);

    /**
     * Determines {@link SourceSheet} from {@link ExcelSheet} metadata.
     * Requires either {@link ExcelSheet#name()} or {@link ExcelSheet#index()}.
     * If none of them is present, throws FaxelException.
     * @param sheetMetadata {@link ExcelSheet} object
     * @return {@link SourceSheet} determined from given {@link ExcelSheet} metadata.
     */
    default SourceSheet sheetOf(ExcelSheet sheetMetadata) {
        boolean hasName = !"".equals(sheetMetadata.name());
        boolean hasIndex = -1 != sheetMetadata.index();
        if (hasName) {
            return sheetOf(sheetMetadata.name());
        } else if (hasIndex) {
            return sheetOf(sheetMetadata.index());
        } else {
            throw new FaxelException("ExcelSheet must have name or index argument provided.");
        }
    }
}
