package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestQuestionFav;

public class TestQuestionFavManager
        extends AbstractEntityManager<TestQuestionFav, TestQuestionFavMapper> {

    public TestQuestionFavManager(DataContext dataContext) {
        super(dataContext, TestQuestionFavMapper.class);
    }

}
