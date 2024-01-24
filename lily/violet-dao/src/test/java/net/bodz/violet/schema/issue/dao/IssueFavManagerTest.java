package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.issue.IssueFav;
import net.bodz.violet.schema.issue.IssueFavSamples;

public class IssueFavManagerTest
        extends AbstractManagerTest<IssueFav, IssueFavMapper, IssueFavManager> {

    @Override
    public IssueFav buildSample()
            throws Exception {
        IssueFavSamples a = new IssueFavSamples();
        return a.buildWired(tables);
    }

}
