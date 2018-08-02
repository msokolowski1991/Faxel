package faxel.converter;

import faxel.source.SourceCell;

/**
 * Custom column converter. Might be used to implement custom cell value extraction
 * @param <DEST> Destination value type
 */
public interface ColumnConverter<DEST> {

    /**
     * Converts cell to given data type
     * @param cell Source cell
     * @return Destination value or null
     */
    DEST convert(SourceCell cell);
}
