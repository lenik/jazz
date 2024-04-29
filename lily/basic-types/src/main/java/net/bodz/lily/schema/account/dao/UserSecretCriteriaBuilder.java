package net.bodz.lily.schema.account.dao;

public class UserSecretCriteriaBuilder
        extends _UserSecretCriteriaBuilder_stuff<UserSecretCriteriaBuilder> {

    public final StringField userName = string("\"user\".name");
    public final StringField name = userName;

    public final StringField phone = string("phone");
    public final StringField email = string("email");

}
