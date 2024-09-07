package net.bodz.bas.t.pojo.eg;

import java.util.Objects;

public class Address {

    private String country;
    private String city;
    private String address;
    private int postCode;
    private String phoneNumber;

    public Address() {
    }

    public Address(String address, String city, String country) {
        this(address, city, country, 0, null);
    }

    public Address(String address, String city, String country, int postCode, String phoneNumber) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, country, phoneNumber, postCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return Objects.equals(address, other.address) && Objects.equals(city, other.city)
                && Objects.equals(country, other.country) && Objects.equals(phoneNumber, other.phoneNumber)
                && postCode == other.postCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s PO[%d] TEL[%s]", //
                address, city, country, postCode, phoneNumber);
    }

    public static final Address Marks100;
    public static final Address Golf200;
    public static final Address YHLib;
    public static final Address HNHome;
    static {
        Marks100 = new Address("100 Marks Street", "London", "England", 12345, "123-456-789");
        Golf200 = new Address("200 Golf Road", "New York", "USA", 23456, "234-567-888");
        YHLib = new Address("300 Culture Park", "Hangzhou", "CHINA", 34567, "034-5678999");
        HNHome = new Address("297 Changdai Rd.", "Haining", "CHINA", 314400, "138-19471680");
    }

}
