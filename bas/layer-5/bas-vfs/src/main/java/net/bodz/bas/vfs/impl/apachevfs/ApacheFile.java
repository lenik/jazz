package net.bodz.bas.vfs.impl.apachevfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSelectInfo;
import org.apache.commons.vfs.FileSelector;
import org.apache.commons.vfs.FileSystemException;

import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.JavaioStreamResource;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.util.IFileFilter;
import net.bodz.bas.vfs.util.IFilenameFilter;

public class ApacheFile
        extends AbstractFile {

    private FileObject fileObject;

    ApacheFile(ApacheVfsDevice device, FileObject fileObject) {
        super(device, fileObject.getName().getBaseName());
        this.fileObject = fileObject;
    }

    @Override
    public IFile clone() {
        ApacheVfsDevice device = (ApacheVfsDevice) getDevice();
        return new ApacheFile(device, fileObject).populate(this);
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    @Override
    public ApacheVfsDevice getDevice() {
        return (ApacheVfsDevice) super.getDevice();
    }

    @Override
    public ApachePath getPath() {
        FileName fileName = fileObject.getName();
        return new ApachePath(fileName);
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

    // -o IFsBlob

    @Override
    public IStreamResource getResource(Charset charset) {
        _Resource resource = new _Resource();
        resource.setCharset(charset);
        return resource;
    }

    class _Resource
            extends JavaioStreamResource {

        @Override
        public InputStream newInputStream()
                throws IOException {
            FileContent content = fileObject.getContent();
            checkOpen(content);
            InputStream in = content.getInputStream();
            return in;
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            FileContent content = fileObject.getContent();
            checkOpen(content);
            OutputStream out = content.getOutputStream(isAppendMode());
            return out;
        }

        void checkOpen(FileContent fileContent) {
            if (fileContent.isOpen())
                throw new IllegalStateException("File is already opened: " + getPath());
        }

    }

    // -o IFsTree

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
    public IFile getChild(String childName) {
        try {
            FileObject childFileObject = fileObject.getChild(childName);
            return new ApacheFile(getDevice(), childFileObject);
        } catch (FileSystemException e) {
            throw new FileResolveException(e.getMessage(), e);
        }
    }

    class VfsNameSelector
            implements FileSelector {

        private final IFilenameFilter vfsFilter;
        private final IFile parentDir;

        public VfsNameSelector(IFilenameFilter vfsFilter, IFile parentDir) {
            if (vfsFilter == null)
                throw new NullPointerException("vfsFilter");
            this.vfsFilter = vfsFilter;
            this.parentDir = parentDir;
        }

        @Override
        public boolean includeFile(FileSelectInfo fileInfo)
                throws Exception {
            FileObject fileObject = fileInfo.getFile();
            String baseName = fileObject.getName().getBaseName();
            return vfsFilter.accept(parentDir, baseName);
        }

        @Override
        public boolean traverseDescendents(FileSelectInfo fileInfo)
                throws Exception {
            return false;
        }

    }

    class VfsFileSelector
            implements FileSelector {

        private final IFileFilter vfsFilter;

        public VfsFileSelector(IFileFilter vfsFilter) {
            if (vfsFilter == null)
                throw new NullPointerException("vfsFilter");
            this.vfsFilter = vfsFilter;
        }

        @Override
        public boolean includeFile(FileSelectInfo fileInfo)
                throws Exception {
            FileObject fileObject = fileInfo.getFile();
            ApacheFile file = new ApacheFile(getDevice(), fileObject);
            return vfsFilter.accept(file);
        }

        @Override
        public boolean traverseDescendents(FileSelectInfo fileInfo)
                throws Exception {
            return false;
        }

    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        FileSelector selector = new VfsNameSelector(nameFilter, this);
        try {
            FileObject[] files = fileObject.findFiles(selector);
            return convertFiles(Arrays.asList(files));
        } catch (FileSystemException e) {
            throw new VFSException(e.getMessage(), e);
        }
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException {
        FileSelector selector = new VfsFileSelector(fileFilter);
        try {
            FileObject[] files = fileObject.findFiles(selector);
            return convertFiles(Arrays.asList(files));
        } catch (FileSystemException e) {
            throw new VFSException(e.getMessage(), e);
        }
    }

    Iterable<ApacheFile> convertFiles(Iterable<FileObject> fileObjects) {
        return Iterables.transform(fileObjects, new ITransformer<FileObject, ApacheFile>() {
            @Override
            public ApacheFile transform(FileObject input)
                    throws RuntimeException {
                return new ApacheFile(getDevice(), input);
            }
        });
    }

}
