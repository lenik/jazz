package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.template.CoCategory;

@Table(name = "couponcat")
public class CouponCategory
        extends CoCategory<CouponCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public CouponCategory() {
    }

}
