package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.CourseFav;
import net.bodz.violet.edu.CourseFavSamples;

public class CourseFavMapperTest
        extends AbstractMapperTest<CourseFav, CourseFavMask, CourseFavMapper> {

    @Override
    public CourseFav buildSample() {
        return CourseFavSamples.build();
    }

}
