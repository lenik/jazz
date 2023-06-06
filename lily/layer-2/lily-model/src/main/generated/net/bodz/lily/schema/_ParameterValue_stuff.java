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

    public static final int N_VAL = 2147483647;

    private static final int _ord_ID = 1;
    private static final int _ord_PARM_ID = _ord_ID + 10;
    private static final int _ord_VAL = _ord_PARM_ID + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    String val;

    /**  */
    @NotNull
    ParameterDef parm;

    @NotNull
    int parmId;

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
    public ParameterDef getParm() {
        return parm;
    }

    /**
     */
    public void setParm(@NotNull ParameterDef value) {
        this.parm = value;
    }

    @Ordinal(_ord_PARM_ID)
    @Precision(value = 10)
    @Column(name = "parm", nullable = false, precision = 10)
    public synchronized int getParmId() {
        if (parm != null) {
            if (parm.getId() == null)
                return 0;
            return parm.getId();
        }
        return parmId;
    }

    public synchronized void setParmId(int value) {
        this.parmId = value;
    }

    public void initNotNulls() {
        this.val = "";
    }

}
