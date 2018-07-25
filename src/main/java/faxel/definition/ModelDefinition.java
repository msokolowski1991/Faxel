package faxel.definition;

import org.apache.poi.ss.usermodel.Workbook;

public interface ModelDefinition<T> {
    T fillFrom(Workbook workbook);
}
