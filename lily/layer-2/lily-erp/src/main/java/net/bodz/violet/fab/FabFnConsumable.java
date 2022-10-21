package net.bodz.violet.fab;

import java.math.BigDecimal;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@IdType(Integer.class)
public class FabFnConsumable
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    FabFn fn;
    FabConsumable consumable;
    BigDecimal quantity = BigDecimal.ONE;

    public FabFnConsumable() {
    }

    public FabFn getFn() {
        return fn;
    }

    public void setFn(FabFn fn) {
        this.fn = fn;
    }

    public FabConsumable getConsumable() {
        return consumable;
    }

    public void setConsumable(FabConsumable consumable) {
        this.consumable = consumable;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
