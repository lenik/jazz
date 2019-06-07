package net.bodz.lily.contact.impl;

import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.lily.model.base.CoObjectMask;

public class PartyMask
        extends CoObjectMask {

    Integer categoryId;

    DateTimeRange birthdayRange = new DateTimeRange();
    Locale locale;
    TimeZone timeZone;

    Boolean peer;
    Boolean customer;
    Boolean supplier;
    Set<String> tags;

    String subjectPattern;
    ContactMask contact = new ContactMask();

    String bank;
    String account;

}
