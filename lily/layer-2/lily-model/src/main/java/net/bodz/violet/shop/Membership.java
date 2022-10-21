package net.bodz.violet.shop;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@IdType(Integer.class)
public class Membership
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public Membership() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
