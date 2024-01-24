package net.bodz.lily.schema.inet.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.inet.ExternalSite;

public class ExternalSiteManager
        extends AbstractEntityManager<ExternalSite, ExternalSiteMapper> {

    public ExternalSiteManager(DataContext dataContext) {
        super(dataContext, ExternalSiteMapper.class);
    }

}
