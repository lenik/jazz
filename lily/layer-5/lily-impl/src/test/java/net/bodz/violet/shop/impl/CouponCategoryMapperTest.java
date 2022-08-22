package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.CouponCategory;
import net.bodz.violet.shop.CouponCategorySamples;

public class CouponCategoryMapperTest
        extends AbstractTableTest<CouponCategory, CouponCategoryMask, CouponCategoryMapper> {

    @Override
    public CouponCategory buildSample() {
        return CouponCategorySamples.build();
    }

}
