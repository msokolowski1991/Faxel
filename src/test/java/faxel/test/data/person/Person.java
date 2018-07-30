package faxel.test.data.person;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import faxel.annotation.Column;
import faxel.test.converters.CustomBigDecimalConverter;

public class Person {
    @Column(index = 0)
    private int number;

    @Column(index = 1)
    private String firstName;

    @Column(index = 2)
    private String lastName;

    @Column(index = 3)
    private boolean isResident;

    @Column(index = 4)
    private Date birhdate;

    @Column(index = 5, converter = CustomBigDecimalConverter.class)
    private BigDecimal accountValue;

    public Person() {
    }

    public Person(int number, String firstName, String lastName, boolean isResident, Date birhdate, BigDecimal accountValue) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isResident = isResident;
        this.birhdate = birhdate;
        this.accountValue = accountValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return number == person.number &&
                isResident == person.isResident &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(birhdate, person.birhdate) &&
                Objects.equals(accountValue, person.accountValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, firstName, lastName, isResident, birhdate, accountValue);
    }

    public Date getBirhdate() {
        return birhdate;
    }

    public void setBirhdate(Date birhdate) {
        this.birhdate = birhdate;
    }

    public int getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isResident() {
        return isResident;
    }

    public void setResident(boolean resident) {
        isResident = resident;
    }

    public BigDecimal getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(BigDecimal accountValue) {
        this.accountValue = accountValue;
    }
}
