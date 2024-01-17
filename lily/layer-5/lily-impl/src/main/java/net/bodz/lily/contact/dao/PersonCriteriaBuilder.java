package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.Gender;

/**
 * @see net.bodz.lily.contact.Person
 */
public class PersonCriteriaBuilder
        extends PartyCriteriaBuilder {

    Gender gender;
    Boolean employee;
    String homeland;
    String passport;
    String socialSecurity;
    String driverLicenseNum;

}
