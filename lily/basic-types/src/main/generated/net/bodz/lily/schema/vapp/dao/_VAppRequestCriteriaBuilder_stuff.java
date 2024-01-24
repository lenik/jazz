package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _VAppRequestCriteriaBuilder_stuff<self_t extends _VAppRequestCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField code = string("code");

    public final IntegerField year = integer("\"year\"");

    public final StringField subject = string("subject");

    public final IntegerField opId = integer("op");

    public final StringField rawText = string("text");

    public final IntegerField formId = integer("form");

    public final StringField formArguments = string("formargs");

    public final IntegerField dummy = integer("dummy");

}
