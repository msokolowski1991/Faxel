package faxel.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.FaxelException;
import faxel.annotation.Cell;
import faxel.annotation.ExcelSheet;

/**
 * ModelDefinition objects Factory. <br>
 * Example usage:
 * <pre>
 * ModelDefinitionFactory factory = ModelDefinitionFactory.get()
 * ModelDefinition model = factory.create(PersonDataExcel.class);
 * </pre>
 */
public final class ModelDefinitionFactory {
    private static Logger LOG = LoggerFactory.getLogger(ModelDefinitionFactory.class);

    public static ModelDefinitionFactory get() {
        return new ModelDefinitionFactory();
    }

    private ModelDefinitionFactory() {}

    /**
     * Creates ModelDefinition from Class object. <br>
     * All collections annotated as {@link ExcelSheet} will be matched to excel sheets.  <br>
     * Which field within above collection generic type annotated as {@link Cell} will be matched to excel column by index.
     * @param clazz The class of model destination object.
     * @param <T> Type of destination object
     * @return Created ModelDefinition for given class
     */
    public <T> ModelDefinition<T> create(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class argument can not be null");
        }
        final List<SheetDefinition> sheetDefinitions = Arrays.stream(clazz.getDeclaredFields())
                .peek(sheetField -> sheetField.setAccessible(true))
                .map(this::tryToCreateSheetDefinition)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (sheetDefinitions.isEmpty()) {
            LOG.warn("Did not found any sheet definition. Did you forgot to annotate @ExcelSheet?");
        }
        return new DefaultModelDefinition<>(sheetDefinitions);
    }

    private SheetDefinition tryToCreateSheetDefinition(Field sheetFieldCandidate) {
        if (sheetFieldCandidate.isAnnotationPresent(ExcelSheet.class)) {
            final ExcelSheet sheetMetadata = sheetFieldCandidate.getAnnotation(ExcelSheet.class);
            final Class<?> fieldType = sheetFieldCandidate.getType();
            if (fieldType.isAssignableFrom(Collection.class)) {
                return createSheetDefinition(sheetFieldCandidate, sheetMetadata);
            } else {
                throw new FaxelException("Error on %s field. ExcelSheet annotation must be placed on Collection!", sheetFieldCandidate.getName());
            }
        } else {
            LOG.trace("Field {} is not recognized as sheet field. Skipping", sheetFieldCandidate);
            return null;
        }
    }

    private SheetDefinition createSheetDefinition(Field sheetField, ExcelSheet sheetMetadata) {
        final Class<?> rowType = (Class<?>) ((ParameterizedType) sheetField.getGenericType()).getActualTypeArguments()[0];
        final List<CellDefinition> cellDefinitions = Arrays.stream(rowType.getDeclaredFields())
                .peek(cellFieldCandidateField -> cellFieldCandidateField.setAccessible(true))
                .map(this::tryToCreateCellDefinition)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (cellDefinitions.isEmpty()) {
            LOG.warn("Did not found any column definition in {}. Did you forgot to annotate @Cell?", sheetMetadata);
        }
        return new SheetDefinition(sheetMetadata, sheetField, rowType, cellDefinitions);
    }

    private CellDefinition tryToCreateCellDefinition(Field cellFieldCandidate) {
        if (cellFieldCandidate.isAnnotationPresent(Cell.class)) {
            return createCellDefinition(cellFieldCandidate);
        } else {
            LOG.trace("Field {} is not recognized as column field. Skipping", cellFieldCandidate);
            return null;
        }
    }

    private CellDefinition createCellDefinition(Field columnField) {
        final Cell cellAnnotation = columnField.getAnnotation(Cell.class);
        return CellDefinition.create(cellAnnotation, columnField);
    }

}
