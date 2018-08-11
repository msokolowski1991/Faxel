package faxel.test.data.inrow.person;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class PersonDataExcelWithMaxLimit {
    @ExcelSheet(sheetName = "Person", startIndex = 2, maxIndex = 3)
    private Collection<Person> people;

    @ExcelSheet(sheetName = "Address", startIndex = 3, maxIndex = 4)
    private Collection<Address> addresses;

    public Collection<Person> getPeople() {
        return people;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }
}
