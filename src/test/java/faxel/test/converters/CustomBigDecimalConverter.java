package faxel.test.converters;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;

import faxel.converter.ColumnConverter;

public class CustomBigDecimalConverter implements ColumnConverter<BigDecimal> {
    @Override
    public BigDecimal convert(Cell cell) {
        final double numericCellValue = cell.getNumericCellValue();
        return BigDecimal.valueOf(numericCellValue);
    }
}
