package net.bodz.violet.schema.store;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;

@IdType(Long.class)
public abstract class _StoreItem_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "storel";

    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_REGION_ID = "region";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_QUANTITY = "qty";

    public static final int N_ARTIFACT_ID = 10;
    public static final int N_REGION_ID = 10;
    public static final int N_BATCH = 2147483647;
    public static final int N_QUANTITY = 20;

    private static final int _ord_ARTIFACT_ID = 12;
    private static final int _ord_REGION_ID = _ord_ARTIFACT_ID + 1;
    private static final int _ord_BATCH = _ord_REGION_ID + 1;
    private static final int _ord_QUANTITY = _ord_BATCH + 1;

    JsonVariant batch;

    @NotNull
    BigDecimal quantity;

    /**  */
    @NotNull
    Region region;

    @NotNull
    int regionId;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public JsonVariant getBatch() {
        return batch;
    }

    public void setBatch(JsonVariant value) {
        this.batch = value;
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
     * @constraint foreign key (region) references violet.region (id)
     */
    @JoinColumn(name = "region")
    @ManyToOne
    @NotNull
    public Region getRegion() {
        return region;
    }

    /**
     */
    public void setRegion(@NotNull Region value) {
        this.region = value;
    }

    @Ordinal(_ord_REGION_ID)
    @Precision(value = 10)
    @Column(name = "region", nullable = false, precision = 10)
    public synchronized int getRegionId() {
        if (region != null) {
            if (region.getId() == null)
                return 0;
            return region.getId();
        }
        return regionId;
    }

    public synchronized void setRegionId(int value) {
        this.regionId = value;
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
        this.quantity = BigDecimal.ZERO;
    }

}
