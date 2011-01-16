package user.bean;

import net.bodz.bas.meta.info.Doc;

public class AddressBean {

    private String country;
    private String city;
    private String address;

    @Doc("Country in the world")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Doc("City in a country")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Doc("Address in the city")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
