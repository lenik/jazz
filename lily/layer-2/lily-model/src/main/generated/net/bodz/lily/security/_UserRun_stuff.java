package net.bodz.lily.security;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * User Activity Log
 */
@IdType(Integer.class)
public abstract class _UserRun_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_USER = 1;
    private static final int _ord_SCORE = _ord_USER + 10;
    private static final int _ord_LASTLOG = _ord_SCORE + 1;
    private static final int _ord_LASTLOGIP = _ord_LASTLOG + 1;

    /** The user */
    @Id
    @NotNull
    int user;

    @NotNull
    int score;

    /** Last time of login */
    Timestamp lastlog;

    /** The source IP of last login */
    Object lastlogip;

    @Override
    public Integer id() {
        return getUser();
    }

    @Override
    public void id(Integer id) {
        setUser(id);
    }

    /**
     * The user
     */
    @Id
    @Ordinal(_ord_USER)
    @Precision(value = 10)
    @Column(name = "user", nullable = false, precision = 10)
    public int getUser() {
        return user;
    }

    /**
     * The user
     */
    public void setUser(int value) {
        this.user = value;
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
    @Ordinal(_ord_LASTLOG)
    @Precision(value = 35, scale = 6)
    @Column(name = "lastlog", precision = 35, scale = 6)
    public Timestamp getLastlog() {
        return lastlog;
    }

    /**
     * Last time of login
     */
    public void setLastlog(Timestamp value) {
        this.lastlog = value;
    }

    /**
     * The source IP of last login
     */
    @Ordinal(_ord_LASTLOGIP)
    @Precision(value = 2147483647)
    @Column(name = "lastlogip", precision = 2147483647)
    public Object getLastlogip() {
        return lastlogip;
    }

    /**
     * The source IP of last login
     */
    public void setLastlogip(Object value) {
        this.lastlogip = value;
    }

    public void initNotNulls() {
    }

}
