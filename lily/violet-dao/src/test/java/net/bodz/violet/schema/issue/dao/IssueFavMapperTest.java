package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.IssueFav;
import net.bodz.violet.schema.issue.IssueFavSamples;

public class IssueFavMapperTest
        extends AbstractTableTest<IssueFav, IssueFavMapper> {

    @Override
    public IssueFav buildSample()
            throws Exception {
        IssueFavSamples a = new IssueFavSamples();
        return a.buildWired(tables);
    }

}
