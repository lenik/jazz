package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

/**
 * 购物车项
 */
@IdType(Long.class)
@Table(name = "cartitem")
public class CartItem
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    ShopItem shopItem;
    BigDecimal priceSnapshot = BigDecimal.ZERO;
    BigDecimal quantity = BigDecimal.ZERO;

    /**
     * 商品
     */
    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    public Artifact getArtifact() {
        if (shopItem == null)
            return null;
        else
            return shopItem.getArtifact();
    }

    /**
     * 当时价格
     */
    public BigDecimal getPriceSnapshot() {
        return priceSnapshot;
    }

    public void setPriceSnapshot(BigDecimal priceSnapshot) {
        this.priceSnapshot = priceSnapshot;
    }

    public BigDecimal getPrice() {
        if (shopItem == null)
            return BigDecimal.ZERO;
        else
            return shopItem.getPrice();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        BigDecimal price = getPrice();
        return price.multiply(quantity);
    }

}