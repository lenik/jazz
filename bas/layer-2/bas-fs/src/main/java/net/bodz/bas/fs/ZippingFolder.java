package net.bodz.bas.fs;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.io.ZipEntryOutputStream;

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
        this.zipOut = file.forWrite().newZipOutputStream();
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
    public File getFile() {
        return file.getFile();
    }

    @Override
    public Path getPath() {
        return file.getPath();
    }

    @Override
    public URI getURI() {
        return file.getURI();
    }

    @Override
    public URL getURL() {
        return file.getURL();
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
    public List<? extends IFsEntry> listEntries()
            throws IOException {
        return IteratorToList.toList(entryIterator(null));
    }

    @Override
    public List<? extends IFsEntry> listEntries(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(entryIterator(entryNameFilter));
    }

    @Override
    public ImmediateIteratorX<? extends IFsEntry, IOException> entryIterator(final IFilter<String> entryNameFilter)
            throws IOException {
        throw new UnsupportedOperationException("Not accessible");
    }

    private ZippingFile currentFile;
    private ZipEntry currentEntry;

    @Override
    public IFsEntry getEntry(String entryName)
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
        public File getFile() {
            return null;
        }

        @Override
        public Path getPath() {
            return null;
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

        class WriteToolkit
                extends AbstractWriteToolkit {

            public WriteToolkit() {
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
        public IReadToolkit forRead() {
            throw new UnsupportedOperationException();
        }

        @Override
        public IWriteToolkit forWrite() {
            return new WriteToolkit();
        }

    }

}