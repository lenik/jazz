package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.CourseKitTag;

public class CourseKitTagManager
        extends AbstractEntityManager<CourseKitTag, CourseKitTagMapper> {

    public CourseKitTagManager(DataContext dataContext) {
        super(dataContext, CourseKitTagMapper.class);
    }

}
