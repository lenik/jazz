package net.bodz.bas.loader;

import java.net.URL;

public class _LoadConfig implements LoadConfig {

    @Override
    public ClassLoader getLoader(ClassLoader parent) {
        return parent;
    }

    protected ClassLoader addLibs(ClassLoader parent, String... libspecs) {
        URL[] urls = LoadUtil.findLibs(libspecs);
        return UCL.addOrCreate(parent, urls);
    }

    @Override
    public void load(int stageFrom, int stageTo) {
    }

    @Override
    public void loadAll() {
        load(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

}
