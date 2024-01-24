package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.CourseFav;

public class CourseFavManager
        extends AbstractEntityManager<CourseFav, CourseFavMapper> {

    public CourseFavManager(DataContext dataContext) {
        super(dataContext, CourseFavMapper.class);
    }

}
