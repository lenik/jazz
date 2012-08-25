package net.bodz.bas.loader;

import java.net.URL;

import net.bodz.bas.c.java.net.URLClassLoaders;

public class _LoadConfig
        implements LoadConfig {

    @Override
    public ClassLoader getLoader(ClassLoader parent)
            throws LoadException {
        return parent;
    }

    protected ClassLoader addLibs(ClassLoader parent, String... libspecs) {
        URL[] urls = LoadUtil.find(libspecs);
        assert urls != null : "findLibs returns null";
        return URLClassLoaders.addOrCreate(parent, urls);
    }

    @Override
    public void load(int stageFrom, int stageTo)
            throws LoadException {
    }

    @Override
    public void loadAll()
            throws LoadException {
        load(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

}
