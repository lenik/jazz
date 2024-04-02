package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.account.User;

@ForEntityType(User.class)
public class _UserCriteriaBuilder_stuff<self_t extends _UserCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** User type like system-user, normal-user, etc. */
    public final IntegerField typeId = integer("\"type\"");

    /** The user name (unique) */
    public final StringField name = string("\"name\"");

    /** The primary user group, the default value of ownerGroup. */
    public final IntegerField primaryGroupId = integer("gid0");

    /** The referer user (used for promotion) */
    public final IntegerField refererId = integer("referer");

    public final IntegerField personId = integer("person");

}
