package net.bodz.violet.schema.edu;

import javax.persistence.Table;

@Table(schema = CourseCategory.SCHEMA_NAME, name = CourseCategory.TABLE_NAME)
public class CourseCategory
        extends _CourseCategory_stuff<CourseCategory> {

    private static final long serialVersionUID = 1L;

}
