package net.bodz.bas.program.meta;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.io.res.tools.StreamLoading;
import net.bodz.bas.vfs.impl.url.URLFile;

public class BuildInfoUtil {

    /**
     * @return <code>null</code> if build info is unknown.
     * @throws IOException
     */
    public static Properties getBuildInfo(Class<?> clazz)
            throws IOException {
        String resname = Nullables.getAnnotation(clazz, BuildInfo.class).value();
        if (resname == null)
            return null;
        URL url = clazz.getResource(resname);
        if (url == null)
            throw new NullPointerException("BuildInfo resource isn't existed: " + resname);
        return URLFile.resolve(url).tooling()._for(StreamLoading.class).loadProperties();
    }

}
