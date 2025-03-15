package net.bodz.bas.io.res;

import java.nio.file.Path;
import java.util.List;

import net.bodz.bas.io.IErrorHandler;

public interface ISaveOptions {

    boolean isUpdateAll();

    List<Path> getUpdateDirs();

    boolean isPurgeEmpty();

    boolean isSaveChangedOnly();

    boolean isCreateParentDirs();

    String getEncoding();

    IErrorHandler getErrorHandler();

    ISaveOptions DEFAULT = SaveOptions.DEFAULT;

}