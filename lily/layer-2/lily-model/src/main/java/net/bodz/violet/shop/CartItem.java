package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.t.struct.IOrderItem;
import net.bodz.violet.art.Artifact;

/**
 * 购物车项
 */
@IdType(Long.class)
@Table(name = "cartitem")
public class CartItem
        extends CoEntity<Long>
        implements IOrderItem {

    private static final long serialVersionUID = 1L;

    ShopItem shopItem;
    BigDecimal price = BigDecimal.ZERO;
    BigDecimal quantity = BigDecimal.ZERO;
    BigDecimal amount;

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
     * 数量
     */
    @Override
    @Priority(200)
    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public synchronized void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        this.amount = null;
    }

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    /**
     * 价格 (当时价格)
     */
    @Override
    @Priority(201)
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public synchronized void setPrice(BigDecimal price) {
        this.price = price;
        this.amount = null;
    }

    public void setPrice(double price) {
        setPrice(BigDecimal.valueOf(price));
    }

    /**
     * 总额
     */
    @Derived
    @Override
    @Priority(202)
    public synchronized BigDecimal getAmount() {
        if (amount == null)
            amount = price.multiply(quantity);
        return amount;
    }

}