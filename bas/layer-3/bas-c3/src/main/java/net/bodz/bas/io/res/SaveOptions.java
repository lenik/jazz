package net.bodz.bas.io.res;

import net.bodz.bas.io.IErrorHandler;

public class SaveOptions
        implements
            ISaveOptions {

    boolean purgeEmpty;
    boolean compare;
    boolean mkdirs;
    String encoding;
    IErrorHandler errorHandler;

    @Override
    public boolean isPurgeEmpty() {
        return purgeEmpty;
    }

    public void setPurgeEmpty(boolean purgeEmpty) {
        this.purgeEmpty = purgeEmpty;
    }

    @Override
    public boolean isCompare() {
        return compare;
    }

    public void setCompare(boolean compare) {
        this.compare = compare;
    }

    @Override
    public boolean isMkdirs() {
        return mkdirs;
    }

    public void setMkdirs(boolean mkdirs) {
        this.mkdirs = mkdirs;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public IErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(IErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

}
