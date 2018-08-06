package net.bodz.lily.model.mixin;

import java.math.BigDecimal;

public interface IOrderItem {

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    void setPrice(double price);

    BigDecimal getQuantity();

    void setQuantity(BigDecimal quantity);

    void setQuantity(double quantity);

    /**
     * Returns price * quantity.
     */
    BigDecimal getAmount();

}
