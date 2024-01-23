package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.IssueFav;
import net.bodz.violet.issue.IssueFavSamples;

public class IssueFavManagerTest
        extends AbstractManagerTest<IssueFav, IssueFavMapper, IssueFavManager> {

    @Override
    public IssueFav buildSample()
            throws Exception {
        IssueFavSamples a = new IssueFavSamples();
        return a.buildWired(tables);
    }

}
