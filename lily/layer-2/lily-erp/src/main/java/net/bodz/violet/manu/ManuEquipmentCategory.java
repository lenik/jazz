package net.bodz.violet.manu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class ManuEquipmentCategory
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public ManuEquipmentCategory() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
