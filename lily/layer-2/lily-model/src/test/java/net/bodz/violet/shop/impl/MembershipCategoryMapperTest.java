package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.MembershipCategory;
import net.bodz.violet.shop.MembershipCategorySamples;

public class MembershipCategoryMapperTest
        extends AbstractMapperTest<MembershipCategory, MembershipCategoryMask, MembershipCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public MembershipCategory buildSample() {
        return MembershipCategorySamples.build();
    }

}
