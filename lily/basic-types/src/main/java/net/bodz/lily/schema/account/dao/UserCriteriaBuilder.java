package net.bodz.lily.schema.account.dao;

public class UserCriteriaBuilder
        extends _UserCriteriaBuilder_stuff<UserCriteriaBuilder> {

    public final IntegerField categoryId = integer("cat");

    // join with Group-Member
    public final IntegerField groupId = integer("g_id");
    public final StringField groupLabel = string("g_label");

    // join with UserSecret
    public final StringField password = string("password");
    public final StringField email = string("email");
    public final StringField phone = string("phone");

}
