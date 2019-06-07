package net.bodz.violet.store.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.store.StoreOrderItem
 */
public class StoreOrderItemMask
        extends CoObjectMask {

    Long orderId;

    Integer artifactId;
    Integer regionId;

    DoubleRange quantityRange;
    DoubleRange priceRange;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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

    public DoubleRange getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(DoubleRange quantityRange) {
        this.quantityRange = quantityRange;
    }

    public DoubleRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(DoubleRange priceRange) {
        this.priceRange = priceRange;
    }

}
