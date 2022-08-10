package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.shop.Coupon;
import net.bodz.violet.shop.CouponSamples;

public class CouponMapperTest
        extends AbstractMapperTest<Coupon, CouponMask, CouponMapper> {

    @Override
    public Coupon buildSample() {
        return CouponSamples.build();
    }

}
