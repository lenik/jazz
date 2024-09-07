package net.bodz.violet.schema.asset;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import net.bodz.lily.concrete.CoEvent;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.store.Region;

/**
 * 资产
 */
@IdType(Long.class)
public abstract class AbstractAsset
        extends CoEvent<Long> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    Integer artifactId;

    Region region;
    Integer regionId;

    // Batch complex
    BigDecimal quantity = BigDecimal.ZERO;
    Long serial;
    ZonedDateTime expire;

    public AbstractAsset() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public synchronized Integer getArtifactId() {
        if (artifact != null) {
            return artifact.getId();
        }
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public synchronized Integer getRegionId() {
        if (region != null) {
            return region.getId();
        }
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public ZonedDateTime getExpire() {
        return expire;
    }

    public void setExpire(ZonedDateTime expire) {
        this.expire = expire;
    }

}
