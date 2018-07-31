package net.bodz.lily.model.base.security;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * OAuth
 */
@Table(name = "userid")
@IdType(Integer.class)
public class UserId
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    User user;
    OAuthType type;
    String oid;

    Object auth;
    Object data;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OAuthType getType() {
        return type;
    }

    public void setType(OAuthType type) {
        this.type = type;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Object getAuth() {
        return auth;
    }

    public void setAuth(Object auth) {
        this.auth = auth;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
