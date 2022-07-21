package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.CouponCategory;
import net.bodz.violet.shop.CouponCategorySamples;

public class CouponCategoryMapperTest
        extends AbstractMapperTest<CouponCategory, CouponCategoryMask, CouponCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public CouponCategory buildSample() {
        return CouponCategorySamples.build();
    }

}
