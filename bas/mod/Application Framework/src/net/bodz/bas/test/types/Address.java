package net.bodz.bas.test.types;

import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.Objects;

public class Address {

    private String country;
    private String city;
    private String address;
    private int    postCode;
    private String phoneNumber;

    public Address() {
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
    public boolean equals(Object obj) {
        if (!(obj instanceof Address))
            return false;
        Address a = (Address) obj;
        if (postCode != a.postCode)
            return false;
        if (!Objects.equals(address, a.address))
            return false;
        if (!Objects.equals(city, a.city))
            return false;
        if (!Objects.equals(country, a.country))
            return false;
        if (!Objects.equals(phoneNumber, a.phoneNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format(AppNLS.getString("Address.fmt.address_sssds"), // //$NON-NLS-1$
                address, city, country, postCode, phoneNumber);
    }

    public static final Address Marks100;
    public static final Address Golf200;
    public static final Address YHLib;
    public static final Address HNHome;
    static {
        Marks100 = new Address(
                AppNLS.getString("Address.a1"), AppNLS.getString("Address.b1"), AppNLS.getString("Address.c1"), 12345, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                AppNLS.getString("Address.d1")); //$NON-NLS-1$
        Golf200 = new Address(
                AppNLS.getString("Address.a2"), AppNLS.getString("Address.b2"), AppNLS.getString("Address.c2"), 23456, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                AppNLS.getString("Address.d2")); //$NON-NLS-1$
        YHLib = new Address(
                AppNLS.getString("Address.a3"), AppNLS.getString("Address.b3"), AppNLS.getString("Address.c3"), 34567, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                AppNLS.getString("Address.d3")); //$NON-NLS-1$
        HNHome = new Address(
                AppNLS.getString("Address.a4"), AppNLS.getString("Address.b4"), AppNLS.getString("Address.c4"), 314400, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                AppNLS.getString("Address.d4")); //$NON-NLS-1$
    }

}
