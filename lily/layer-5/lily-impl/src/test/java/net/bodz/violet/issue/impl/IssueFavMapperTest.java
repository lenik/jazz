package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueFav;
import net.bodz.violet.issue.IssueFavSamples;

public class IssueFavMapperTest
        extends AbstractTableTest<IssueFav, IssueFavMask, IssueFavMapper> {

    @Override
    public IssueFav buildSample() {
        return IssueFavSamples.build();
    }

}
