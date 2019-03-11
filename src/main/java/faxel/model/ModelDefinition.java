package faxel.model;

import faxel.source.SourceExcel;

/**
 * ModelDefinition is usually created by {@link ModelDefinitionFactory}. <br>
 * Represents Metadata about Destination class type and knows how to fill destination bean with data from the {@link SourceExcel} <br>
 * Example usage:
 * <pre>
 *  // Create model from factory
 *  ModelDefinition definition = ModelDefinitionFactory.get().create(PersonDataExcel.class);
 *
 *  // Create source
 *  InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
 *  SourceExcel source = SourceFactory.get().create(excelStream);
 *
 *  // Fill the model using definition
 *  definition.fill(source, new PersonDataExcel());
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
