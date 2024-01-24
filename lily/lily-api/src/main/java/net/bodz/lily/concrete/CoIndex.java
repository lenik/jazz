package net.bodz.lily.concrete;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.ws.AbstractEntityController;

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
