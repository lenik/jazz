package net.bodz.lily.security;

import javax.persistence.Table;

import net.bodz.bas.site.json.JsonMap;
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
    UserIdType type;
    String oid;

    JsonMap auth;
    JsonMap data;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserIdType getType() {
        return type;
    }

    public void setType(UserIdType type) {
        this.type = type;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public JsonMap getAuth() {
        return auth;
    }

    public void setAuth(JsonMap auth) {
        this.auth = auth;
    }

    public JsonMap getData() {
        return data;
    }

    public void setData(JsonMap data) {
        this.data = data;
    }

}
