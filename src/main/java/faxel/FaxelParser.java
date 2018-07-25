package faxel;

import org.apache.poi.ss.usermodel.Workbook;

public interface FaxelParser<D> {
    D parseFrom(Workbook workbook);
}
