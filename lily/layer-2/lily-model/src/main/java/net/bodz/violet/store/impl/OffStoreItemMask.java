package net.bodz.violet.store.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.store.OffStoreItem
 */
public class OffStoreItemMask
        extends CoObjectMask {

    Integer artifactId;
    // String batch;
    DoubleRange quantityRange;

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public DoubleRange getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(DoubleRange quantityRange) {
        this.quantityRange = quantityRange;
    }

}
