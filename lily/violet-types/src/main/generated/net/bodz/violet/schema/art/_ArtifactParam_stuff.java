package net.bodz.violet.schema.art;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoRelation;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ArtifactParam_stuff
        extends CoRelation<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "art_parm";

    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_PARAMETER_ID = "parm";
    public static final String FIELD_IVAL = "ival";
    public static final String FIELD_FVAL = "fval";
    public static final String FIELD_SVAL = "sval";

    public static final int N_ARTIFACT_ID = 10;
    public static final int N_PARAMETER_ID = 10;
    public static final int N_IVAL = 10;
    public static final int N_FVAL = 17;
    public static final int N_SVAL = 250;

    private static final int _ord_ARTIFACT_ID = 5;
    private static final int _ord_PARAMETER_ID = _ord_ARTIFACT_ID + 1;
    private static final int _ord_IVAL = _ord_PARAMETER_ID + 1;
    private static final int _ord_FVAL = _ord_IVAL + 1;
    private static final int _ord_SVAL = _ord_FVAL + 1;

    Integer ival;

    Double fval;

    String sval;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

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
     * @constraint foreign key (art) references violet.art (id)
     */
    @JoinColumn(name = "art")
    @ManyToOne
    @NotNull
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     */
    public void setArtifact(@NotNull Artifact value) {
        this.artifact = value;
    }

    @Ordinal(_ord_ARTIFACT_ID)
    @Precision(value = 10)
    @Column(name = "art", nullable = false, precision = 10)
    public synchronized int getArtifactId() {
        if (artifact != null) {
            if (artifact.getId() == null)
                return 0;
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(int value) {
        this.artifactId = value;
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
