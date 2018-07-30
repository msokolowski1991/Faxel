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

    public Person() {
    }

    public Person(int number, String firstName, String lastName) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return number == person.number &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, firstName, lastName);
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
}
