package faxel.model;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * ModelDefinition is usually created by ModelDefinitionFactory.
 * Using Metadata from destination type annotations, populates destination instance with workbook data
 * @param <DEST> Destination class type.
 */
public interface ModelDefinition<DEST> {
    /**
     * Fills destination class with given workbook data
     * @param workbook  source of data
     * @param destination destination
     * @return destination - the same as input one
     */
    DEST fill(Workbook workbook, DEST destination);
}
