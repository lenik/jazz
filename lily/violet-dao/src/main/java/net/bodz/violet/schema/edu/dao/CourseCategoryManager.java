package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.CourseCategory;

public class CourseCategoryManager
        extends AbstractEntityManager<CourseCategory, CourseCategoryMapper> {

    public CourseCategoryManager(DataContext dataContext) {
        super(dataContext, CourseCategoryMapper.class);
    }

}
