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
public abstract class _SchemaDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_schema";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_DUMMY = "dummy";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_DUMMY = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = _ord_ID + 1;
    private static final int _ord_DUMMY = _ord_CODE + 9;

    @Id
    @NotNull
    int id;

    String code;

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

    @Ordinal(_ord_CODE)
    @Precision(value = N_CODE)
    @TextInput(maxLength = N_CODE)
    @Column(name = "code", length = N_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
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
