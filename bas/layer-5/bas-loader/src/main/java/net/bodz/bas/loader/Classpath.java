package net.bodz.bas.loader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.sio.IPrintOut;

public class Classpath {

    static Logger logger;
    static ILogSink out = NullLogSink.getInstance();

    /**
     * @return <code>false</code> if url is existed.
     */
    public static int addURL(ClassLoader loader, URL... urls) {
        if (!(loader instanceof URLClassLoader))
            throw new UnsupportedOperationException("can\'t addURL to " + loader.getClass());
        URLClassLoader ucl = (URLClassLoader) loader;
        int count = 0;
        for (URL url : urls) {
            int added = URLClassLoaders.addURL(ucl, url);
            if (added != 0) {
                count += added;
                out.p("addURL ", url, " -> ", ucl);
            }
        }
        return count;
    }

    /**
     * @return <code>false</code> if url is existed.
     */
    public static int addURL(URL... urls)
            throws IOException {
        return addURL(ClassLoader.getSystemClassLoader(), urls);
    }

    public static void dumpURLs(IPrintOut out) {
        URLClassLoaders.dump(Caller.getCallerClassLoader(0), out);
        out.flush();
    }

    public static String dumpURLs() {
        return URLClassLoaders.dump(Caller.getCallerClassLoader(0));
    }

}
