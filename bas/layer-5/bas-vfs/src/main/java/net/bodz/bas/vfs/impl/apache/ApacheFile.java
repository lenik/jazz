package net.bodz.bas.vfs.impl.apache;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSelectInfo;
import org.apache.commons.vfs.FileSelector;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.Selectors;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;
import net.bodz.bas.fn.IFilter;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileAttributes;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.IFsBlob;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.PathUnsupportedException;
import net.bodz.bas.vfs.VFSException;

public class ApacheFile
        extends AbstractFile {

    private FileObject fileObject;

    ApacheFile(ApacheVfsDevice device, FileObject fileObject) {
        super(device, fileObject.getName().getBaseName());
        this.fileObject = fileObject;
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

    @NotNull
    @Override
    public Path toPath()
            throws PathUnsupportedException {
        URI uri = null;
        try {
            uri = fileObject.getURL().toURI();
            return Paths.get(uri);
        } catch (Exception e) {
            throw new PathUnsupportedException(e);
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
    public int delete(DeleteOption... options)
            throws IOException {
        int count = 0;

        if (DeleteOptions.isDeleteTree(options))
            count = fileObject.delete(Selectors.SELECT_SELF_AND_CHILDREN);
        else
            count = fileObject.delete(Selectors.SELECT_SELF);

        if (DeleteOptions.isRemoveEmptyParents(options)) {
            // TODO
        }

        return count;
    }

    @Override
    public boolean deleteOnExit(DeleteOption... options) {
        return false;
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength()
            throws IOException {
        if (!fileObject.exists())
            return 0L;

        FileContent fileContent = fileObject.getContent();
        long size = fileContent.getSize();
        return size;
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        try {
            fileObject.createFile();

            // Apache VFS: createFile() does nothing if file is already existed.
            if (touch) {
                FileTime lastModifiedTime = FileTime.fromMillis(System.currentTimeMillis());
                setTimes(lastModifiedTime, null, null);
            }

            return true;
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public IRandomResource getResource(Charset charset) {
        FileObjectResource resource = new FileObjectResource(fileObject);
        resource.setCharset(charset);
        return resource;
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public boolean mkdir() {
        try {
            if (fileObject.getParent().exists()) {
                fileObject.createFolder();
                return true;
            }
        } catch (FileSystemException e) {
        }
        return false;
    }

    @Override
    public boolean mkdirs() {
        try {
            fileObject.createFolder();
            return true;
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

        private final IFilter<IFile> vfsFilter;

        public VfsFileSelector(IFilter<IFile> vfsFilter) {
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
    public Iterable<? extends IFile> children(IFilter<IFile> fileFilter)
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
        return Iterables.transform(fileObjects, //
                (FileObject input) -> new ApacheFile(getDevice(), input));
    }

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public boolean isBlob() {
        try {
            return fileObject.getType().hasContent();
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
    public boolean isHidden() {
        try {
            return fileObject.isHidden();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        try {
            FileContent fileContent = fileObject.getContent();
            fileContent.setLastModifiedTime(lastModifiedTime.toMillis());
        } catch (FileSystemException e) {
        }
    }

    /** ⇱ Implementation Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    @Override
    public FileTime lastModifiedTime() {
        try {
            long time = fileObject.getContent().getLastModifiedTime();
            return FileTime.fromMillis(time);
        } catch (FileSystemException e) {
            return null;
        }
    }

    @Override
    public boolean isRegularFile() {
        try {
            return fileObject.getType().hasContent();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isDirectory() {
        try {
            return fileObject.getType().hasChildren();
        } catch (FileSystemException e) {
            return false;
        }
    }

}
