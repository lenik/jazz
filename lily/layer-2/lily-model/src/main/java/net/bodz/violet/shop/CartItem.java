package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.lily.t.struct.IOrderItem;
import net.bodz.violet.art.Artifact;

/**
 * 购物车项
 */
@Table(schema = "violet", name = "cartitem")
public class CartItem
        extends _CartItem_stuff
        implements
            IOrderItem {

    private static final long serialVersionUID = 1L;

    public Artifact getArtifact() {
        if (shopItem == null)
            return null;
        else
            return shopItem.getArtifact();
    }

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    @Override
    public void setPrice(double price) {
        setPrice(BigDecimal.valueOf(price));
    }

    /**
     * 总额
     */
    @Override
    @Derived
    @Priority(202)
    public synchronized BigDecimal getAmount() {
        return price.multiply(quantity);
    }

}
