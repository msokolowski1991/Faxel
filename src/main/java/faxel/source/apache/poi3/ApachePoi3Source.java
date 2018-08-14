package faxel.source.apache.poi3;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import faxel.source.SourceExcel;
import faxel.source.SourceSheet;

/**
 * The Apache POI SourceExcel implementation.
 * To use this implementation you need to provide apache poi 3.x or later dependency.
 */
public class ApachePoi3Source implements SourceExcel {

    private final Workbook workbook;

    public ApachePoi3Source(Workbook workbook) {this.workbook = workbook;}

    @Override
    public SourceSheet sheetOf(String sheetName) {
        if (sheetName == null) {
            throw new IllegalArgumentException("SheetName can not be null");
        }
        final Sheet sheet = workbook.getSheet(sheetName);
        return new ApachePoiSheet(sheet);
    }

    @Override
    public SourceSheet sheetOf(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index should be >= 0");
        }

        final Sheet sheet = workbook.getSheetAt(index);
        return new ApachePoiSheet(sheet);
    }

}
