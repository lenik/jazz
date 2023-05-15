package net.bodz.lily.app;

import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.lily.storage.IVolumeProvider;

public interface IDataApplication
        extends
            IDataContextAware,
            IVolumeProvider {

    String ATTRIBUTE_KEY = IDataApplication.class.getName();

    ISiteRoot getDefaultSite();

}
