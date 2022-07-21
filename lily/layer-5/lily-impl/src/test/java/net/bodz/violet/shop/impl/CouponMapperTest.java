package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.Coupon;
import net.bodz.violet.shop.CouponSamples;

public class CouponMapperTest
        extends AbstractMapperTest<Coupon, CouponMask, CouponMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Coupon buildSample() {
        return CouponSamples.build();
    }

}
