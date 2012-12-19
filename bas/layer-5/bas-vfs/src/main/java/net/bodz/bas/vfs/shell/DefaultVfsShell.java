package net.bodz.bas.vfs.shell;

import java.io.IOException;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.path.IPath;

public class DefaultVfsShell
        extends AbstractVfsShell {

    boolean moveAcrossDevice = true;

    @Override
    public byte[] read(IFile src, int readSize)
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
    public char[] readChars(IFile src, int readSize)
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
    public String readString(IFile src, int readSize)
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
    public void write(IFile dst, byte[] buf, int off, int len)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        if (buf == null)
            throw new NullPointerException("buf");
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.write(buf, off, len);
    }

    @Override
    public void writeChars(IFile dst, char[] buf, int off, int len)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        if (buf == null)
            throw new NullPointerException("buf");
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.writeChars(buf, off, len);
    }

    @Override
    public void writeString(IFile dst, String string)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        if (string == null)
            throw new NullPointerException("string");
        StreamWriting writing = dst.tooling()._for(StreamWriting.class);
        writing.writeString(string);
    }

    @Override
    public boolean copy(IFile src, IFile dst)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        if (dst == null)
            throw new NullPointerException("dst");

        if (src == dst)
            return true;
        if (src.equals(dst))
            return true;

        IStreamInputSource srcSource = src.getInputSource();
        dst.tooling()._for(StreamWriting.class).write(srcSource);
        return true;
    }

    @Override
    public boolean move(IFile src, IFile dst)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        if (dst == null)
            throw new NullPointerException("dst");

        if (src == dst)
            return true;
        if (src.equals(dst))
            return true;

        if (dst.isExisted())
            if (isForce())
                return false;

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

        return false;
    }

}
