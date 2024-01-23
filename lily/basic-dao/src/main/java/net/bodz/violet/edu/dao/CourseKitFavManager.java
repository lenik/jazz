package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseKitFav;

public class CourseKitFavManager
        extends AbstractEntityManager<CourseKitFav, CourseKitFavMapper> {

    public CourseKitFavManager(DataContext dataContext) {
        super(dataContext, CourseKitFavMapper.class);
    }

}
