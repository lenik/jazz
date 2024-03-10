package net.bodz.lily.schema.vapp;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ApiType_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "apitype";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_UOM = "uom";

    public static final int N_CODE = 30;
    public static final int N_UOM = 30;

    private static final int _ord_CODE = 11;
    private static final int _ord_UOM = _ord_CODE + 2;

    String code;

    @NotNull
    String uom;

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
