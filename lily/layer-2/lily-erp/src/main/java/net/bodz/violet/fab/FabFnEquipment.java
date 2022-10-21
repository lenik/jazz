package net.bodz.violet.fab;

import java.math.BigDecimal;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@IdType(Integer.class)
public class FabFnEquipment
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    FabFn fn;
    FabEquipment equipment;
    BigDecimal quantity = BigDecimal.ONE;

    public FabFnEquipment() {
    }

    public FabFn getFn() {
        return fn;
    }

    public void setFn(FabFn fn) {
        this.fn = fn;
    }

    public FabEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(FabEquipment equipment) {
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
