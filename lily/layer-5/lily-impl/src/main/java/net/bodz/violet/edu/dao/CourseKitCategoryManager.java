package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseKitCategory;

public class CourseKitCategoryManager
        extends AbstractEntityManager<CourseKitCategory, CourseKitCategoryMapper> {

    public CourseKitCategoryManager(DataContext dataContext) {
        super(dataContext, CourseKitCategoryMapper.class);
    }

}
