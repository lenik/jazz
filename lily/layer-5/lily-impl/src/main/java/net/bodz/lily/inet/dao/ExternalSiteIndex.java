package net.bodz.lily.inet.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.model.base.CoIndex;

/**
* @label ExternalSite
*/
@ObjectType(ExternalSite.class)
public class ExternalSiteIndex
        extends CoIndex<ExternalSite, ExternalSiteCriteriaBuilder> {

    public ExternalSiteIndex() {
    }

}
