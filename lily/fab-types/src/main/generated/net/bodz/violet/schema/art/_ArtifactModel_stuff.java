package net.bodz.violet.schema.art;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImagedEvent;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ArtifactModel_stuff
        extends CoImagedEvent<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "artmodel";

    public static final String FIELD_VALID = "valid";
    public static final String FIELD_VALID_SINCE = "validsince";
    public static final String FIELD_VALID_UNTIL = "validuntil";
    public static final String FIELD_OBSOLETE_ID = "obsolete";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_MODEL_NAME = "model";

    public static final int N_VALID = 1;
    public static final int N_VALID_SINCE = 35;
    public static final int N_VALID_UNTIL = 35;
    public static final int N_OBSOLETE_ID = 10;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_MODEL_NAME = 40;

    private static final int _ord_VALID = 19;
    private static final int _ord_VALID_SINCE = _ord_VALID + 1;
    private static final int _ord_VALID_UNTIL = _ord_VALID_SINCE + 1;
    private static final int _ord_OBSOLETE_ID = _ord_VALID_UNTIL + 1;
    private static final int _ord_ARTIFACT_ID = _ord_OBSOLETE_ID + 1;
    private static final int _ord_MODEL_NAME = _ord_ARTIFACT_ID + 1;

    @NotNull
    boolean valid;

    OffsetDateTime validSince;

    OffsetDateTime validUntil;

    String modelName;

    /**  */
    ArtifactModel obsolete;

    Integer obsoleteId;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    @Ordinal(_ord_VALID)
    @Precision(value = 1)
    @Column(name = "valid", nullable = false, precision = 1)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        this.valid = value;
    }

    @Ordinal(_ord_VALID_SINCE)
    @Precision(value = 35, scale = 6)
    @Column(name = "validsince", precision = 35, scale = 6)
    public OffsetDateTime getValidSince() {
        return validSince;
    }

    public void setValidSince(OffsetDateTime value) {
        this.validSince = value;
    }

    @Ordinal(_ord_VALID_UNTIL)
    @Precision(value = 35, scale = 6)
    @Column(name = "validuntil", precision = 35, scale = 6)
    public OffsetDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(OffsetDateTime value) {
        this.validUntil = value;
    }

    @Ordinal(_ord_MODEL_NAME)
    @Precision(value = N_MODEL_NAME)
    @TextInput(maxLength = N_MODEL_NAME)
    @Column(name = "model", length = N_MODEL_NAME)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String value) {
        this.modelName = value;
    }

    /**
     *
     * @constraint foreign key (obsolete) references violet.artmodel (id)
     */
    @JoinColumn(name = "obsolete")
    @ManyToOne
    public ArtifactModel getObsolete() {
        return obsolete;
    }

    /**
     */
    public void setObsolete(ArtifactModel value) {
        this.obsolete = value;
    }

    @Ordinal(_ord_OBSOLETE_ID)
    @Precision(value = N_OBSOLETE_ID)
    @Column(name = "obsolete", precision = 10)
    public synchronized Integer getObsoleteId() {
        if (obsolete != null) {
            return obsolete.getId();
        }
        return obsoleteId;
    }

    public synchronized void setObsoleteId(Integer value) {
        this.obsoleteId = value;
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

    public void initNotNulls() {
    }

}
