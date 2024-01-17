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
public abstract class _ParameterValue_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_parmval";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_PARAMETER_ID = "parm";
    public static final String FIELD_VAL = "val";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_PARAMETER_ID = 10;
    public static final int N_VAL = 2147483647;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = _ord_ID + 1;
    private static final int _ord_PARAMETER_ID = _ord_CODE + 9;
    private static final int _ord_VAL = _ord_PARAMETER_ID + 1;

    @Id
    @NotNull
    int id;

    String code;

    @NotNull
    String val;

    /**  */
    @NotNull
    ParameterDef parameter;

    @NotNull
    int parameterId;

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

    @Ordinal(_ord_VAL)
    @NotNull
    @Precision(value = N_VAL)
    @TextInput(maxLength = N_VAL)
    @Column(name = "val", nullable = false, length = N_VAL)
    public String getVal() {
        return val;
    }

    public void setVal(@NotNull String value) {
        this.val = value;
    }

    /**
     *
     * @label parm
     * @constraint foreign key (parm) references lily._parm (id)
     */
    @NotNull
    public ParameterDef getParameter() {
        return parameter;
    }

    /**
     */
    public void setParameter(@NotNull ParameterDef value) {
        this.parameter = value;
    }

    @Ordinal(_ord_PARAMETER_ID)
    @Precision(value = 10)
    @Column(name = "parm", nullable = false, precision = 10)
    public synchronized int getParameterId() {
        if (parameter != null) {
            return parameter.getId();
        }
        return parameterId;
    }

    public synchronized void setParameterId(int value) {
        this.parameterId = value;
    }

    public void initNotNulls() {
        this.val = "";
    }

}
