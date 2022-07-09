package com.mongo.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Address entity class.
 *
 * @author Artem Boiko
 */
public class Address implements Serializable {
    private String line1;
    private String line2;
    private Integer countryCode;

    public Address() {
    }

    public Address(String line1, String line2, Integer countryCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.countryCode = countryCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return line1.equals(address.line1) &&
               line2.equals(address.line2) &&
               countryCode.equals(address.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line1, line2, countryCode);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                                Address.class.getSimpleName() + "[",
                                "]")
                .add("line1='" + line1 + "'")
                .add("line2='" + line2 + "'")
                .add("countryCode='" + countryCode + "'")
                .toString();
    }
}
