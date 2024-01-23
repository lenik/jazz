package net.bodz.lily.schema;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _Policy_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_METHOD = 40;

    private static final int _ord_ID = 1;
    private static final int _ord_METHOD = 13;
    private static final int _ord_ALLOW = _ord_METHOD + 1;
    private static final int _ord_DENY = _ord_ALLOW + 1;

    @Id
    @NotNull
    int id;

    String method;

    @NotNull
    int allow;

    @NotNull
    int deny;

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

    @Ordinal(_ord_METHOD)
    @Precision(value = N_METHOD)
    @TextInput(maxLength = N_METHOD)
    @Column(name = "method", length = N_METHOD)
    public String getMethod() {
        return method;
    }

    public void setMethod(String value) {
        this.method = value;
    }

    @Ordinal(_ord_ALLOW)
    @Precision(value = 10)
    @Column(name = "allow", nullable = false, precision = 10)
    public int getAllow() {
        return allow;
    }

    public void setAllow(int value) {
        this.allow = value;
    }

    @Ordinal(_ord_DENY)
    @Precision(value = 10)
    @Column(name = "deny", nullable = false, precision = 10)
    public int getDeny() {
        return deny;
    }

    public void setDeny(int value) {
        this.deny = value;
    }

    public void initNotNulls() {
    }

}
