package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseCategory;

public class CourseCategoryManager
        extends AbstractEntityManager<CourseCategory, CourseCategoryMapper> {

    public CourseCategoryManager(DataContext dataContext) {
        super(dataContext, CourseCategoryMapper.class);
    }

}
