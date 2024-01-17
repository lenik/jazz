package net.bodz.lily.vapp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _ApiType_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "apitype";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_UOM = "uom";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_UOM = 30;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = _ord_ID + 9;
    private static final int _ord_UOM = _ord_CODE + 2;

    @Id
    @NotNull
    int id;

    String code;

    @NotNull
    String uom;

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

    @Ordinal(_ord_UOM)
    @NotNull
    @Precision(value = N_UOM)
    @TextInput(maxLength = N_UOM)
    @Column(name = "uom", nullable = false, length = N_UOM)
    public String getUom() {
        return uom;
    }

    public void setUom(@NotNull String value) {
        this.uom = value;
    }

    public void initNotNulls() {
        this.uom = "";
    }

}
