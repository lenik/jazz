package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueFav;
import net.bodz.violet.issue.IssueFavSamples;

public class IssueFavMapperTest
        extends AbstractTableTest<IssueFav, IssueFavMapper> {

    @Override
    public IssueFav buildSample()
            throws Exception {
        IssueFavSamples a = new IssueFavSamples();
        return a.buildWired(tables);
    }

}
