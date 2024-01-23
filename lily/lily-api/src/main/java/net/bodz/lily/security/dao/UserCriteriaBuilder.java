package net.bodz.lily.security.dao;

public class UserCriteriaBuilder
        extends _UserCriteriaBuilder_stuff<UserCriteriaBuilder> {

    IntegerField categoryId = integer("cat");

    // join with Group-Member
    IntegerField groupId = integer("g_id");
    StringField groupLabel = string("g_label");

    // join with UserSecret
    StringField password = string("password");
    StringField email = string("email");
    StringField phone = string("phone");

}
