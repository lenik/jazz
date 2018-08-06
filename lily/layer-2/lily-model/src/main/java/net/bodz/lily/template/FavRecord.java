package net.bodz.lily.template;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.security.User;

@IdType(Long.class)
public abstract class FavRecord
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    User user;

    public final User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
