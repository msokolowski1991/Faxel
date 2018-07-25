package faxel.definition;

import org.apache.poi.ss.usermodel.Workbook;

public interface ModelDefinition<DEST> {
    DEST fill(Workbook workbook, DEST destination);
}
