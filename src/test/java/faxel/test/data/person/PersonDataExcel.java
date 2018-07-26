package faxel.test.data.person;

import java.util.Collection;

import faxel.annotation.SheetRows;

public class PersonDataExcel {
    @SheetRows(sheetName = "Person")
    private Collection<Person> people;

    @SheetRows(sheetName = "Address")
    private Collection<Address> addresses;

    public Collection<Person> getPeople() {
        return people;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }
}
