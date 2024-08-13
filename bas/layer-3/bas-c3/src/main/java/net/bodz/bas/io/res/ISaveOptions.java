package net.bodz.bas.io.res;

import net.bodz.bas.io.IErrorHandler;

public interface ISaveOptions {

    boolean isPurgeEmpty();

    boolean isCompare();

    boolean isMkdirs();

    String getEncoding();

    IErrorHandler getErrorHandler();

    ISaveOptions DEFAULT = new DefaultSaveOptions();

}