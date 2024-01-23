package net.bodz.violet.fab;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactModel;

@IdType(Integer.class)
public abstract class _FabStdProcessInput_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabstdproc_in";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PROCESS_ID = "proc";
    public static final String FIELD_MODEL_ID = "model";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_QUANTITY = "qty";

    public static final int N_ID = 10;
    public static final int N_PROCESS_ID = 10;
    public static final int N_MODEL_ID = 10;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_QUANTITY = 20;

    private static final int _ord_ID = 1;
    private static final int _ord_PROCESS_ID = _ord_ID + 4;
    private static final int _ord_MODEL_ID = _ord_PROCESS_ID + 1;
    private static final int _ord_ARTIFACT_ID = _ord_MODEL_ID + 1;
    private static final int _ord_QUANTITY = _ord_ARTIFACT_ID + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    BigDecimal quantity;

    /**  */
    @NotNull
    FabStdProcess process;

    @NotNull
    int processId;

    /**  */
    ArtifactModel model;

    Integer modelId;

    /**  */
    Artifact artifact;

    Integer artifactId;

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

    @Ordinal(_ord_QUANTITY)
    @NotNull
    @Precision(value = N_QUANTITY, scale = 2)
    @Column(name = "qty", nullable = false, precision = 20, scale = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull BigDecimal value) {
        this.quantity = value;
    }

    /**
     *
     * @label proc
     * @constraint foreign key (proc) references violet.fabstdproc (id)
     */
    @NotNull
    public FabStdProcess getProcess() {
        return process;
    }

    /**
     */
    public void setProcess(@NotNull FabStdProcess value) {
        this.process = value;
    }

    @Ordinal(_ord_PROCESS_ID)
    @Precision(value = 10)
    @Column(name = "proc", nullable = false, precision = 10)
    public synchronized int getProcessId() {
        if (process != null) {
            return process.getId();
        }
        return processId;
    }

    public synchronized void setProcessId(int value) {
        this.processId = value;
    }

    /**
     *
     * @label model
     * @constraint foreign key (model) references violet.artmodel (id)
     */
    public ArtifactModel getModel() {
        return model;
    }

    /**
     */
    public void setModel(ArtifactModel value) {
        this.model = value;
    }

    @Ordinal(_ord_MODEL_ID)
    @Precision(value = N_MODEL_ID)
    @Column(name = "model", precision = 10)
    public synchronized Integer getModelId() {
        if (model != null) {
            return model.getId();
        }
        return modelId;
    }

    public synchronized void setModelId(Integer value) {
        this.modelId = value;
    }

    /**
     *
     * @label art
     * @constraint foreign key (art) references violet.art (id)
     */
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     */
    public void setArtifact(Artifact value) {
        this.artifact = value;
    }

    @Ordinal(_ord_ARTIFACT_ID)
    @Precision(value = N_ARTIFACT_ID)
    @Column(name = "art", precision = 10)
    public synchronized Integer getArtifactId() {
        if (artifact != null) {
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(Integer value) {
        this.artifactId = value;
    }

    public void initNotNulls() {
        this.quantity = BigDecimal.ZERO;
    }

}
