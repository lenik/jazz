package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;

/**
 * 陈列商品
 */
@Table(schema = "violet", name = "shopitem")
public class ShopItem
        extends _ShopItem_stuff {

    private static final long serialVersionUID = 1L;

    public void setQuantity(double quantity) {
        setQuantity(new BigDecimal(quantity));
    }

    public void setPrice(double price) {
        setPrice(new BigDecimal(price));
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
