package faxel.source;

import java.util.Date;

/**
 * Abstraction over excel's cell implementation.
 */
public interface SourceCell {
    /**
     * @return cell value as string.
     */
    String stringValue();

    /**
     * @return cell value as number.
     */
    double numericValue();

    /**
     * @return cell value as boolean.
     */
    boolean boolValue();

    /**
     * @return cell value as date
     */
    Date dateValue();
}
