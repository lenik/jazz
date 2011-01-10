package net.bodz.bas.vfs;

import java.io.File;
import java.net.URL;

import net.bodz.bas.vfs.impl.fake.InputBytesFile;
import net.bodz.bas.vfs.impl.fake.InputStringFile;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.bas.vfs.impl.url.URLFile;

public class FileWrapper {

    /**
     * @return non-<code>null</code> value
     * @throws NullPointerException
     *             If <code>var</code> is <code>null</code>.
     * @throws UnsupportedOperationException
     *             If cast isn't supported on specific type.
     */
    public static IFile toFile(Object var) {
        if (var instanceof File)
            return new JavaioFile((File) var);
        if (var instanceof URL)
            return new URLFile((URL) var);
        if (var instanceof String)
            return new InputStringFile((String) var);
        if (var instanceof byte[])
            return new InputBytesFile((byte[]) var);

        throw new UnsupportedOperationException();
    }

}
