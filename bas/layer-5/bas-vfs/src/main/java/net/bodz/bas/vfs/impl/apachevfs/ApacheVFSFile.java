package net.bodz.bas.vfs.impl.apachevfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.JavaioStreamInputSource;
import net.bodz.bas.io.resource.JavaioStreamOutputTarget;
import net.bodz.bas.mode.fn.IFilter;
import net.bodz.bas.util.iter.Mitorx;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;

public class ApacheVFSFile
        extends AbstractFile {

    private final FileSystemManager manager;
    private final FileObject fileObject;

    public ApacheVFSFile(ApacheVFSVolume volume, ApacheVFSPath path)
            throws FileSystemException {
        super(volume, path);
        this.manager = volume.getFileSystemManager();
        FileName fileName = path.getFileName();
        this.fileObject = manager.resolveFile(fileName.getURI());
    }

    ApacheVFSFile(ApacheVFSVolume volume, ApacheVFSPath path, FileObject fileObject) {
        super(volume, path);
        this.manager = volume.getFileSystemManager();
        this.fileObject = fileObject;
    }

    @Override
    public ApacheVFSVolume getVolume() {
        return (ApacheVFSVolume) super.getVolume();
    }

    @Override
    public ApacheVFSPath getPath() {
        return (ApacheVFSPath) super.getPath();
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    @Override
    public IFile clone() {
        return new ApacheVFSFile(getVolume(), getPath(), fileObject).populate(this);
    }

    @Override
    public Long getLastModifiedTime() {
        try {
            return fileObject.getContent().getLastModifiedTime();
        } catch (FileSystemException e) {
            return null;
        }
    }

    @Override
    public boolean setLastModifiedTime(long date) {
        try {
            fileObject.getContent().setLastModifiedTime(date);
            return true;
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public Boolean exists() {
        try {
            return fileObject.exists();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isBlob() {
        try {
            return fileObject.getType().hasContent();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isTree() {
        try {
            return fileObject.getType().hasChildren();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isReadable() {
        try {
            return fileObject.isReadable();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isWritable() {
        try {
            return fileObject.isWriteable();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean delete() {
        try {
            return fileObject.delete();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean deleteOnExit() {
        return false;
    }

    @Override
    public boolean isHidden() {
        try {
            return fileObject.isHidden();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isExecutable() {
        return false; // unknown...
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        JavaioStreamInputSource inputSource = new JavaioStreamInputSource() {
            // XXX - Non-reentrantable.
            @Override
            public InputStream newInputStream()
                    throws IOException {
                FileContent content = fileObject.getContent();
                InputStream in = content.getInputStream();
                return in;
            }
        };
        inputSource.setCharset(charset);
        return inputSource;
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset) {
        JavaioStreamOutputTarget outputTarget = new JavaioStreamOutputTarget() {
            @Override
            public OutputStream newOutputStream()
                    throws IOException {
                FileContent content = fileObject.getContent();
                OutputStream out = content.getOutputStream(isAppendMode());
                return out;
            }
        };
        outputTarget.setAppendMode(appendMode);
        outputTarget.setCharset(charset);
        return outputTarget;
    }

    @Override
    public boolean createTree() {
        try {
            fileObject.createFolder();
            return true;
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isIterable() {
        try {
            return fileObject.getType().hasChildren();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public Mitorx<? extends IFile, VFSException> childIterator(IFilter<String> nameFilter)
            throws VFSException {
        return super.childIterator(nameFilter);
    }

    @Override
    public List<? extends IFile> listChildren()
            throws VFSException {
        return super.listChildren();
    }

    @Override
    public List<? extends IFile> listChildren(IFilter<String> entryNameFilter)
            throws VFSException {
        return super.listChildren(entryNameFilter);
    }

}
