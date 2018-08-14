package faxel.model;

import faxel.source.SourceExcel;

/**
 * ModelDefinition is usually created by ModelDefinitionFactory.
 * Represents Metadata about Destination class type. <br>
 * Example usage:
 * <pre>
 * {@code
 *  // Create model from factory
 *  ModelDefinition definition = ModelDefinitionFactory.get().create(PersonDataExcel.class);
 *
 *  // Create source
 *  InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
 *  SourceExcel source = SourceFactory.get().create(excelStream);
 *
 *  // Fill the model using definition
 *  definition.fill(source, new PersonDataExcel());
 *  }
 * </pre>
 * @param <DEST> Destination class type.
 */
public interface ModelDefinition<DEST> {
    /**
     * Fills destination class with given workbook data
     * @param source  source of data
     * @param destination destination
     * @return destination - the same as input one
     */
    DEST fill(SourceExcel source, DEST destination);
}
