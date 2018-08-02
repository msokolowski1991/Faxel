package faxel.source;

import faxel.annotation.SheetRows;

public interface SourceExcel {
    SourceSheet sheetOf(SheetRows rows);
}
