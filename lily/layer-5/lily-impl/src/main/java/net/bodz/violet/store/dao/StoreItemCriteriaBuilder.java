package net.bodz.violet.store.dao;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.store.StoreItem
 */
public class StoreItemCriteriaBuilder
        extends CoObjectCriteriaBuilder {

    Integer artifactId;
    Integer regionId;
    DoubleRange quantity;

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public DoubleRange getQuantity() {
        return quantity;
    }

    public void setQuantity(DoubleRange quantity) {
        this.quantity = quantity;
    }

}
