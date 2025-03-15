package net.bodz.bas.io.res;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.IErrorHandler;

public class SaveOptions
        implements ISaveOptions {

    boolean updateAll;
    List<Path> updateDirs = new ArrayList<>();
    boolean purgeEmpty;
    boolean saveChangedOnly;
    boolean createParentDirs;
    String encoding;
    IErrorHandler errorHandler;

    public boolean isUpdateAll() {
        return updateAll;
    }

    public void setUpdateAll(boolean updateAll) {
        this.updateAll = updateAll;
    }

    @Override
    public List<Path> getUpdateDirs() {
        return updateDirs;
    }

    public void setUpdateDirs(List<Path> updateDirs) {
        if (updateDirs == null)
            throw new NullPointerException("updateDirs");
        this.updateDirs = updateDirs;
    }

    @Override
    public boolean isPurgeEmpty() {
        return purgeEmpty;
    }

    public void setPurgeEmpty(boolean purgeEmpty) {
        this.purgeEmpty = purgeEmpty;
    }

    @Override
    public boolean isSaveChangedOnly() {
        return saveChangedOnly;
    }

    public void setSaveChangedOnly(boolean saveChangedOnly) {
        this.saveChangedOnly = saveChangedOnly;
    }

    @Override
    public boolean isCreateParentDirs() {
        return createParentDirs;
    }

    public void setCreateParentDirs(boolean createParentDirs) {
        this.createParentDirs = createParentDirs;
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

    static final SaveOptions DEFAULT = new SaveOptions();

    static {
        DEFAULT.purgeEmpty = true;
        DEFAULT.saveChangedOnly = true;
        DEFAULT.createParentDirs = true;
    }

}