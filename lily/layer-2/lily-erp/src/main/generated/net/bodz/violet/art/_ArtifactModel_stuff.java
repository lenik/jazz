package net.bodz.violet.art;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import org.joda.time.DateTime;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _ArtifactModel_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "artmodel";

    public static final String FIELD_ID = "id";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_VALID = "valid";
    public static final String FIELD_VALIDSINCE = "validsince";
    public static final String FIELD_VALIDUNTIL = "validuntil";
    public static final String FIELD_OBSOLETE_ID = "obsolete";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_MODEL = "model";

    public static final int N_ID = 10;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_VALID = 1;
    public static final int N_VALIDSINCE = 35;
    public static final int N_VALIDUNTIL = 35;
    public static final int N_OBSOLETE_ID = 10;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_MODEL = 40;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = 14;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_VALID = _ord_YEAR + 2;
    private static final int _ord_VALIDSINCE = _ord_VALID + 1;
    private static final int _ord_VALIDUNTIL = _ord_VALIDSINCE + 1;
    private static final int _ord_OBSOLETE_ID = _ord_VALIDUNTIL + 1;
    private static final int _ord_ARTIFACT_ID = _ord_OBSOLETE_ID + 1;
    private static final int _ord_MODEL = _ord_ARTIFACT_ID + 1;

    @Id
    @NotNull
    int id;

    DateTime beginTime;

    DateTime endTime;

    @NotNull
    int year;

    @NotNull
    boolean valid;

    Timestamp validsince;

    Timestamp validuntil;

    String model;

    /**  */
    ArtifactModel obsolete;

    Integer obsoleteId;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

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

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public DateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(DateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Ordinal(_ord_VALID)
    @Precision(value = 1)
    @Column(name = "valid", nullable = false, precision = 1)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        this.valid = value;
    }

    @Ordinal(_ord_VALIDSINCE)
    @Precision(value = 35, scale = 6)
    @Column(name = "validsince", precision = 35, scale = 6)
    public Timestamp getValidsince() {
        return validsince;
    }

    public void setValidsince(Timestamp value) {
        this.validsince = value;
    }

    @Ordinal(_ord_VALIDUNTIL)
    @Precision(value = 35, scale = 6)
    @Column(name = "validuntil", precision = 35, scale = 6)
    public Timestamp getValiduntil() {
        return validuntil;
    }

    public void setValiduntil(Timestamp value) {
        this.validuntil = value;
    }

    @Ordinal(_ord_MODEL)
    @Precision(value = N_MODEL)
    @TextInput(maxLength = N_MODEL)
    @Column(name = "model", length = N_MODEL)
    public String getModel() {
        return model;
    }

    public void setModel(String value) {
        this.model = value;
    }

    /**
     *
     * @label obsolete
     * @constraint foreign key (obsolete) references violet.artmodel (id)
     */
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
     * @label art
     * @constraint foreign key (art) references violet.art (id)
     */
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
