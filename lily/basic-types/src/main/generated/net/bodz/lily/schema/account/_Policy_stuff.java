package net.bodz.lily.schema.account;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

/**
 * Security Policy
 */
@IdType(Integer.class)
public abstract class _Policy_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "policy";

    public static final String FIELD_NAME = "name";
    public static final String FIELD_CONTROL_CLASS = "cclass";
    public static final String FIELD_METHOD_NAME = "method";
    public static final String FIELD_ALLOW_BITS = "allow";
    public static final String FIELD_DENY_BITS = "deny";

    public static final int N_NAME = 30;
    public static final int N_CONTROL_CLASS = 80;
    public static final int N_METHOD_NAME = 80;
    public static final int N_ALLOW_BITS = 10;
    public static final int N_DENY_BITS = 10;

    private static final int _ord_NAME = 2;
    private static final int _ord_CONTROL_CLASS = 13;
    private static final int _ord_METHOD_NAME = _ord_CONTROL_CLASS + 1;
    private static final int _ord_ALLOW_BITS = _ord_METHOD_NAME + 1;
    private static final int _ord_DENY_BITS = _ord_ALLOW_BITS + 1;

    /** The policy name (unique) */
    String name;

    /** The control class */
    @NotNull
    String controlClass;

    /** The method name */
    String methodName;

    @NotNull
    int allowBits;

    @NotNull
    int denyBits;

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
     * The control class
     */
    @Ordinal(_ord_CONTROL_CLASS)
    @NotNull
    @Precision(value = N_CONTROL_CLASS)
    @TextInput(maxLength = N_CONTROL_CLASS)
    @Column(name = "cclass", nullable = false, length = N_CONTROL_CLASS)
    public String getControlClass() {
        return controlClass;
    }

    /**
     * The control class
     */
    public void setControlClass(@NotNull String value) {
        this.controlClass = value;
    }

    /**
     * The method name
     */
    @Ordinal(_ord_METHOD_NAME)
    @Precision(value = N_METHOD_NAME)
    @TextInput(maxLength = N_METHOD_NAME)
    @Column(name = "method", length = N_METHOD_NAME)
    public String getMethodName() {
        return methodName;
    }

    /**
     * The method name
     */
    public void setMethodName(String value) {
        this.methodName = value;
    }

    @Ordinal(_ord_ALLOW_BITS)
    @Precision(value = 10)
    @Column(name = "allow", nullable = false, precision = 10)
    public int getAllowBits() {
        return allowBits;
    }

    public void setAllowBits(int value) {
        this.allowBits = value;
    }

    @Ordinal(_ord_DENY_BITS)
    @Precision(value = 10)
    @Column(name = "deny", nullable = false, precision = 10)
    public int getDenyBits() {
        return denyBits;
    }

    public void setDenyBits(int value) {
        this.denyBits = value;
    }

    public void initNotNulls() {
        this.controlClass = "";
    }

}
