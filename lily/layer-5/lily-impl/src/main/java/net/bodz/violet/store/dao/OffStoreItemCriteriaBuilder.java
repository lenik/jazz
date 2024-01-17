package net.bodz.violet.store.dao;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.store.OffStoreItem
 */
public class OffStoreItemCriteriaBuilder
        extends CoObjectCriteriaBuilder {

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
