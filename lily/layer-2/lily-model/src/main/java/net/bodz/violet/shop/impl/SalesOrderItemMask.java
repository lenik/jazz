package net.bodz.violet.shop.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectMask;

public class SalesOrderItemMask
        extends CoObjectMask {

    Long orderId;
    Integer artifactId;

    Boolean resale;
    String altLabelPattern;
    String altSpecPattern;
    String altUomPattern;

    DoubleRange quantityRange;
    DoubleRange priceRange;
    String footnotePattern;

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

    public Boolean getResale() {
        return resale;
    }

    public void setResale(Boolean resale) {
        this.resale = resale;
    }

    public String getAltLabelPattern() {
        return altLabelPattern;
    }

    public void setAltLabelPattern(String altLabelPattern) {
        this.altLabelPattern = altLabelPattern;
    }

    public String getAltSpecPattern() {
        return altSpecPattern;
    }

    public void setAltSpecPattern(String altSpecPattern) {
        this.altSpecPattern = altSpecPattern;
    }

    public String getAltUomPattern() {
        return altUomPattern;
    }

    public void setAltUomPattern(String altUomPattern) {
        this.altUomPattern = altUomPattern;
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

    public String getFootnotePattern() {
        return footnotePattern;
    }

    public void setFootnotePattern(String footnotePattern) {
        this.footnotePattern = footnotePattern;
    }

}
