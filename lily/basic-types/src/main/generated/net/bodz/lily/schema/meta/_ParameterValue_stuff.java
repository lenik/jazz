package net.bodz.lily.schema.meta;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ParameterValue_stuff<this_t extends _ParameterValue_stuff<this_t>>
        extends AbstractDefinition<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_parmval";

    public static final String FIELD_PARAMETER_ID = "parm";
    public static final String FIELD_VAL = "val";

    public static final int N_PARAMETER_ID = 10;
    public static final int N_VAL = 2147483647;

    private static final int _ord_PARAMETER_ID = 12;
    private static final int _ord_VAL = _ord_PARAMETER_ID + 1;

    @NotNull
    String val;

    /**  */
    @NotNull
    ParameterDef parameter;

    @NotNull
    int parameterId;

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
     * @constraint foreign key (parm) references lily._parm (id)
     */
    @JoinColumn(name = "parm")
    @ManyToOne
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
            if (parameter.getId() == null)
                return 0;
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
