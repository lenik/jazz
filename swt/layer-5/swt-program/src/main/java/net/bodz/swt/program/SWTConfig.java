package net.bodz.swt.program;

import java.net.URL;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.loader.BundledLoader;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.loader._LoadConfig;
import net.bodz.bas.snm.SJEclipse;

public class SWTConfig
        extends _LoadConfig
        implements II18nCapable {

    @Override
    public ClassLoader getLoader(ClassLoader parent) {
        BundledLoader bl = BundledLoader.replace(parent);
        URL[] urls = LoadUtil.find("swt_win32");
        if (urls == null) {
            URL url = SJEclipse.findlib("org.eclipse.swt.win32.win32.x86_");
            if (url == null)
                throw new Error(tr._("Can\'t find eclipse-swt lib."));
            URLClassLoaders.addURL(bl, url);
        } else
            URLClassLoaders.addURL(bl, urls);
        return bl;
    }

}
