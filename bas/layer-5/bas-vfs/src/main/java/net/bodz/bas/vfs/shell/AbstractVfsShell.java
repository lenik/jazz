package net.bodz.bas.vfs.shell;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractVfsShell
        implements IVfsShell, IVfsShell_File {

    private static final Charset utf8Charset = Charset.forName("utf-8");

    boolean force;
    Charset charset = utf8Charset;

    @Override
    public boolean isForce() {
        return force;
    }

    @Override
    public void setForce(boolean force) {
        this.force = force;
    }

    @Override
    public String getCharsetName() {
        return charset.name();
    }

    @Override
    public void setCharsetName(String charsetName) {
        charset = Charset.forName(charsetName);
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    public IPath parse(String path) {
        return VFS.parse(path);
    }

    public IFile resolve(String path) {
        return VFS.resolve(path);
    }

    @Override
    public byte[] read(String srcFile, int readSize)
            throws IOException {
        IFile src = resolve(srcFile);
        return read(src, readSize);
    }

    @Override
    public char[] readChars(String srcFile, int readSize)
            throws IOException {
        IFile src = resolve(srcFile);
        return readChars(src, readSize);
    }

    @Override
    public String readString(String srcFile, int readSize)
            throws IOException {
        IFile src = resolve(srcFile);
        return readString(src, readSize);
    }

    @Override
    public void write(String dstFile, byte[] buf, int off, int len)
            throws IOException {
        IFile dst = resolve(dstFile);
        write(dst, buf, off, len);
    }

    @Override
    public void writeChars(String dstFile, char[] buf, int off, int len)
            throws IOException {
        IFile dst = resolve(dstFile);
        writeChars(dst, buf, off, len);
    }

    @Override
    public void writeString(String dstFile, String string)
            throws IOException {
        IFile dst = resolve(dstFile);
        writeString(dst, string);
    }

    @Override
    public boolean copy(String srcFile, String dstFile)
            throws IOException {
        IFile src = resolve(srcFile);
        IFile dst = resolve(dstFile);
        return copy(src, dst);
    }

    @Override
    public boolean move(String srcFile, String dstFile)
            throws IOException {
        IFile src = resolve(srcFile);
        IFile dst = resolve(dstFile);
        return move(src, dst);
    }

    @Override
    public boolean createLink(String target, String linkFile, boolean symbolic)
            throws IOException {
        IPath targetPath = parse(target);
        IFile link = resolve(linkFile);
        return createLink(targetPath, link, symbolic);
    }

}
