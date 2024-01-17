package net.bodz.lily.reward.dao;

import net.bodz.lily.reward.Badge;
import net.bodz.lily.reward.BadgeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class BadgeMapperTest
        extends AbstractTableTest<Badge, BadgeCriteriaBuilder, BadgeMapper> {

    @Override
    public Badge buildSample()
            throws Exception {
        BadgeSamples a = new BadgeSamples();
        return a.build();
    }

}
