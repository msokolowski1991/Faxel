package faxel.source;

import java.util.Date;

public interface SourceCell {
    String stringValue();
    double numericValue();
    boolean boolValue();
    Date dateValue();
}
