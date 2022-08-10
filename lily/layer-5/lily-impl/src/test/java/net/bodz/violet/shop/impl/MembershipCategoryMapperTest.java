package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.shop.MembershipCategory;
import net.bodz.violet.shop.MembershipCategorySamples;

public class MembershipCategoryMapperTest
        extends AbstractMapperTest<MembershipCategory, MembershipCategoryMask, MembershipCategoryMapper> {

    @Override
    public MembershipCategory buildSample() {
        return MembershipCategorySamples.build();
    }

}
