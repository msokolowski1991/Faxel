package faxel.model;

import static java.util.stream.StreamSupport.stream;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.FaxelException;
import faxel.annotation.ExcelSheet;
import faxel.source.SourceCells;
import faxel.source.SourceExcel;
import faxel.source.SourceSheet;

final class SheetDefinition {
    private static Logger LOG = LoggerFactory.getLogger(SheetDefinition.class);

    private final ExcelSheet sheetMetadata;
    private final Field dataCollectionField;
    private final Class<?> dataClass;
    private final Collection<CellDefinition> cellDefinitions;

    SheetDefinition(ExcelSheet sheetMetadata, Field dataCollectionField, Class<?> dataClass, Collection<CellDefinition> cellDefinitions) {
        assertMetadata(sheetMetadata);
        this.sheetMetadata = sheetMetadata;
        this.dataCollectionField = dataCollectionField;
        this.dataClass = dataClass;
        this.cellDefinitions = cellDefinitions;
    }

    private void assertMetadata(ExcelSheet sheetMetadata) {
        if (sheetMetadata.startPosition() > sheetMetadata.maxPosition()) {
            throw new FaxelException(
                    "Could not create SheetDefinition of %s ExcelSheet.startPosition can not be greater than ExcelSheet.maxPosition", sheetMetadata
            );
        }
    }

    void fill(SourceExcel source, Object destination) {
        LOG.trace("Filling Model {} from {} sheet", destination.getClass(), sheetMetadata);

        final Collection<Object> rows = cellsStream(source).map(rowData -> {
            Object model = ClassInitializer.createSilently(dataClass);
            cellDefinitions.forEach(cellDefinition -> cellDefinition.fill(model, rowData));
            return model;
        }).collect(Collectors.toCollection(this::supplySheetModelCollection));

        Try.onFailureThrowRuntimeException(
                () -> dataCollectionField.set(destination, rows),
                "Could not set value of one of %s fields: %s", sheetMetadata, dataCollectionField.getName()
        );
    }

    @SuppressWarnings("unchecked")
    private <C extends Collection<?>> C supplySheetModelCollection() {
        final Class<?> modelCollectionClass = dataCollectionField.getType();
        if (modelCollectionClass.isInterface()) {
            if (modelCollectionClass.isAssignableFrom(List.class)) {
                return (C) new ArrayList<>();
            } else if (modelCollectionClass.isAssignableFrom(Set.class)) {
                return (C) new HashSet<>();
            } else if (modelCollectionClass.isAssignableFrom(Collection.class)) {
                return (C) new ArrayList<>();
            } else {
                LOG.error("FATAL: Can not create model collection instance of class {}", modelCollectionClass);
                throw new FaxelException("Can not create model collection instance of class %s", modelCollectionClass.getName());
            }
        } else {
            final Object desiredCollection = ClassInitializer.createSilently(modelCollectionClass);
            return (C) desiredCollection;
        }
    }

    private Stream<SourceCells> cellsStream(SourceExcel source) {
        final SourceSheet sheet = source.sheetOf(this.sheetMetadata);
        final int firstPosition = sheetMetadata.startPosition() - 1;
        final int numberOfRowsToParse = sheetMetadata.maxPosition() - sheetMetadata.startPosition() + 1;
        final Iterator<SourceCells> cellsIterator = sheet.cellsIterator(sheetMetadata.arrangement());
        return stream(Spliterators.spliteratorUnknownSize(cellsIterator, Spliterator.ORDERED), false)
                .skip(firstPosition)
                .limit(numberOfRowsToParse);
    }
}
