package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.CourseFav;
import net.bodz.violet.edu.CourseFavSamples;

public class CourseFavManagerTest
        extends AbstractManagerTest<CourseFav, CourseFavMapper, CourseFavManager> {

    @Override
    public CourseFav buildSample()
            throws Exception {
        CourseFavSamples a = new CourseFavSamples();
        return a.buildWired(tables);
    }

}
