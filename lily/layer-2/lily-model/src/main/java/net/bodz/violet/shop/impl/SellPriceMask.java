package net.bodz.violet.shop.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.shop.SellPrice
 */
public class SellPriceMask
        extends CoObjectMask {

    Integer artifactId;
    DoubleRange buyPriceRange;
    DoubleRange sellPriceRange;

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public DoubleRange getBuyPriceRange() {
        return buyPriceRange;
    }

    public void setBuyPriceRange(DoubleRange buyPriceRange) {
        this.buyPriceRange = buyPriceRange;
    }

    public DoubleRange getSellPriceRange() {
        return sellPriceRange;
    }

    public void setSellPriceRange(DoubleRange sellPriceRange) {
        this.sellPriceRange = sellPriceRange;
    }

}
