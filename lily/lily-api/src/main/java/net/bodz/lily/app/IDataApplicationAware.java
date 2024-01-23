package net.bodz.lily.app;

import net.bodz.bas.err.ReadOnlyException;

public interface IDataApplicationAware {

    IDataApplication getDataApp();

    default void setDataApp(IDataApplication dataApp) {
        throw new ReadOnlyException();
    }

}
