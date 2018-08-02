package faxel.test.converters;

import java.math.BigDecimal;

import faxel.converter.ColumnConverter;
import faxel.source.SourceCell;

public class CustomBigDecimalConverter implements ColumnConverter<BigDecimal> {
    @Override
    public BigDecimal convert(SourceCell cell) {
        final double numericCellValue = cell.numericValue();
        return BigDecimal.valueOf(numericCellValue);
    }
}
