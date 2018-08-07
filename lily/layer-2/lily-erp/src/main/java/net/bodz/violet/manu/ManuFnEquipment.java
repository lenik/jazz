package net.bodz.violet.manu;

import java.math.BigDecimal;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class ManuFnEquipment
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    ManuFn fn;
    ManuEquipment equipment;
    BigDecimal quantity = BigDecimal.ONE;

    public ManuFnEquipment() {
    }

    public ManuFn getFn() {
        return fn;
    }

    public void setFn(ManuFn fn) {
        this.fn = fn;
    }

    public ManuEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(ManuEquipment equipment) {
        this.equipment = equipment;
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
