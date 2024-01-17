package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseKit;

public class CourseKitManager
        extends AbstractEntityManager<CourseKit, CourseKitMapper> {

    public CourseKitManager(DataContext dataContext) {
        super(dataContext, CourseKitMapper.class);
    }

}
