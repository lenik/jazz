package net.bodz.violet.store;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

@IdType(Long.class)
public abstract class _StoreItem_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "storel";

    public static final String FIELD_ID = "id";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_REGION_ID = "region";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_QUANTITY = "qty";

    public static final int N_ID = 19;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_REGION_ID = 10;
    public static final int N_BATCH = 2147483647;
    public static final int N_QUANTITY = 20;

    private static final int _ord_ID = 1;
    private static final int _ord_ARTIFACT_ID = 12;
    private static final int _ord_REGION_ID = _ord_ARTIFACT_ID + 1;
    private static final int _ord_BATCH = _ord_REGION_ID + 1;
    private static final int _ord_QUANTITY = _ord_BATCH + 1;

    @Id
    @NotNull
    long id;

    Object batch;

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

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
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
     * @label region
     * @constraint foreign key (region) references violet.region (id)
     */
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
        this.quantity = BigDecimal.ZERO;
    }

}
