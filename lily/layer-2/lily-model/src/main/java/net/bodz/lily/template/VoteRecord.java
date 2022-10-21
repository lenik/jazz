package net.bodz.lily.template;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;
import net.bodz.lily.security.User;

@IdType(Long.class)
public abstract class VoteRecord
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    User user;
    int votes;

    public final User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public final int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

}
