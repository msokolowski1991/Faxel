package faxel.test.data.inrow.person;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import faxel.annotation.Cell;
import faxel.test.converters.CustomBigDecimalConverter;

public class Person {
    @Cell(index = 0)
    private int number;

    @Cell(index = 1)
    private String firstName;

    @Cell(index = 2)
    private String lastName;

    @Cell(index = 3)
    private boolean isResident;

    @Cell(index = 4)
    private Date birhdate;

    @Cell(index = 5, converter = CustomBigDecimalConverter.class)
    private BigDecimal accountValue;

    @Cell(index = 6)
    private LocalTime workDayStart;

    @Cell(index = 7)
    private Float qualityFactor;

    public Person() {
    }

    public Person(int number, String firstName, String lastName, boolean isResident, Date birhdate, BigDecimal accountValue, LocalTime workDayStart, Float qualityFactor) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isResident = isResident;
        this.birhdate = birhdate;
        this.accountValue = accountValue;
        this.workDayStart = workDayStart;
        this.qualityFactor = qualityFactor;
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
                Objects.equals(accountValue, person.accountValue) &&
                Objects.equals(workDayStart, person.workDayStart) &&
                Objects.equals(qualityFactor, person.qualityFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, firstName, lastName, isResident, birhdate, accountValue, workDayStart, qualityFactor);
    }

    public LocalTime getWorkDayStart() {
        return workDayStart;
    }

    public void setWorkDayStart(LocalTime workDayStart) {
        this.workDayStart = workDayStart;
    }

    public Float getQualityFactor() {
        return qualityFactor;
    }

    public void setQualityFactor(Float qualityFactor) {
        this.qualityFactor = qualityFactor;
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
