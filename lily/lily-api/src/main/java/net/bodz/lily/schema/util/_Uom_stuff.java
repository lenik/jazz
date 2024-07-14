package net.bodz.lily.schema.util;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoCode;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _Uom_stuff<this_t extends _Uom_stuff<this_t>>
        extends CoCode<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "uom";

    public static final String FIELD_PROPERTY = "prop";
    public static final String FIELD_STANDARD_ID = "std";
    public static final String FIELD_SCALE = "scale";

    public static final int N_PROPERTY = 30;
    public static final int N_STANDARD_ID = 10;
    public static final int N_SCALE = 17;

    private static final int _ord_PROPERTY = 7;
    private static final int _ord_STANDARD_ID = _ord_PROPERTY + 1;
    private static final int _ord_SCALE = _ord_STANDARD_ID + 1;

    @NotNull
    String property;

    @NotNull
    double scale;

    /**  */
    Uom standard;

    Integer standardId;

    @Ordinal(_ord_PROPERTY)
    @NotNull
    @Precision(value = N_PROPERTY)
    @TextInput(maxLength = N_PROPERTY)
    @Column(name = "prop", nullable = false, length = N_PROPERTY)
    public String getProperty() {
        return property;
    }

    public void setProperty(@NotNull String value) {
        this.property = value;
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
     * @constraint foreign key (std) references lily.uom (id)
     */
    @JoinColumn(name = "std")
    @ManyToOne
    public Uom getStandard() {
        return standard;
    }

    /**
     */
    public void setStandard(Uom value) {
        this.standard = value;
    }

    @Ordinal(_ord_STANDARD_ID)
    @Precision(value = N_STANDARD_ID)
    @Column(name = "std", precision = 10)
    public synchronized Integer getStandardId() {
        if (standard != null) {
            return standard.getId();
        }
        return standardId;
    }

    public synchronized void setStandardId(Integer value) {
        this.standardId = value;
    }

    public void initNotNulls() {
        this.property = "";
    }

}
