package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.schema.account.User;

@FieldGroupVue
@IdType(Long.class)
@TsTyped
public abstract class VoteRecord
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_USER_ID = 1;

    @NotNull
    User user;

    @NotNull
    int userId;

    int voteCount;

    /**
     *
     * @label user
     * @constraint foreign key (user) references lily.user (id)
     */
    @NotNull
    public User getUser() {
        return user;
    }

    /**
     */
    public void setUser(@NotNull User value) {
        this.user = value;
    }

    @Ordinal(_ord_USER_ID)
    @Precision(value = 19)
    @Column(name = "user", nullable = false, precision = 19)
    public synchronized int getUserId() {
        if (user != null) {
            if (user.getId() == null)
                return 0;
            return user.getId();
        }
        return userId;
    }

    public synchronized void setUserId(int value) {
        this.userId = value;
    }

    public final int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

}
