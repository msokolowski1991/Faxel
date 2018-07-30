package faxel.test.data.person;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(index = 4)
    private LocalDate validTo;

    @Column(index = 5)
    private LocalDateTime checkInDate;

    public Address() {
    }

    public Address(int number, int personNumber, String address, String type, LocalDate validTo, LocalDateTime checkInDate) {
        this.number = number;
        this.personNumber = personNumber;
        this.address = address;
        this.type = type;
        this.validTo = validTo;
        this.checkInDate = checkInDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return number == address1.number &&
                personNumber == address1.personNumber &&
                Objects.equals(address, address1.address) &&
                Objects.equals(type, address1.type) &&
                Objects.equals(validTo, address1.validTo) &&
                Objects.equals(checkInDate, address1.checkInDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, personNumber, address, type, validTo, checkInDate);
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
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
