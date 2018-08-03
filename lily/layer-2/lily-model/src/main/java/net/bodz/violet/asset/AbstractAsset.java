package net.bodz.violet.asset;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

/**
 * 资产
 */
@IdType(Long.class)
public abstract class AbstractAsset
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    Region region;
    // Batch complex
    BigDecimal quantity = BigDecimal.ZERO;
    Long serial;
    DateTime expire;

    public AbstractAsset() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public DateTime getExpire() {
        return expire;
    }

    public void setExpire(DateTime expire) {
        this.expire = expire;
    }

}
