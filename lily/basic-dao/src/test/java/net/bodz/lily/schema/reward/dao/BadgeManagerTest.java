package net.bodz.lily.schema.reward.dao;

import net.bodz.lily.schema.reward.Badge;
import net.bodz.lily.schema.reward.BadgeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class BadgeManagerTest
        extends AbstractManagerTest<Badge, BadgeMapper, BadgeManager> {

    @Override
    public Badge buildSample()
            throws Exception {
        BadgeSamples a = new BadgeSamples();
        return a.buildWired(tables);
    }

}
