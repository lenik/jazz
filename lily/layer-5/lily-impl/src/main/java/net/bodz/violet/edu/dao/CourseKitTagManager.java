package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseKitTag;

public class CourseKitTagManager
        extends AbstractEntityManager<CourseKitTag, CourseKitTagMapper> {

    public CourseKitTagManager(DataContext dataContext) {
        super(dataContext, CourseKitTagMapper.class);
    }

}
