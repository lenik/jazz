package net.bodz.violet.shop.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.shop.CartItem
 */
public class CartItemMask
        extends CoObjectMask {

    Long shopItemId;
    DoubleRange priceSnapshotRange;
    DoubleRange quantityRange;

    public Long getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(Long shopItemId) {
        this.shopItemId = shopItemId;
    }

    public DoubleRange getPriceSnapshotRange() {
        return priceSnapshotRange;
    }

    public void setPriceSnapshotRange(DoubleRange priceSnapshotRange) {
        this.priceSnapshotRange = priceSnapshotRange;
    }

    public DoubleRange getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(DoubleRange quantityRange) {
        this.quantityRange = quantityRange;
    }

}
