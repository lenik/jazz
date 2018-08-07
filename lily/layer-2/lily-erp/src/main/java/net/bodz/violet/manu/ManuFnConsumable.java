package net.bodz.violet.manu;

import java.math.BigDecimal;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class ManuFnConsumable
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    ManuFn fn;
    ManuConsumable consumable;
    BigDecimal quantity = BigDecimal.ONE;

    public ManuFnConsumable() {
    }

    public ManuFn getFn() {
        return fn;
    }

    public void setFn(ManuFn fn) {
        this.fn = fn;
    }

    public ManuConsumable getConsumable() {
        return consumable;
    }

    public void setConsumable(ManuConsumable consumable) {
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
