package net.bodz.lily.security;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * Security Policy
 */
@IdType(Integer.class)
public abstract class _Policy_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_NAME = 30;
    public static final int N_METHOD = 80;

    private static final int _ord_ID = 1;
    private static final int _ord_NAME = _ord_ID + 1;
    private static final int _ord_METHOD = 13;
    private static final int _ord_ALLOW_BITS = _ord_METHOD + 1;
    private static final int _ord_DENY_BITS = _ord_ALLOW_BITS + 1;

    @Id
    @NotNull
    int id;

    /** The policy name (unique) */
    String name;

    /** The method name */
    String method;

    /** allow */
    @NotNull
    int allowBits;

    /** deny */
    @NotNull
    int denyBits;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    /**
     * The policy name (unique)
     */
    @Ordinal(_ord_NAME)
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", length = N_NAME)
    public String getName() {
        return name;
    }

    /**
     * The policy name (unique)
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * The method name
     */
    @Ordinal(_ord_METHOD)
    @Precision(value = N_METHOD)
    @TextInput(maxLength = N_METHOD)
    @Column(name = "method", length = N_METHOD)
    public String getMethod() {
        return method;
    }

    /**
     * The method name
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * allow
     */
    @Ordinal(_ord_ALLOW_BITS)
    @Precision(value = 10)
    @Column(name = "allow", nullable = false, precision = 10)
    public int getAllowBits() {
        return allowBits;
    }

    /**
     * allow
     */
    public void setAllowBits(int value) {
        this.allowBits = value;
    }

    /**
     * deny
     */
    @Ordinal(_ord_DENY_BITS)
    @Precision(value = 10)
    @Column(name = "deny", nullable = false, precision = 10)
    public int getDenyBits() {
        return denyBits;
    }

    /**
     * deny
     */
    public void setDenyBits(int value) {
        this.denyBits = value;
    }

    public void initNotNulls() {
    }

}
