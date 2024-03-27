package net.bodz.lily.schema.contact.dao;

import java.time.LocalDate;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _PersonCriteriaBuilder_stuff<self_t extends _PersonCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final DateField<LocalDate> birthday = date("birthday", LocalDate.class);

    public final IntegerField fatherId = integer("father");

    public final IntegerField motherId = integer("mother");

    public final StringField langTag = string("locale");

    public final StringField timeZone = string("timezone");

    public final IntegerField roleCount = integer("nrole");

    public final BooleanField customer = bool("customer");

    public final BooleanField supplier = bool("supplier");

    public final BooleanField employee = bool("employee");

    public final StringField subject = string("subject");

    public final IntegerField bankCount = integer("nbank");

    public final StringField gender = string("gender");

    public final StringField pronoun = string("pronoun");

    public final StringField sexualOrientation = string("sexual_orient");

    public final StringField homeland = string("homeland");

    public final StringField passport = string("passport");

    public final StringField ssn = string("ssn");

    public final StringField dln = string("dln");

}
