package net.bodz.lily.contact.dao;

import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class PartyCriteriaBuilder
        extends CoObjectCriteriaBuilder {

    Integer categoryId;

    DateTimeRange birthdayRange = new DateTimeRange();
    Locale locale;
    TimeZone timeZone;

    Boolean peer;
    Boolean customer;
    Boolean supplier;
    Set<String> tags;

    String subjectPattern;
    ContactCriteriaBuilder contact = new ContactCriteriaBuilder();

    String bank;
    String account;

}
