package net.bodz.lily.model.mx;

import java.io.File;

import net.bodz.lily.model.base.CoIndex;

public abstract class CoMessageIndex<T extends CoMessage<?>, M extends CoMessageMask>
        extends CoIndex<T, M> {

    @Override
    protected void renameUrlAsFileChange(T obj, String relativePath, File oldName, File newName) {
        String html = obj.getText();

    }

}
