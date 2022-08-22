package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseFav;
import net.bodz.violet.edu.CourseFavSamples;

public class CourseFavMapperTest
        extends AbstractTableTest<CourseFav, CourseFavMask, CourseFavMapper> {

    @Override
    public CourseFav buildSample() {
        return CourseFavSamples.build();
    }

}
