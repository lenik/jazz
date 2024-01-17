package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseTag;

public class CourseTagManager
        extends AbstractEntityManager<CourseTag, CourseTagMapper> {

    public CourseTagManager(DataContext dataContext) {
        super(dataContext, CourseTagMapper.class);
    }

}
