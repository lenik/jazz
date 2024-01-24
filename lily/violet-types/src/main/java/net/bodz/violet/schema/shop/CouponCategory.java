package net.bodz.violet.schema.shop;

import javax.persistence.Table;

import net.bodz.lily.concrete.CoCategory;

@Table(name = "couponcat")
public class CouponCategory
        extends CoCategory<CouponCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public CouponCategory() {
    }

}
