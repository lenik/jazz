package net.bodz.violet.schema.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.issue.IssueCategory;

public class IssueCategoryManager
        extends AbstractEntityManager<IssueCategory, IssueCategoryMapper> {

    public IssueCategoryManager(DataContext dataContext) {
        super(dataContext, IssueCategoryMapper.class);
    }

}
