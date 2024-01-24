package net.bodz.lily.schema.account.dao;

public class UserSecretCriteriaBuilder
        extends _UserSecretCriteriaBuilder_stuff<UserSecretCriteriaBuilder> {

    public final StringField userName = string("user");
    public final StringField phone = string("phone");
    public final StringField email = string("email");

}
