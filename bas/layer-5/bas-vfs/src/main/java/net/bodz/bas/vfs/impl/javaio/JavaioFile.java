package net.bodz.bas.vfs.impl.javaio;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.model.IFilter;
import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Mitorx;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFsTree;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.IPath;

public class JavaioFile
        extends AbstractFile.TransientPath
        implements IFsTree {

    private static final JavaioFileSystem volume = JavaioFileSystem.getInstance();

    private final java.io.File jdkFile;

    /**
     * @param jdkPath
     *            The pathname of a {@link java.io.File}.
     * @throws NullPointerException
     *             if <code>jdkPath</code> is <code>null</code>
     */
    public JavaioFile(String jdkPath) {
        super(_slashNormalize(jdkPath));
        this.jdkFile = new File(this.pathString);
    }

    /**
     * @param jdkFile
     *            non-<code>null</code> {@link java.io.File} object.
     */
    public JavaioFile(File jdkFile) {
        super(jdkFile.getPath());
        this.jdkFile = jdkFile;
    }

    private static String _slashNormalize(String path) {
        return path;
    }

    @Override
    public JavaioFileSystem getVolume() {
        return volume;
    }

    @Override
    public IPath getPath() {
        return new JavaioPath(jdkFile.getPath());
    }

    @Override
    public JavaioFile clone() {
        JavaioFile o = new JavaioFile(jdkFile);
        o.populate(this);
        return o;
    }

    @Override
    public JavaioFile getParentFile() {
        File parentFile = jdkFile.getParentFile();
        if (parentFile == null)
            return null;
        return new JavaioFile(parentFile);
    }

    @Override
    public Boolean exists() {
        return jdkFile.exists();
    }

    @Override
    public Long getLastModifiedTime() {
        return jdkFile.lastModified();
    }

    @Override
    public boolean setLastModifiedTime(long date) {
        return jdkFile.setLastModified(date);
    }

    @Override
    public boolean isBlob() {
        return jdkFile.isFile();
    }

    @Override
    public boolean isTree() {
        return jdkFile.isDirectory();
    }

    @Override
    public boolean isReadable() {
        return jdkFile.canRead();
    }

    @Override
    public boolean isWritable() {
        return jdkFile.canWrite();
    }

    @Override
    public boolean isHidden() {
        return jdkFile.isHidden();
    }

    @Override
    public boolean isExecutable() {
        return jdkFile.canExecute();
    }

    @Override
    public Long getLength() {
        return jdkFile.length();
    }

    @Override
    public boolean isSeekable() {
        return true;
    }

    @Override
    public boolean isIterable() {
        return jdkFile.canExecute();
    }

    @Override
    public boolean delete() {
        return jdkFile.delete();
    }

    @Override
    public boolean deleteOnExit() {
        jdkFile.deleteOnExit();
        return true;
    }

    @Override
    public IStreamResource getResource(Charset charset) {
        LocalFileResource resource = new LocalFileResource(jdkFile);
        resource.setCharset(charset);
        return resource;
    }

    // IFsTree

    @Override
    public JavaioFile getChild(String entryName) {
        File file = new File(this.jdkFile, entryName);
        return new JavaioFile(file);
    }

    /**
     * 
     */
    @Override
    public Mitorx<? extends JavaioFile, VFSException> childIterator(final IFilter<String> entryNameFilter) {
        final String[] list;
        if (entryNameFilter == null)
            list = jdkFile.list();
        else
            list = jdkFile.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return entryNameFilter.accept(name);
                }
            });

        return new AbstractMitorx<JavaioFile, VFSException>() {

            int index = 0;

            @Override
            public JavaioFile _next()
                    throws VFSException {
                if (index >= list.length)
                    return end();
                String childName = list[index++];
                File childFile = new File(jdkFile, childName);
                return new JavaioFile(childFile);
            }

        };
    }

    @Override
    public boolean createTree() {
        return jdkFile.mkdirs();
    }

    @Override
    public int hashCode() {
        int hash = 0x4c691595;
        hash += jdkFile.hashCode();
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JavaioFile))
            return false;
        JavaioFile o = (JavaioFile) obj;
        if (!jdkFile.equals(o.jdkFile))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return jdkFile.toString();
    }

}
