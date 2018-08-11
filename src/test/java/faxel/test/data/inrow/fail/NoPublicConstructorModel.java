package faxel.test.data.inrow.fail;

import faxel.annotation.Cell;

public class NoPublicConstructorModel {
    @Cell(index = 0)
    private Object any;

    private NoPublicConstructorModel() {

    }
}
