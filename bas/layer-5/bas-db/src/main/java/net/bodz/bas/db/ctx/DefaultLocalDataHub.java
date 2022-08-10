package net.bodz.bas.db.ctx;

import java.io.File;

import net.bodz.bas.meta.decl.Priority;

public class DefaultLocalDataHub
        extends LocalDataHub {

    public DefaultLocalDataHub(File folder) {
        super(folder);
    }

    @Override
    public int getPriority() {
        return Priority.LOW;
    }

}
