package faxel.test.data.inrow.types;

import java.util.Date;
import java.util.Objects;

import faxel.annotation.Cell;

public class TypesPrimitives {

    @Cell(index = 1)
    private String aString;

    @Cell(index = 2)
    private int aInteger;

    @Cell(index = 3)
    private long aLong;

    @Cell(index = 4)
    private short aShort;

    @Cell(index = 5)
    private float aFloat;

    @Cell(index = 6)
    private double aDouble;

    @Cell(index = 7)
    private boolean aBoolean;

    @Cell(index = 8)
    private Date aLocalDate;

    @Cell(index = 9)
    private Date aLocalDateTime;

    @Cell(index = 10)
    private Date aLocalTime;

    public TypesPrimitives() {
    }

    public TypesPrimitives(String aString, int aInteger, long aLong, short aShort, float aFloat, double aDouble, boolean aBoolean, Date aLocalDate, Date aLocalDateTime, Date aLocalTime) {
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
        TypesPrimitives that = (TypesPrimitives) o;
        return aInteger == that.aInteger &&
                aLong == that.aLong &&
                aShort == that.aShort &&
                Float.compare(that.aFloat, aFloat) == 0 &&
                Double.compare(that.aDouble, aDouble) == 0 &&
                aBoolean == that.aBoolean &&
                Objects.equals(aString, that.aString) &&
                Objects.equals(aLocalDate, that.aLocalDate) &&
                Objects.equals(aLocalDateTime, that.aLocalDateTime) &&
                Objects.equals(aLocalTime, that.aLocalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aString, aInteger, aLong, aShort, aFloat, aDouble, aBoolean, aLocalDate, aLocalDateTime, aLocalTime);
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public int getaInteger() {
        return aInteger;
    }

    public void setaInteger(int aInteger) {
        this.aInteger = aInteger;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public short getaShort() {
        return aShort;
    }

    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Date getaLocalDate() {
        return aLocalDate;
    }

    public void setaLocalDate(Date aLocalDate) {
        this.aLocalDate = aLocalDate;
    }

    public Date getaLocalDateTime() {
        return aLocalDateTime;
    }

    public void setaLocalDateTime(Date aLocalDateTime) {
        this.aLocalDateTime = aLocalDateTime;
    }

    public Date getaLocalTime() {
        return aLocalTime;
    }

    public void setaLocalTime(Date aLocalTime) {
        this.aLocalTime = aLocalTime;
    }
}
