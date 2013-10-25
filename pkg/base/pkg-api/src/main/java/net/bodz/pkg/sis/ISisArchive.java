package net.bodz.pkg.sis;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.vfs.IFile;

/**
 * file.sis - runnable package.
 * 
 * <pre>
 * foobar.sis/, foobar.sis.jar
 *     ├── bin
 *     │   └── ProjectInstallerRunner.class
 *     ├── data
 *     │   ├── project.rst
 *     │   └── section-1
 *     │       ├── sub-section
 *     │       │   └── ...
 *     │       ├── file-copy-1.zip
 *     │       ├── file-copy-2.zip
 *     │       └── ...
 *     ├── etc
 *     │   └── classpath: jar file each line
 *     ├── lib
 *     │   └── net
 *     │      └── bodz
 *     │          └── pkg
 *     │             ├── classwords-x.y.z.jar
 *     │             ├── pkg-sis-x.y.z.jar
 *     │             ├── com.foobar.jar...
 *     │             └── ...
 *     └── META-INF
 *         ├── services
 *         │  └── net.bodz.pkg.sis.IProject: com.foobar.FooBarProject
 *         └── MANIFEST.MF: Main-Class: bin.SisProjectInstallerRunner
 * </pre>
 */
public interface ISisArchive
        extends Flushable, Closeable {

    IFile getBaseDir();

    /**
     * Get named resource.
     * 
     * @param name
     *            name of the resource
     * @param autoCreate
     *            create new resource if not existed
     * @return Non-null {@link IRandomResource} which can be opened later. After used the resource,
     *         it can be left opened for next time use, all unclosed resources are auto closed at
     *         the end.
     */
    IRandomResource getResource(String name, boolean autoCreate)
            throws IOException;

    /**
     * Get named data resource.
     * 
     * @param name
     *            name of the data resource
     * @param autoCreate
     *            create new data resource if not existed
     * @return Non-null {@link IRandomResource} which can be opened later. After used the data
     *         resource, it can be left opened for next time use, all unclosed data resources are
     *         auto closed at the end.
     */
    IRandomResource getDataResource(String name, boolean autoCreate)
            throws IOException;

    /**
     * Flush opened resources.
     * 
     * This will close the current working zip entries.
     */
    @Override
    void flush();

    /**
     * Close opened resources.
     * 
     * This will close opened zip streams.
     */
    void close();

}
