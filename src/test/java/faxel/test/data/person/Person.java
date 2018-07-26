package faxel.test.data.person;

import faxel.annotation.Column;

public class Person {
    @Column(index = 0)
    private int number;

    @Column(index = 1)
    private String firstName;

    @Column(index = 2)
    private String lastName;

    public int getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
