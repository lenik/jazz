package net.bodz.bas.repr.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * The resource is replaced by {@link URLResource}.
 */
@Deprecated
public class ClassResource
        extends AbstractResource {

    private final ClassLoader loader;
    private final Class<?> clazz;
    private final String name;

    public ClassResource(ClassLoader classLoader, String name) {
        if (classLoader == null)
            throw new NullPointerException("classLoader");
        if (name == null)
            throw new NullPointerException("name");
        this.loader = classLoader;
        this.clazz = null;

        // ClassLoader resource should always be absolute.
        while (name.startsWith("/"))
            name = name.substring(1);

        this.name = name;
    }

    public ClassResource(Class<?> clazz, String name) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (name == null)
            throw new NullPointerException("name");
        this.loader = null;
        this.clazz = clazz;
        this.name = name;
    }

    @Override
    public String getPath() {
        return name;
    }

    @Override
    public InputStream openBinary()
            throws IOException {
        InputStream in;

        if (loader != null)
            in = loader.getResourceAsStream(name);
        else
            in = clazz.getResourceAsStream(name);
        return in;
    }

}
