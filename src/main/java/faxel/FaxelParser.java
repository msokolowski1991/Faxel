package faxel;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Parses Workbook to user provided destination class
 * @param <D> User provided destination class
 */
public interface FaxelParser<D> {
    /**
     * Parses data from given workbook to new instance of D
     * @param workbook Source workbook. Should match destination metadata.
     * @return
     */
    D parseFrom(Workbook workbook);
}
