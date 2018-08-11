package faxel.model;

import static java.lang.String.format;
import static java.util.stream.StreamSupport.stream;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.annotation.ExcelSheet;
import faxel.source.SourceCells;
import faxel.source.SourceExcel;
import faxel.source.SourceSheet;

final class SheetDefinition {
    private static Logger LOG = LoggerFactory.getLogger(SheetDefinition.class);

    private final ExcelSheet sheetMetadata;
    private final Field modelsCollection;
    private final Class<?> rowType;
    private final Collection<CellDefinition> cellDefinitions;

    SheetDefinition(ExcelSheet sheetMetadata, Field modelsCollection, Class<?> rowType, Collection<CellDefinition> cellDefinitions) {
        assertMetadata(sheetMetadata);
        this.sheetMetadata = sheetMetadata;
        this.modelsCollection = modelsCollection;
        this.rowType = rowType;
        this.cellDefinitions = cellDefinitions;
    }

    private void assertMetadata(ExcelSheet sheetMetadata) {
        if (sheetMetadata.startPosition() > sheetMetadata.maxPosition()) {
            throw new IllegalArgumentException(
                    format("Could not create SheetDefinition of %s ExcelSheet.startPosition can not be greater than ExcelSheet.maxPosition", sheetMetadata)
            );
        }
    }

    void fill(SourceExcel source, Object destination) {
        LOG.trace("Filling Model {} from {} sheet", destination.getClass(), sheetMetadata.name());

        final List<Object> rows = cellsStream(source).map(rowData -> {
            Object model = ClassInitializer.createSilently(rowType);
            cellDefinitions.forEach(cellDefinition -> cellDefinition.fill(model, rowData));
            return model;
        }).collect(Collectors.toList());

        Try.silently(() -> modelsCollection.set(destination, rows));
    }

    private Stream<SourceCells> cellsStream(SourceExcel source) {
        final SourceSheet sheet = sourceSheetFrom(source);
        final int firstPosition = sheetMetadata.startPosition() - 1;
        final int numberOfRowsToParse = sheetMetadata.maxPosition() - sheetMetadata.startPosition() + 1;
        final Iterator<SourceCells> cellsIterator = cellsIterator(sheet);
        return stream(Spliterators.spliteratorUnknownSize(cellsIterator, Spliterator.ORDERED), false)
                .skip(firstPosition)
                .limit(numberOfRowsToParse);
    }

    private SourceSheet sourceSheetFrom(SourceExcel source) {
        boolean hasName = !"".equals(this.sheetMetadata.name());
        boolean hasIndex = -1 != this.sheetMetadata.index();
        if (hasName) {
            return source.sheetOf(this.sheetMetadata.name());
        } else if (hasIndex) {
            return source.sheetOf(this.sheetMetadata.index());
        } else {
            throw new IllegalStateException("ExcelSheet must have name or index argument provided.");
        }
    }

    private Iterator<SourceCells> cellsIterator(SourceSheet sheet) {
        switch (sheetMetadata.arrangement()) {
            case ROW:
                return sheet.rowsIterator();
            case COLUMN:
                return sheet.columnsIterator();
            default:
                throw new IllegalArgumentException("Unknown data arrangement type");
        }
    }
}
