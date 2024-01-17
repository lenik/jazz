package net.bodz.violet.plan.dao;

import net.bodz.lily.t.base.CoMessageCriteriaBuilder;

public class _DiaryCriteriaBuilder_stuff<self_t extends _DiaryCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final IntegerField value = integer("\"value\"");

}
