package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.CourseKitFav;

public class CourseKitFavManager
        extends AbstractEntityManager<CourseKitFav, CourseKitFavMapper> {

    public CourseKitFavManager(DataContext dataContext) {
        super(dataContext, CourseKitFavMapper.class);
    }

}
