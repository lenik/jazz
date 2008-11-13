package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.lang.util.Classpath;

public class _LoadConfig implements LoadConfig {

    @Override
    public ClassLoader getLoader(ClassLoader parent) {
        return parent;
    }

    protected ClassLoader addLibs(ClassLoader parent, String... libspecs) {
        URLClassLoader ucl = LoadUtil.getUcl(parent);
        for (String libspec : libspecs) {
            URL url = LoadUtil.findLib(libspec);
            Classpath.addURL(ucl, url);
        }
        return ucl;
    }

    @Override
    public void load(int stageFrom, int stageTo) {
    }

    @Override
    public void loadAll() {
        load(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

}
