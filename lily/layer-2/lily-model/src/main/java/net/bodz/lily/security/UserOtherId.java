package net.bodz.lily.security;

import javax.persistence.Table;

import net.bodz.bas.repr.state.StdStates;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.template.RichProperties;

/**
 * OAuth
 */
@Table(name = "useroid")
@IdType(Integer.class)
public class UserOtherId
        extends CoMomentInterval<Integer> {

    private static final long serialVersionUID = 1L;

    User user;
    UserOtherIdType type;
    String otherId;

    RichProperties properties;
    JsonMap auth;

    public boolean isVerified() {
        return getState() == StdStates.OK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserOtherIdType getType() {
        return type;
    }

    public void setType(UserOtherIdType type) {
        this.type = type;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    @Override
    public RichProperties getProperties() {
        if (properties == null) {
            synchronized (this) {
                if (properties == null) {
                    properties = new RichProperties();
                }
            }
        }
        return properties;
    }

    public void setProperties(RichProperties properties) {
        this.properties = properties;
    }

    public JsonMap getAuth() {
        if (auth == null) {
            synchronized (this) {
                if (auth == null) {
                    auth = new JsonMap();
                }
            }
        }
        return auth;
    }

    public void setAuth(JsonMap auth) {
        this.auth = auth;
    }

}
