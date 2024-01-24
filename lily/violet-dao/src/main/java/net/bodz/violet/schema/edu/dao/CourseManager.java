package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.Course;

public class CourseManager
        extends AbstractEntityManager<Course, CourseMapper> {

    public CourseManager(DataContext dataContext) {
        super(dataContext, CourseMapper.class);
    }

}
