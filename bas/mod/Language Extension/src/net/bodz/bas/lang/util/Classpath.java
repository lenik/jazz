package net.bodz.bas.lang.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.loader.UCL;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;

public class Classpath {

    static LogOut out = LogOuts.debug;

    /**
     * @return <code>false</code> if url is existed.
     */
    public static boolean addURL(ClassLoader loader, URL url) {
        if (!(loader instanceof URLClassLoader))
            throw new UnsupportedOperationException("can't addURL to "
                    + loader.getClass());
        URLClassLoader ucl = (URLClassLoader) loader;
        boolean added = UCL.addURL(ucl, url);
        if (added)
            out.P("addURL ", url, " -> ", ucl);
        return added;
    }

    /**
     * @return <code>false</code> if url is existed.
     */
    public static boolean addURL(URL url) throws IOException {
        return addURL(ClassLoader.getSystemClassLoader(), url);
    }

    public static void dumpURLs(CharOut out) {
        UCL.dump(Caller.getCallerClassLoader(), out);
    }

    public static String dumpURLs() {
        return UCL.dump(Caller.getCallerClassLoader());
    }

}
