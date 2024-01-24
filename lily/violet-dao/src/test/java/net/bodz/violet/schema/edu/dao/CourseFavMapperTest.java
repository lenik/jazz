package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseFav;
import net.bodz.violet.schema.edu.CourseFavSamples;

public class CourseFavMapperTest
        extends AbstractTableTest<CourseFav, CourseFavMapper> {

    @Override
    public CourseFav buildSample()
            throws Exception {
        CourseFavSamples a = new CourseFavSamples();
        return a.buildWired(tables);
    }

}
