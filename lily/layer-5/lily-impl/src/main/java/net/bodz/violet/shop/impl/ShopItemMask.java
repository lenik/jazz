package net.bodz.violet.shop.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoMomentIntervalMask;

/**
 * @see net.bodz.violet.shop.ShopItem
 */
public class ShopItemMask
        extends CoMomentIntervalMask {

    Integer shopId;
    Integer categoryId;
    Integer artifactId;

    DoubleRange quantity;
    DoubleRange price;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public DoubleRange getQuantity() {
        return quantity;
    }

    public void setQuantity(DoubleRange quantity) {
        this.quantity = quantity;
    }

    public DoubleRange getPrice() {
        return price;
    }

    public void setPrice(DoubleRange price) {
        this.price = price;
    }

}
