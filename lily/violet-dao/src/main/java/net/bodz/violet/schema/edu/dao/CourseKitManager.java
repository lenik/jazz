package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.CourseKit;

public class CourseKitManager
        extends AbstractEntityManager<CourseKit, CourseKitMapper> {

    public CourseKitManager(DataContext dataContext) {
        super(dataContext, CourseKitMapper.class);
    }

}
