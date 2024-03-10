package net.bodz.violet.schema.shop;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Shop_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "shop";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_HYDM = "hydm";

    public static final int N_CODE = 30;
    public static final int N_HYDM = 10;

    private static final int _ord_CODE = 15;
    private static final int _ord_HYDM = _ord_CODE + 2;

    String code;

    Integer hydm;

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

    @Ordinal(_ord_HYDM)
    @Precision(value = N_HYDM)
    @Column(name = "hydm", precision = 10)
    public Integer getHydm() {
        return hydm;
    }

    public void setHydm(Integer value) {
        this.hydm = value;
    }

    public void initNotNulls() {
    }

}
