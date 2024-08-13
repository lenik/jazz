package net.bodz.bas.io.res;

import net.bodz.bas.io.IErrorHandler;

public class DefaultSaveOptions
        implements
            ISaveOptions {

    @Override
    public boolean isPurgeEmpty() {
        return true;
    }

    @Override
    public boolean isCompare() {
        return true;
    }

    @Override
    public boolean isMkdirs() {
        return true;
    }

    @Override
    public String getEncoding() {
        return null;
    }

    @Override
    public IErrorHandler getErrorHandler() {
        return null;
    }

}
