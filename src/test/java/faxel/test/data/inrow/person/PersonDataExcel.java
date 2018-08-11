package faxel.test.data.inrow.person;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class PersonDataExcel {
    @ExcelSheet(sheetName = "Person", startIndex = 2)
    private Collection<Person> people;

    @ExcelSheet(sheetName = "Address", startIndex = 2)
    private Collection<Address> addresses;

    public Collection<Person> getPeople() {
        return people;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }
}
