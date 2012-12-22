package net.bodz.bas.vfs.facade;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.OpenOption;

import net.bodz.bas.vfs.FileSystemProxy;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractVfsFacade
        extends FileSystemProxy
        implements IVfsFacade {

    private static final Charset utf8Charset = Charset.forName("utf-8");

    Charset charset = utf8Charset;

    public AbstractVfsFacade() {
        super();
    }

    public AbstractVfsFacade(IFileSystem _orig) {
        super(_orig);
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

    @Override
    public IPath parse(String path) {
        return VFS.parse(path);
    }

    public IFile resolve(String path) {
        return VFS.resolve(path);
    }

    @Override
    public byte[] read(String srcFile, int readSize, OpenOption... options)
            throws IOException {
        IFile src = resolve(srcFile);
        return read(src, readSize, options);
    }

    @Override
    public char[] readChars(String srcFile, int readSize, OpenOption... options)
            throws IOException {
        IFile src = resolve(srcFile);
        return readChars(src, readSize, options);
    }

    @Override
    public String readString(String srcFile, int readSize, OpenOption... options)
            throws IOException {
        IFile src = resolve(srcFile);
        return readString(src, readSize, options);
    }

    @Override
    public void write(String dstFile, byte[] buf, int off, int len, OpenOption... options)
            throws IOException {
        IFile dst = resolve(dstFile);
        write(dst, buf, off, len, options);
    }

    @Override
    public void writeChars(String dstFile, char[] buf, int off, int len, OpenOption... options)
            throws IOException {
        IFile dst = resolve(dstFile);
        writeChars(dst, buf, off, len, options);
    }

    @Override
    public void writeString(String dstFile, String string, OpenOption... options)
            throws IOException {
        IFile dst = resolve(dstFile);
        writeString(dst, string, options);
    }

    @Override
    public boolean copy(String srcFile, String dstFile, CopyOption... options)
            throws IOException {
        IFile src = resolve(srcFile);
        IFile dst = resolve(dstFile);
        return copy(src, dst, options);
    }

    @Override
    public boolean move(String srcFile, String dstFile, CopyOption... options)
            throws IOException {
        IFile src = resolve(srcFile);
        IFile dst = resolve(dstFile);
        return move(src, dst, options);
    }

    @Override
    public boolean createLink(String target, String linkFile, boolean symbolic)
            throws IOException {
        IPath targetPath = parse(target);
        IFile link = resolve(linkFile);
        return createLink(targetPath, link, symbolic);
    }

}
