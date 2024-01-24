package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseKitCategory;
import net.bodz.violet.schema.edu.CourseKitCategorySamples;

public class CourseKitCategoryMapperTest
        extends AbstractTableTest<CourseKitCategory, CourseKitCategoryMapper> {

    @Override
    public CourseKitCategory buildSample()
            throws Exception {
        CourseKitCategorySamples a = new CourseKitCategorySamples();
        return a.buildWired(tables);
    }

}
