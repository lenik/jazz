package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.Coupon;
import net.bodz.violet.shop.CouponSamples;

public class CouponMapperTest
        extends AbstractTableTest<Coupon, CouponMask, CouponMapper> {

    @Override
    public Coupon buildSample() {
        return CouponSamples.build();
    }

}
