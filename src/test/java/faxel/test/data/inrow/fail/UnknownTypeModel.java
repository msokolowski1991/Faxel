package faxel.test.data.inrow.fail;

import faxel.annotation.Cell;

public class UnknownTypeModel {

    @Cell(index = 0)
    private java.beans.BeanInfo unknownType;

    public java.beans.BeanInfo getUnknownType() {
        return unknownType;
    }
}
