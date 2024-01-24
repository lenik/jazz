package net.bodz.violet.schema.shop;

import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public class Coupon
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public Coupon() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
