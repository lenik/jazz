package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestQuestionFav;

public class TestQuestionFavManager
        extends AbstractEntityManager<TestQuestionFav, TestQuestionFavMapper> {

    public TestQuestionFavManager(DataContext dataContext) {
        super(dataContext, TestQuestionFavMapper.class);
    }

}
