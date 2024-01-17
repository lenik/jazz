package net.bodz.lily.contact.dao;

import java.sql.Date;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _OrganizationCriteriaBuilder_stuff<self_t extends _OrganizationCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final DateField<Date> birthday = date("birthday", Date.class);

    public final StringField langTag = string("locale");

    public final StringField timeZone = string("timezone");

    public final IntegerField roleCount = integer("nrole");

    public final BooleanField supplier = bool("supplier");

    public final BooleanField customer = bool("customer");

    public final StringField subject = string("subject");

    public final IntegerField bankCount = integer("nbank");

    public final IntegerField size = integer("\"size\"");

    public final StringField taxId = string("taxid");

}
