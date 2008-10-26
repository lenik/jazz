package net.bodz.bas.test.types;

public class Address {

    private String country;
    private String city;
    private String address;
    private int    postCode;
    private String phoneNumber;

    public Address() {
    }

    public Address(String address, String city, String country, int postCode,
            String phoneNumber) {
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
    public String toString() {
        return String.format("%s, %s, %s PO[%d] TEL[%s]", //
                address, city, country, postCode, phoneNumber);
    }

}
