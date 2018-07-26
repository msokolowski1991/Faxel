package faxel.test.data.person;

import faxel.annotation.Column;

public class Address {
    @Column(index = 0)
    private int number;

    @Column(index = 1)
    private int personNumber;

    @Column(index = 2)
    private String address;

    @Column(index = 3)
    private String type;

    public int getNumber() {
        return number;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
}
