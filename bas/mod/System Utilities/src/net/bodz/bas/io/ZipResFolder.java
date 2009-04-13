package net.bodz.bas.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipResFolder implements ResFolder {

    private File            file_zip;

    private ZipFile         zipFile;
    private ZipOutputStream zipOut;

    public ZipResFolder(File zipfile) {
        if (zipfile == null)
            throw new NullPointerException("zipfile");
        this.file_zip = zipfile;
    }

    @Override
    public ZipEntryResLink get(String entryPath) {
        return new ZipEntryResLink(entryPath);
    }

    synchronized void openZipForRead() throws IOException {
        if (zipFile != null)
            return;
        if (zipOut != null)
            throw new IOException("currently opened for writing: " + file_zip);
        zipFile = new ZipFile(file_zip);
    }

    synchronized void openZipForWrite() throws IOException {
        if (zipOut != null)
            return;
        if (zipFile != null)
            throw new IOException("currently opened for reading: " + file_zip);
        FileOutputStream out = new FileOutputStream(file_zip, true);
        zipOut = new ZipOutputStream(out);
    }

    public synchronized void close() throws IOException {
        if (zipFile != null) {
            zipFile.close();
            zipFile = null;
        }
        if (zipOut != null) {
            zipOut.close();
            zipOut = null;
        }
    }

    @Override
    public int hashCode() {
        return 0x1237767b ^ file_zip.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ZipResFolder) {
            ZipResFolder a = (ZipResFolder) obj;
            return file_zip.equals(a.file_zip);
        }
        return false;
    }

    public class ZipEntryResLink extends _ResLink {

        private String  entryName;
        private boolean entryCreated;

        public ZipEntryResLink(String entry) {
            if (entry == null)
                throw new NullPointerException("entryName");
            this.entryName = entry;
        }

        @Override
        public File getFile() {
            return null;
        }

        @Override
        public URL getURL() {
            String s = "jar:" + file_zip.toURI() + "!" + entryName;
            URL entryURL;
            try {
                entryURL = new URL(s);
            } catch (MalformedURLException e) {
                // seems like unexpectable, but may happen.
                e.printStackTrace();
                return null;
            }
            return entryURL;
        }

        @Override
        public Boolean exists() {
            if (zipFile != null)
                return zipFile.getEntry(entryName) != null;
            if (entryCreated)
                return true;
            return null;
        }

        @Override
        public InputStream openInputStream() throws IOException {
            openZipForRead();
            assert zipFile != null;
            ZipEntry zipEntry = zipFile.getEntry(entryName);
            if (zipEntry == null)
                return null;
            InputStream zin = zipFile.getInputStream(zipEntry);
            return zin;
        }

        @Override
        public OutputStream openOutputStream(boolean append) throws IOException {
            if (append)
                throw new UnsupportedOperationException(
                        "Can't append to entries in a zip file");
            openZipForWrite();
            assert zipOut != null;
            ZipEntry zipEntry = new ZipEntry(entryName);
            ZipEntryOutputStream entryOut = new ZipEntryOutputStream(zipOut,
                    zipEntry);
            entryCreated = true;
            return entryOut;
        }

        @Override
        public int hashCode() {
            return 0x893e4c15 ^ entryName.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ZipEntryResLink) {
                ZipEntryResLink link = (ZipEntryResLink) obj;
                return entryName.equals(link.entryName);
            }
            return false;
        }

        @Override
        public String toString() {
            return file_zip + "!" + entryName;
        }

    }

}
