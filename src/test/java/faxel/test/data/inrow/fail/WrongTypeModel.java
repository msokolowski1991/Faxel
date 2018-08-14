package faxel.test.data.inrow.fail;

import faxel.annotation.Cell;

public class WrongTypeModel {

    @Cell(index = 0)
    private Long stringValue;

    @Cell(index = 1)
    private String longValue;

    public Long getStringValue() {
        return stringValue;
    }

    public String getLongValue() {
        return longValue;
    }
}
