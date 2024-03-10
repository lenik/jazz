package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;

public class _VAppRequestCriteriaBuilder_stuff<self_t extends _VAppRequestCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField code = string("code");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final IntegerField dummy = integer("dummy");

}
