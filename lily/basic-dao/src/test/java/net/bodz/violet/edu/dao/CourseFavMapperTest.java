package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseFav;
import net.bodz.violet.edu.CourseFavSamples;

public class CourseFavMapperTest
        extends AbstractTableTest<CourseFav, CourseFavMapper> {

    @Override
    public CourseFav buildSample()
            throws Exception {
        CourseFavSamples a = new CourseFavSamples();
        return a.buildWired(tables);
    }

}
