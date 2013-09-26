package net.bodz.bas.program.meta;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URL;
import java.util.Properties;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.io.res.tools.StreamLoading;
import net.bodz.bas.vfs.impl.url.URLFile;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuildInfo {

    /**
     * Resource name, which contains counters
     */
    String value();

    class fn {

        /**
         * @return <code>null</code> if build info is unknown.
         * @throws IOException
         */
        public static Properties resolve(Class<?> clazz)
                throws IOException {
            String resname = Nullables.getAnnotation(clazz, BuildInfo.class).value();
            if (resname == null)
                return null;
            URL url = clazz.getResource(resname);
            if (url == null)
                throw new NullPointerException("BuildInfo resource isn't existed: " + resname);
            return URLFile.resolve(url).to(StreamLoading.class).loadProperties();
        }

    }

}
