package net.bodz.lily.util;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _Uom_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "uom";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_PROP = "prop";
    public static final String FIELD_STD_ID = "std";
    public static final String FIELD_SCALE = "scale";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_PROP = 30;
    public static final int N_STD_ID = 10;
    public static final int N_SCALE = 17;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = _ord_ID + 1;
    private static final int _ord_PROP = _ord_CODE + 4;
    private static final int _ord_STD_ID = _ord_PROP + 1;
    private static final int _ord_SCALE = _ord_STD_ID + 1;

    @Id
    @NotNull
    int id;

    String code;

    @NotNull
    String prop;

    @NotNull
    double scale;

    /**  */
    Uom std;

    Integer stdId;

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

    @Ordinal(_ord_PROP)
    @NotNull
    @Precision(value = N_PROP)
    @TextInput(maxLength = N_PROP)
    @Column(name = "prop", nullable = false, length = N_PROP)
    public String getProp() {
        return prop;
    }

    public void setProp(@NotNull String value) {
        this.prop = value;
    }

    @Ordinal(_ord_SCALE)
    @Precision(value = 17, scale = 17)
    @Column(name = "scale", nullable = false, precision = 17, scale = 17)
    public double getScale() {
        return scale;
    }

    public void setScale(double value) {
        this.scale = value;
    }

    /**
     *
     * @label std
     * @constraint foreign key (std) references lily.uom (id)
     */
    public Uom getStd() {
        return std;
    }

    /**
     */
    public void setStd(Uom value) {
        this.std = value;
    }

    @Ordinal(_ord_STD_ID)
    @Precision(value = N_STD_ID)
    @Column(name = "std", precision = 10)
    public synchronized Integer getStdId() {
        if (std != null) {
            return std.getId();
        }
        return stdId;
    }

    public synchronized void setStdId(Integer value) {
        this.stdId = value;
    }

    public void initNotNulls() {
        this.prop = "";
    }

}
