package faxel.test.data.person;

import java.util.Objects;

import faxel.annotation.Column;

public class Person {
    @Column(index = 0)
    private int number;

    @Column(index = 1)
    private String firstName;

    @Column(index = 2)
    private String lastName;

    @Column(index = 3)
    private boolean isResident;

    public Person() {
    }

    public Person(int number, String firstName, String lastName, boolean isResident) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isResident = isResident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return number == person.number &&
                isResident == person.isResident &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, firstName, lastName, isResident);
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
}
