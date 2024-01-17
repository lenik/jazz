package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.CourseFav;

public class CourseFavManager
        extends AbstractEntityManager<CourseFav, CourseFavMapper> {

    public CourseFavManager(DataContext dataContext) {
        super(dataContext, CourseFavMapper.class);
    }

}
