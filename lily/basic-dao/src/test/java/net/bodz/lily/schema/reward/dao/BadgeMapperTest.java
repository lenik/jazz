package net.bodz.lily.schema.reward.dao;

import net.bodz.lily.schema.reward.Badge;
import net.bodz.lily.schema.reward.BadgeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class BadgeMapperTest
        extends AbstractTableTest<Badge, BadgeMapper> {

    @Override
    public Badge buildSample()
            throws Exception {
        BadgeSamples a = new BadgeSamples();
        return a.buildWired(tables);
    }

}
