package net.bodz.lily.schema.contact.dao;

import java.time.LocalDate;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _OrganizationCriteriaBuilder_stuff<self_t extends _OrganizationCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final DateField<LocalDate> birthday = date("birthday", LocalDate.class);

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
