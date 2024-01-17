package net.bodz.lily.model.base;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;

public abstract class CoIndex<T>
        extends AbstractEntityController<T> {

    static final Logger logger = LoggerFactory.getLogger(CoIndex.class);

    public CoIndex() {
    }

    @Override
    public IDataApplication getDataApp() {
        return DataApps.fromRequest();
    }

}
