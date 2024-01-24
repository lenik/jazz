package net.bodz.lily.schema.contact.dao;

import java.sql.Date;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _PersonCriteriaBuilder_stuff<self_t extends _PersonCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final DateField<Date> birthday = date("birthday", Date.class);

    public final StringField langTag = string("locale");

    public final StringField timeZone = string("timezone");

    public final IntegerField roleCount = integer("nrole");

    public final BooleanField customer = bool("customer");

    public final BooleanField supplier = bool("supplier");

    public final BooleanField employee = bool("employee");

    public final StringField subject = string("subject");

    public final IntegerField bankCount = integer("nbank");

    public final StringField gender = string("gender");

    public final StringField homeland = string("homeland");

    public final StringField passport = string("passport");

    public final StringField ssn = string("ssn");

    public final StringField dln = string("dln");

}
