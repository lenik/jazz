package net.bodz.swt.program;

import java.net.URL;

import net.bodz.bas.loader.BundledLoader;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.loader.UCL;
import net.bodz.bas.loader._LoadConfig;
import net.bodz.bas.snm.SJEclipse;
import net.bodz.swt.reflect.nls.GUINLS;

public class SWTConfig
        extends _LoadConfig {

    @Override
    public ClassLoader getLoader(ClassLoader parent) {
        BundledLoader bl = BundledLoader.replace(parent);
        URL[] urls = LoadUtil.find("swt_win32"); //$NON-NLS-1$
        if (urls == null) {
            URL url = SJEclipse.findlib("org.eclipse.swt.win32.win32.x86_"); //$NON-NLS-1$
            if (url == null)
                throw new Error(GUINLS.getString("SWTConfig.cantFindSWTLib")); //$NON-NLS-1$
            UCL.addURL(bl, url);
        } else
            UCL.addURL(bl, urls);
        return bl;
    }

}
