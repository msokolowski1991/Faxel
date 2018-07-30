package faxel.test.data.person;

import java.util.Objects;

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

    public Address() {
    }

    public Address(int number, int personNumber, String address, String type) {
        this.number = number;
        this.personNumber = personNumber;
        this.address = address;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return number == address1.number &&
                personNumber == address1.personNumber &&
                Objects.equals(address, address1.address) &&
                Objects.equals(type, address1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, personNumber, address, type);
    }

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
