package net.bodz.bas.lang.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.io.term.Terminals;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.loader.UCL;
import net.bodz.bas.nls.LangNLS;

public class Classpath {

    static Terminal out = Terminals.nil;

    /**
     * @return <code>false</code> if url is existed.
     */
    public static int addURL(ClassLoader loader, URL... urls) {
        if (!(loader instanceof URLClassLoader))
            throw new UnsupportedOperationException(LangNLS
                    .getString("Classpath.cantAddURL") //$NON-NLS-1$
                    + loader.getClass());
        URLClassLoader ucl = (URLClassLoader) loader;
        int count = 0;
        for (URL url : urls) {
            int added = UCL.addURL(ucl, url);
            if (added != 0) {
                count += added;
                out.p("addURL ", url, " -> ", ucl); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        return count;
    }

    /**
     * @return <code>false</code> if url is existed.
     */
    public static int addURL(URL... urls) throws IOException {
        return addURL(ClassLoader.getSystemClassLoader(), urls);
    }

    public static void dumpURLs(CharOut out) {
        UCL.dump(Caller.getCallerClassLoader(), out);
        out.flush();
    }

    public static String dumpURLs() {
        return UCL.dump(Caller.getCallerClassLoader());
    }

}
