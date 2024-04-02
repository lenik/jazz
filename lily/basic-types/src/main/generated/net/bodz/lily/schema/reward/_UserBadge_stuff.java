package net.bodz.lily.schema.reward;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.account.User;

@IdType(Integer.class)
public abstract class _UserBadge_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "user_badge";

    public static final String FIELD_USER_ID = "user";
    public static final String FIELD_BADGE_ID = "badge";

    public static final int N_USER_ID = 10;
    public static final int N_BADGE_ID = 10;

    private static final int _ord_USER_ID = 5;
    private static final int _ord_BADGE_ID = _ord_USER_ID + 1;

    /**  */
    Badge badge;

    Integer badgeId;

    /** (User Account) */
    User user;

    Integer userId;

    /**
     *
     * @constraint foreign key (badge) references lily.badge (id)
     */
    @JoinColumn(name = "badge")
    @ManyToOne
    public Badge getBadge() {
        return badge;
    }

    /**
     */
    public void setBadge(Badge value) {
        this.badge = value;
    }

    @Ordinal(_ord_BADGE_ID)
    @Precision(value = N_BADGE_ID)
    @Column(name = "badge", precision = 10)
    public synchronized Integer getBadgeId() {
        if (badge != null) {
            return badge.getId();
        }
        return badgeId;
    }

    public synchronized void setBadgeId(Integer value) {
        this.badgeId = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @constraint foreign key (user) references lily.user (id)
     */
    @JoinColumn(name = "user")
    @ManyToOne
    public User getUser() {
        return user;
    }

    /**
     * User Account
     */
    public void setUser(User value) {
        this.user = value;
    }

    @Ordinal(_ord_USER_ID)
    @Precision(value = N_USER_ID)
    @Column(name = "user", precision = 10)
    public synchronized Integer getUserId() {
        if (user != null) {
            return user.getId();
        }
        return userId;
    }

    public synchronized void setUserId(Integer value) {
        this.userId = value;
    }

    public void initNotNulls() {
    }

}
