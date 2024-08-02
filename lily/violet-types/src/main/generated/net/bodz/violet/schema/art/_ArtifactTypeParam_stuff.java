package net.bodz.violet.schema.art;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoRelation;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ArtifactTypeParam_stuff
        extends CoRelation {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "arttype_parm";

    public static final String FIELD_ARTTYPE_ID = "arttype";
    public static final String FIELD_PARAMETER_ID = "parm";
    public static final String FIELD_IVAL = "ival";
    public static final String FIELD_FVAL = "fval";
    public static final String FIELD_SVAL = "sval";

    public static final int N_ARTTYPE_ID = 10;
    public static final int N_PARAMETER_ID = 10;
    public static final int N_IVAL = 10;
    public static final int N_FVAL = 17;
    public static final int N_SVAL = 250;

    private static final int _ord_ARTTYPE_ID = 5;
    private static final int _ord_PARAMETER_ID = _ord_ARTTYPE_ID + 1;
    private static final int _ord_IVAL = _ord_PARAMETER_ID + 1;
    private static final int _ord_FVAL = _ord_IVAL + 1;
    private static final int _ord_SVAL = _ord_FVAL + 1;

    Integer ival;

    Double fval;

    String sval;

    /**  */
    @NotNull
    ArtifactType arttype;

    @NotNull
    int arttypeId;

    /**  */
    @NotNull
    StdParameter parameter;

    @NotNull
    int parameterId;

    @Ordinal(_ord_IVAL)
    @Precision(value = N_IVAL)
    @Column(name = "ival", precision = 10)
    public Integer getIval() {
        return ival;
    }

    public void setIval(Integer value) {
        this.ival = value;
    }

    @Ordinal(_ord_FVAL)
    @Precision(value = N_FVAL, scale = 17)
    @Column(name = "fval", precision = 17, scale = 17)
    public Double getFval() {
        return fval;
    }

    public void setFval(Double value) {
        this.fval = value;
    }

    @Ordinal(_ord_SVAL)
    @Precision(value = N_SVAL)
    @TextInput(maxLength = N_SVAL)
    @Column(name = "sval", length = N_SVAL)
    public String getSval() {
        return sval;
    }

    public void setSval(String value) {
        this.sval = value;
    }

    /**
     *
     * @constraint foreign key (arttype) references violet.arttype (id)
     */
    @JoinColumn(name = "arttype")
    @ManyToOne
    @NotNull
    public ArtifactType getArttype() {
        return arttype;
    }

    /**
     */
    public void setArttype(@NotNull ArtifactType value) {
        this.arttype = value;
    }

    @Ordinal(_ord_ARTTYPE_ID)
    @Precision(value = 10)
    @Column(name = "arttype", nullable = false, precision = 10)
    public synchronized int getArttypeId() {
        if (arttype != null) {
            if (arttype.getId() == null)
                return 0;
            return arttype.getId();
        }
        return arttypeId;
    }

    public synchronized void setArttypeId(int value) {
        this.arttypeId = value;
    }

    /**
     *
     * @constraint foreign key (parm) references violet.artparm (id)
     */
    @JoinColumn(name = "parm")
    @ManyToOne
    @NotNull
    public StdParameter getParameter() {
        return parameter;
    }

    /**
     */
    public void setParameter(@NotNull StdParameter value) {
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
    }

}