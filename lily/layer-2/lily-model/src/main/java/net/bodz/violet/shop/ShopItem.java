package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.art.Artifact;

/**
 * 陈列商品
 */
@IdType(Long.class)
@Table(name = "shopitem")
public class ShopItem
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_LABEL = 200;

    Shop shop;
    ShopItemCategory category;
    Artifact artifact;

    BigDecimal quantity = BigDecimal.ZERO;
    BigDecimal price = BigDecimal.ZERO;

    /**
     * 所属商店
     */
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * 陈列类别
     */
    public ShopItemCategory getCategory() {
        return category;
    }

    public void setCategory(ShopItemCategory category) {
        this.category = category;
    }

    /**
     * 商品
     */
    @Priority(100)
    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    /**
     * 数量
     */
    @Priority(200)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 价格
     */
    @Priority(201)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 总额
     */
    @Priority(202)
    @Derived
    public BigDecimal getTotal() {
        return price.multiply(quantity);
    }

}