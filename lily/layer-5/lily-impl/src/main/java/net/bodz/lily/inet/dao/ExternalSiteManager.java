package net.bodz.lily.inet.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.inet.ExternalSite;

public class ExternalSiteManager
        extends AbstractEntityManager<ExternalSite, ExternalSiteMapper> {

    public ExternalSiteManager(DataContext dataContext) {
        super(dataContext, ExternalSiteMapper.class);
    }

}
