package faxel.model;

import static java.util.stream.StreamSupport.stream;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.SheetRows;
import faxel.source.SourceExcel;
import faxel.source.SourceRow;
import faxel.source.SourceSheet;

final class SheetDefinition {
    private static Logger LOG = LoggerFactory.getLogger(SheetDefinition.class);

    private final SheetRows sheetRows;
    private final Field rowsCollectionField;
    private final Class<?> rowType;
    private final Collection<ColumnDefinition> columnDefinitions;

    SheetDefinition(SheetRows sheetRows, Field rowsCollectionField, Class<?> rowType, Collection<ColumnDefinition> columnDefinitions) {
        this.sheetRows = sheetRows;
        this.rowsCollectionField = rowsCollectionField;
        this.rowType = rowType;
        this.columnDefinitions = columnDefinitions;
    }

    void fill(SourceExcel source, Object destination) {
        LOG.trace("Filling Model {} from {} sheet", destination.getClass(), sheetRows.sheetName());

        final List<Object> rows = rowsStream(source).map(rowData -> {
            Object rowModel = ClassInitializer.createSilently(rowType);
            columnDefinitions.forEach(columnDefinition -> columnDefinition.fill(rowModel, rowData));
            return rowModel;
        }).collect(Collectors.toList());

        Try.silently(() -> rowsCollectionField.set(destination, rows));
    }

    private Stream<SourceRow> rowsStream(SourceExcel source) {
        final SourceSheet sheet = source.sheetOf(this.sheetRows);
        return stream(Spliterators.spliteratorUnknownSize(sheet.rowsIterator(), Spliterator.ORDERED), false).skip(sheetRows.firstDataRow() - 1);
    }
}
