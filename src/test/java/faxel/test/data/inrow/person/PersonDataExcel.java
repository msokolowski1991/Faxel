package faxel.test.data.inrow.person;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class PersonDataExcel {
    @ExcelSheet(name = "Person", startPosition = 2)
    private Collection<Person> people;

    @ExcelSheet(name = "Address", startPosition = 2)
    private Collection<Address> addresses;

    public Collection<Person> getPeople() {
        return people;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }
}
