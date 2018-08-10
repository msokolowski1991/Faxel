package faxel.source.poi;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import faxel.source.SourceExcel;
import faxel.source.SourceSheet;

public class ApachePoiSource implements SourceExcel {

    private final Workbook workbook;

    public ApachePoiSource(Workbook workbook) {this.workbook = workbook;}

    @Override
    public SourceSheet sheetOf(String sheetName) {
        if (sheetName == null) {
            throw new IllegalArgumentException("SheetName can not be null");
        }
        final Sheet sheet = workbook.getSheet(sheetName);
        return new ApachePoiSheet(sheet);
    }

}
