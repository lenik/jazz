package net.bodz.lily.schema.account;

import java.net.InetAddress;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

/**
 * User Activity Log
 */
@IdType(Integer.class)
public abstract class _UserRun_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "user_run";

    public static final String FIELD_USER_ID = "user";
    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_SCORE = "score";
    public static final String FIELD_LAST_LOGIN_TIME = "lastlog";
    public static final String FIELD_LAST_LOGIN_I_P = "lastlogip";

    public static final int N_USER_ID = 10;
    public static final int N_PROPERTIES = 2147483647;
    public static final int N_SCORE = 10;
    public static final int N_LAST_LOGIN_TIME = 35;
    public static final int N_LAST_LOGIN_I_P = 2147483647;

    private static final int _ord_USER_ID = 1;
    private static final int _ord_PROPERTIES = _ord_USER_ID + 10;
    private static final int _ord_SCORE = _ord_PROPERTIES + 1;
    private static final int _ord_LAST_LOGIN_TIME = _ord_SCORE + 1;
    private static final int _ord_LAST_LOGIN_I_P = _ord_LAST_LOGIN_TIME + 1;

    JsonVariant properties;

    @NotNull
    int score;

    /** Last time of login */
    Timestamp lastLoginTime;

    /** The source IP of last login */
    InetAddress lastLoginIP;

    /** The user */
    @NotNull
    User user;

    /** The user */
    @Id
    @NotNull
    int userId;

    @Override
    public Integer id() {
        return getUserId();
    }

    @Override
    public void id(Integer id) {
        setUserId(id);
    }

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
    }

    @Ordinal(_ord_SCORE)
    @Precision(value = 10)
    @Column(name = "score", nullable = false, precision = 10)
    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    /**
     * Last time of login
     */
    @Ordinal(_ord_LAST_LOGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "lastlog", precision = 35, scale = 6)
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * Last time of login
     */
    public void setLastLoginTime(Timestamp value) {
        this.lastLoginTime = value;
    }

    /**
     * The source IP of last login
     */
    @Ordinal(_ord_LAST_LOGIN_I_P)
    @Precision(value = 2147483647)
    @Column(name = "lastlogip", precision = 2147483647)
    public InetAddress getLastLoginIP() {
        return lastLoginIP;
    }

    /**
     * The source IP of last login
     */
    public void setLastLoginIP(InetAddress value) {
        this.lastLoginIP = value;
    }

    /**
     * The user
     *
     * @constraint foreign key (user) references lily.user (id)
     */
    @JoinColumn(name = "user")
    @ManyToOne
    @NotNull
    public User getUser() {
        return user;
    }

    /**
     * The user
     */
    public void setUser(@NotNull User value) {
        this.user = value;
    }

    /**
     * The user
     */
    @Id
    @Ordinal(_ord_USER_ID)
    @Precision(value = 10)
    @Column(name = "user", nullable = false, precision = 10)
    public synchronized int getUserId() {
        if (user != null) {
            if (user.getId() == null)
                return 0;
            return user.getId();
        }
        return userId;
    }

    /**
     * The user
     */
    public synchronized void setUserId(int value) {
        this.userId = value;
    }

    public void initNotNulls() {
    }

}
