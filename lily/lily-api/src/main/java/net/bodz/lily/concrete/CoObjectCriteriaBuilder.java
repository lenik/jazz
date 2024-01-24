package net.bodz.lily.concrete;

import net.bodz.lily.security.login.LoginToken;

/**
 * @see net.bodz.lily.concrete.CoObject
 */
public class CoObjectCriteriaBuilder<self_t extends CoObjectCriteriaBuilder<self_t>>
        extends StructRowCriteriaBuilder<self_t> {

    public final StringField uniqName = string("name");
    public final StringField label = string("label");
    public final StringField description = string("description");

//    String queryText;

    public final IntegerField priority = integer("priority");
    public final IntegerField flags = integer("flags");
    public final IntegerField state = integer("state");
    public final IntegerField ownerId = integer("uid");
    public final IntegerField ownerGroupId = integer("gid");
    public final IntegerField acl = integer("acl");

    public void query(String text) {
        String pattern = "%" + text + "%";
        uniqName.like(pattern);
        label.like(pattern);
        description.like(pattern);
    }

    /**
     * XXX mybatis global vars won't work.
     */
    public LoginToken getLoginToken() {
        LoginToken token = LoginToken.fromRequest();
        return token;
    }

}
