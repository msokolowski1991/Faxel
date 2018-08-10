package faxel.test.data.inrow.types;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import faxel.annotation.Cell;

public class TypesBoxed {

    @Cell(index = 1)
    private String aString;

    @Cell(index = 2)
    private Integer aInteger;

    @Cell(index = 3)
    private Long aLong;

    @Cell(index = 4)
    private Short aShort;

    @Cell(index = 5)
    private Float aFloat;

    @Cell(index = 6)
    private Double aDouble;

    @Cell(index = 7)
    private Boolean aBoolean;

    @Cell(index = 8)
    private LocalDate aLocalDate;

    @Cell(index = 9)
    private LocalDateTime aLocalDateTime;

    @Cell(index = 10)
    private LocalTime aLocalTime;

    public TypesBoxed() {
    }

    public TypesBoxed(String aString, Integer aInteger, Long aLong, Short aShort, Float aFloat, Double aDouble, Boolean aBoolean, LocalDate aLocalDate, LocalDateTime aLocalDateTime,
            LocalTime aLocalTime) {
        this.aString = aString;
        this.aInteger = aInteger;
        this.aLong = aLong;
        this.aShort = aShort;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aBoolean = aBoolean;
        this.aLocalDate = aLocalDate;
        this.aLocalDateTime = aLocalDateTime;
        this.aLocalTime = aLocalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypesBoxed types1 = (TypesBoxed) o;
        return Objects.equals(aString, types1.aString) &&
                Objects.equals(aInteger, types1.aInteger) &&
                Objects.equals(aLong, types1.aLong) &&
                Objects.equals(aShort, types1.aShort) &&
                Objects.equals(aFloat, types1.aFloat) &&
                Objects.equals(aDouble, types1.aDouble) &&
                Objects.equals(aBoolean, types1.aBoolean) &&
                Objects.equals(aLocalDate, types1.aLocalDate) &&
                Objects.equals(aLocalDateTime, types1.aLocalDateTime) &&
                Objects.equals(aLocalTime, types1.aLocalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aString, aInteger, aLong, aShort, aFloat, aDouble, aBoolean, aLocalDate, aLocalDateTime, aLocalTime);
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public Integer getaInteger() {
        return aInteger;
    }

    public void setaInteger(Integer aInteger) {
        this.aInteger = aInteger;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }

    public Short getaShort() {
        return aShort;
    }

    public void setaShort(Short aShort) {
        this.aShort = aShort;
    }

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public LocalDate getaLocalDate() {
        return aLocalDate;
    }

    public void setaLocalDate(LocalDate aLocalDate) {
        this.aLocalDate = aLocalDate;
    }

    public LocalDateTime getaLocalDateTime() {
        return aLocalDateTime;
    }

    public void setaLocalDateTime(LocalDateTime aLocalDateTime) {
        this.aLocalDateTime = aLocalDateTime;
    }

    public LocalTime getaLocalTime() {
        return aLocalTime;
    }

    public void setaLocalTime(LocalTime aLocalTime) {
        this.aLocalTime = aLocalTime;
    }
}
