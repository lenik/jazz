package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 购物车项
 */
@IdType(Long.class)
@Table(name = "cartitem")
public class CartItem
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    ShopItem shopItem;
    double priceSnapshot;
    double quantity;

    /**
     * 商品
     */
    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    /**
     * 当时价格
     */
    public double getPriceSnapshot() {
        return priceSnapshot;
    }

    public void setPriceSnapshot(double priceSnapshot) {
        this.priceSnapshot = priceSnapshot;
    }

    public double getPrice() {
        if (shopItem == null)
            return 0;
        else
            return shopItem.getPrice();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        double price = getPrice();
        return price * quantity;
    }

}