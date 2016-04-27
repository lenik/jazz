package net.bodz.bas.db.ibatis;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.PackageError;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;

public class IncludeMapperXmlConfigurer
        extends AbstractIbatisConfigurer {

    @Override
    public void configure(Configuration config) {
        for (Class<?> declaringClass : IndexedTypes.list(IncludeMapperXml.class, true)) {
            String packagePath = declaringClass.getPackage().getName().replace('.', '/');

            IncludeMapperXml aIncludeMapperXml = declaringClass.getAnnotation(IncludeMapperXml.class);
            // aIncludeMapperXml.priority();

            String[] names = aIncludeMapperXml.value();
            if (names.length == 0)
                names = new String[] { declaringClass.getSimpleName() + ".xml" };

            for (String name : names) {
                String pathName = packagePath + "/" + name;
                byte[] data = readClassResource(declaringClass, name);
                InputStream in = new ByteArrayInputStream(data);
                XMLMapperBuilder xmb = new XMLMapperBuilder(in, config, pathName, config.getSqlFragments());
                xmb.parse();
            }
        }
    }

    protected byte[] readClassResource(Class<?> clazz, String name) {
        URL url = clazz.getResource(name);
        if (url == null)
            throw new IllegalArgumentException(String.format("Bad resource name %s for %s.", name, getClass()));

        try {
            return new URLResource(url).to(StreamReading.class).read();
        } catch (IOException e) {
            throw new PackageError(String.format("Can't read resource %s for %s.", name, getClass()), e);
        }
    }

}
