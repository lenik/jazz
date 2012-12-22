package net.bodz.bas.vfs.facade;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.CopyOptions;
import net.bodz.bas.c.java.nio.CreateOptions;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;

public class DefaultVfsFacade
        extends AbstractVfsFacade {

    boolean moveAcrossDevice = true;

    public DefaultVfsFacade() {
        super(VFS.getFileSystem());
    }

    @Override
    public byte[] read(IFile src, int readSize, OpenOption... options)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        StreamReading reading = src.tooling()._for(StreamReading.class);
        if (readSize == -1)
            return reading.read();
        else
            return reading.read(readSize);
    }

    @Override
    public char[] readChars(IFile src, int readSize, OpenOption... options)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        StreamReading reading = src.tooling()._for(StreamReading.class);
        if (readSize == -1)
            return reading.readChars();
        else
            return reading.readChars(readSize);
    }

    @Override
    public String readString(IFile src, int readSize, OpenOption... options)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        StreamReading reading = src.tooling()._for(StreamReading.class);
        if (readSize == -1)
            return reading.readString();
        else {
            char[] chars = reading.readChars(readSize);
            return new String(chars);
        }
    }

    @Override
    public void write(IFile dst, byte[] buf, int off, int len, OpenOption... options)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        if (buf == null)
            throw new NullPointerException("buf");
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.setOpenOptions(options);
        writing.write(buf, off, len);
    }

    @Override
    public void writeChars(IFile dst, char[] buf, int off, int len, OpenOption... options)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        if (buf == null)
            throw new NullPointerException("buf");
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.setOpenOptions(options);
        writing.writeChars(buf, off, len);
    }

    @Override
    public void writeString(IFile dst, String string, OpenOption... options)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        if (string == null)
            throw new NullPointerException("string");
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.setOpenOptions(options);
        writing.writeString(string);
    }

    @Override
    public boolean copy(IFile src, IFile dst, CopyOption... options)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        if (dst == null)
            throw new NullPointerException("dst");

        if (src == dst)
            return true;
        if (src.equals(dst))
            return true;

        if (dst.isExisted()) {
            if (CopyOptions.isNoReplaceExisting(options))
                return false;
        } else {
            IFile parent = dst.getParentFile();
            if (parent != null)
                if (!parent.isExisted() && CreateOptions.isCreateParents(options))
                    if (!parent.mkdirs())
                        return false;
        }

        IStreamInputSource srcSource = src.getInputSource();
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.write(srcSource);
        return true;
    }

    @Override
    public boolean move(IFile src, IFile dst, CopyOption... options)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        if (dst == null)
            throw new NullPointerException("dst");

        if (src == dst)
            return true;
        if (src.equals(dst))
            return true;

        if (dst.isExisted()) {
            if (CopyOptions.isNoReplaceExisting(options))
                return false;
        } else {
            IFile parent = dst.getParentFile();
            if (parent != null)
                if (!parent.isExisted() && CreateOptions.isCreateParents(options))
                    if (!parent.mkdirs())
                        return false;
        }

        IVfsDevice srcDev = src.getDevice();
        IVfsDevice dstDev = dst.getDevice();
        if (srcDev.equals(dstDev)) {
            String dstLocalPath = dst.getPath().getLocalPath();
            return src.renameTo(dstLocalPath);
        }

        // src.isDeletable()
        if (moveAcrossDevice)
            if (copy(src, dst)) {
                if (src.delete())
                    return true;
                else
                    dst.delete();
            }

        return false;
    }

    @Override
    public boolean createLink(IPath targetPath, IFile linkFile, boolean symbolic)
            throws IOException {
        String targetSpec = targetPath.toString();
        return linkFile.createLink(targetSpec, symbolic);
    }

    static final DefaultVfsFacade instance = new DefaultVfsFacade();

    public static DefaultVfsFacade getInstance() {
        return instance;
    }

}
