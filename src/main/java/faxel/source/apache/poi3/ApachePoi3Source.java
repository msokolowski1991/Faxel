package faxel.source.apache.poi3;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import faxel.FaxelException;
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
            throw new FaxelException("SheetName can not be null");
        }
        final Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new FaxelException("Sheet %s does not exist", sheetName);
        }
        return new ApachePoiSheet(sheet);
    }

    @Override
    public SourceSheet sheetOf(int index) {
        if (index < 0) {
            throw new FaxelException("Index should be >= 0");
        }
        if (index >= workbook.getNumberOfSheets()) {
            throw new FaxelException("Sheet at %d index does not exist", index);
        }
        final Sheet sheet = workbook.getSheetAt(index);
        return new ApachePoiSheet(sheet);
    }

}
