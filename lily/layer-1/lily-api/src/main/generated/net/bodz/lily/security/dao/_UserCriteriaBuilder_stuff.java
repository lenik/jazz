package net.bodz.lily.security.dao;

public class _UserCriteriaBuilder_stuff<self_t extends _UserCriteriaBuilder_stuff<self_t>>
        extends CoPrincipalCriteriaBuilder<self_t> {

    /** User type like system-user, normal-user, etc. */
    public final IntegerField typeId = integer("\"type\"");

    /** The primary user group, the default value of ownerGroup. */
    public final IntegerField primaryGroupId = integer("gid0");

    /** The referer user (used for promotion) */
    public final IntegerField refererId = integer("referer");

}
