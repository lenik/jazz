package net.bodz.lily.schema.account;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

/**
 * User Type
 */
@IdType(Integer.class)
public abstract class _UserType_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "usertype";

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DUMMY = "dummy";

    public static final int N_ID = 10;
    public static final int N_NAME = 20;
    public static final int N_DUMMY = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_NAME = _ord_ID + 1;
    private static final int _ord_DUMMY = 13;

    @Id
    @NotNull
    int id;

    /** The user type name */
    String name;

    Integer dummy;

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
     * The user type name
     */
    @Ordinal(_ord_NAME)
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", length = N_NAME)
    public String getName() {
        return name;
    }

    /**
     * The user type name
     */
    public void setName(String value) {
        this.name = value;
    }

    @Ordinal(_ord_DUMMY)
    @Precision(value = N_DUMMY)
    @Column(name = "dummy", precision = 10)
    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer value) {
        this.dummy = value;
    }

    public void initNotNulls() {
    }

}
