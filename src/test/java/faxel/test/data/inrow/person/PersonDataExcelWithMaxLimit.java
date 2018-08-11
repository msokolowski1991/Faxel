package faxel.test.data.inrow.person;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class PersonDataExcelWithMaxLimit {
    @ExcelSheet(name = "Person", startPosition = 2, maxPosition = 3)
    private Collection<Person> people;

    @ExcelSheet(name = "Address", startPosition = 3, maxPosition = 4)
    private Collection<Address> addresses;

    public Collection<Person> getPeople() {
        return people;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }
}
