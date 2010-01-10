package net.bodz.bas.fs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.exceptions.ReadOnlyException;

public class ZippedFolder
        extends AbstractFsEntry
        implements IFolder {

    private final IFile file;
    private final ZipFile zipFile;

    /**
     * @throws IOException
     * @throws NullPointerException
     *             If inputFile is <code>null</code>.
     */
    public ZippedFolder(IFile inputFile)
            throws IOException {
        super(inputFile.getName());
        this.file = inputFile;
        this.zipFile = inputFile.forRead().newZipFile();
    }

    @Override
    protected ZippedFolder clone()
            throws CloneNotSupportedException {
        ZippedFolder o;
        try {
            o = new ZippedFolder(file);
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
    public Boolean exists() {
        return file.exists();
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
        return file.isReadable();
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public boolean isHidden() {
        return file.isHidden();
    }

    @Override
    public boolean isDeletable() {
        return file.isDeletable();
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
    public boolean isIterable() {
        return true;
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
        final Enumeration<? extends ZipEntry> entryEnum = zipFile.entries();
        return new AbstractImmediateIteratorX<IFsEntry, IOException>() {
            @Override
            public IFsEntry next()
                    throws IOException {
                while (entryEnum.hasMoreElements()) {
                    ZipEntry zipEntry = entryEnum.nextElement();
                    if (entryNameFilter != null)
                        if (!entryNameFilter.accept(zipEntry.getName()))
                            continue;
                    return new ZippedFile(zipEntry);
                }
                return end();
            }
        };
    }

    @Override
    public IFsEntry getEntry(String entryName)
            throws IOException {
        ZipEntry zipEntry = zipFile.getEntry(entryName);
        if (zipEntry == null)
            return null;
        return new ZippedFile(zipEntry);
    }

    public class ZippedFile
            extends AbstractFile {

        private final ZipEntry entry;

        /**
         * @throws NullPointerException
         *             If <code>zipEntry</code> is <code>null</code>.
         */
        public ZippedFile(ZipEntry zipEntry) {
            super(zipEntry.getName());
            this.entry = zipEntry;
        }

        @Override
        protected ZippedFile clone()
                throws CloneNotSupportedException {
            ZippedFile o = new ZippedFile(entry);
            return super.clone(o);
        }

        @Override
        public IFolder getParentFolder() {
            return ZippedFolder.this;
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
            URL url = ZippedFolder.this.getURL();
            URL entryURL;
            try {
                entryURL = new URL(url, getName());
            } catch (MalformedURLException e) {
                return null;
            }
            return entryURL;
        }

        @Override
        public boolean isFile() {
            return !entry.isDirectory();
        }

        @Override
        public boolean isFolder() {
            return entry.isDirectory();
        }

        @Override
        public Long getCreatedTime() {
            return entry.getTime();
        }

        @Override
        public Long getLastModifiedTime() {
            return entry.getTime();
        }

        @Override
        public void setLastModifiedTime(long date)
                throws IOException {
            entry.setTime(date);
        }

        @Override
        public Long getLength() {
            return entry.getSize();
        }

        @Override
        public boolean isReadable() {
            return true;
        }

        @Override
        public boolean isWritable() {
            return false;
        }

        @Override
        public boolean isExecutable() {
            return false;
        }

        @Override
        public boolean isDeletable() {
            return false;
        }

        @Override
        public boolean delete()
                throws IOException {
            throw new ReadOnlyException();
        }

        class ReadToolkit
                extends AbstractReadToolkit {

            private InputStream entryIn;

            public ReadToolkit() {
                super(ZippedFile.this);
            }

            @Override
            public InputStream newInputStream()
                    throws IOException {
                if (entryIn == null) {
                    entryIn = zipFile.getInputStream(entry);
                }
                return entryIn;
            }
        }

        @Override
        public AbstractReadToolkit forRead() {
            return new ReadToolkit();
        }

        @Override
        public AbstractWriteToolkit forWrite() {
            throw new UnsupportedOperationException();
        }

    }

}
