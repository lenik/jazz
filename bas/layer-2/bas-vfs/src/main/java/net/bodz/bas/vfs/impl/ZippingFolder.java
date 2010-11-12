package net.bodz.bas.vfs.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.io.ZipEntryOutputStream;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.AbstractFsEntry;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFolder;
import net.bodz.bas.vfs.IFsEntry;

public class ZippingFolder
        extends AbstractFsEntry
        implements IFolder {

    private final IFile file;
    private final ZipOutputStream zipOut;

    /**
     * @throws NullPointerException
     *             If <code>outputFile</code> is <code>null</code>.
     */
    public ZippingFolder(IFile outputFile)
            throws IOException {
        super(outputFile.getName());
        this.file = outputFile;
        this.zipOut = new ZipOutputStream(file.toTarget().newOutputStream());
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        ZippingFolder o;
        try {
            o = new ZippingFolder(file);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return super.clone(o);
    }

    @Override
    public IFolder getParentFolder() {
        return file.getParentFolder();
    }

    @Override
    public Boolean exists() {
        return file.exists();
    }

    @Override
    public Long getCreatedTime() {
        return file.getCreatedTime();
    }

    @Override
    public Long getLastModifiedTime() {
        return file.getLastModifiedTime();
    }

    @Override
    public void setLastModifiedTime(long date)
            throws IOException {
        file.setLastModifiedTime(date);
    }

    @Override
    public boolean isFile() {
        return file.isFile();
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public boolean isReadable() {
        return false;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

    @Override
    public boolean isHidden() {
        return file.isHidden();
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    @Override
    public boolean isIterable() {
        return false;
    }

    @Override
    public List<? extends IFsEntry> listChildren()
            throws IOException {
        return IteratorToList.toList(childIterator(null));
    }

    @Override
    public List<? extends IFsEntry> listChildren(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(childIterator(entryNameFilter));
    }

    @Override
    public ImmediateIteratorX<? extends IFsEntry, IOException> childIterator(final IFilter<String> entryNameFilter)
            throws IOException {
        throw new UnsupportedOperationException("Not accessible");
    }

    private ZippingFile currentFile;
    private ZipEntry currentEntry;

    @Override
    public IFsEntry getChild(String entryName)
            throws IOException {
        if (currentEntry != null)
            zipOut.closeEntry(); // repeat close a same entry is allowed.
        currentEntry = new ZipEntry(entryName);
        zipOut.putNextEntry(currentEntry);
        currentFile = new ZippingFile(currentEntry);
        return currentFile;
    }

    class ZippingFile
            extends AbstractFile {

        private final ZipEntry zipEntry;
        private ZipEntryOutputStream entryOut;

        /**
         * @throws IOException
         * @throws NullPointerException
         *             If <code>zipEntry</code> is <code>null</code>.
         */
        public ZippingFile(ZipEntry zipEntry)
                throws IOException {
            super(zipEntry.getName());
            this.zipEntry = zipEntry;
        }

        @Override
        protected Object clone()
                throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        @Override
        public IFolder getParentFolder() {
            return ZippingFolder.this;
        }

        @Override
        public Boolean exists() {
            return true;
        }

        @Override
        public URI getURI() {
            try {
                return getURL().toURI();
            } catch (URISyntaxException e) {
                return null;
            }
        }

        @Override
        public URL getURL() {
            return null;
        }

        @Override
        public boolean isDeletable() {
            return false;
        }

        @Override
        public boolean isFile() {
            return true;
        }

        @Override
        public boolean isFolder() {
            return false;
        }

        @Override
        public boolean isReadable() {
            return false;
        }

        @Override
        public boolean isWritable() {
            return true;
        }

        @Override
        public boolean isExecutable() {
            return false;
        }

        @Override
        public Long getCreatedTime() {
            return zipEntry.getTime();
        }

        @Override
        public Long getLastModifiedTime() {
            return zipEntry.getTime();
        }

        @Override
        public void setLastModifiedTime(long date)
                throws IOException {
            zipEntry.setTime(date);
        }

        @Override
        public Long getLength() {
            return zipEntry.getSize();
        }

        @Override
        public boolean delete()
                throws IOException {
            throw new UnsupportedOperationException();
        }

        class WritePreparation
                extends StreamWritePreparation {

            public WritePreparation() {
                super(ZippingFile.this);
            }

            @Override
            public OutputStream newOutputStream()
                    throws IOException {
                if (entryOut == null) {
                    entryOut = new ZipEntryOutputStream(zipOut, zipEntry);
                }
                return entryOut;
            }

        }

        @Override
        public IStreamReadPreparation forRead() {
            throw new UnsupportedOperationException();
        }

        @Override
        public IStreamWritePreparation forWrite() {
            return new WritePreparation();
        }

    }

}