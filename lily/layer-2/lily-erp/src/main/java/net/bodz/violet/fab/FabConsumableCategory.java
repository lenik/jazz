package net.bodz.violet.fab;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class FabConsumableCategory
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public FabConsumableCategory() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
