package net.bodz.bas.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.nls.SysNLS;

public class ZipResFolder implements ResFolder {

    private final File      file;
    private final String    jarurl;

    private final boolean   autoMkdirs;

    private ZipFile         zipFile;
    private ZipOutputStream zipOut;

    public ZipResFolder(File zipfile) {
        this(zipfile, false);
    }

    public ZipResFolder(File zipfile, boolean autoMkdirs) {
        if (zipfile == null)
            throw new NullPointerException("zipfile"); //$NON-NLS-1$
        this.file = zipfile;
        this.jarurl = "jar:" + Files.getURL(file).toExternalForm() + "!/"; //$NON-NLS-1$ //$NON-NLS-2$
        this.autoMkdirs = autoMkdirs;
    }

    public File getFile() {
        return file;
    }

    public boolean isAutoMkdirs() {
        return autoMkdirs;
    }

    void autoMkdirs() {
        if (autoMkdirs) {
            File parentDir = file.getParentFile();
            if (parentDir != null)
                parentDir.mkdirs();
        }
    }

    @Override
    public ResLink get(String entryPath) {
        // return new ZipEntryResLink(entryPath);
        String s = jarurl + entryPath;
        try {
            URL url = new URL(s);
            return new URLResLink(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(SysNLS.getString("ZipResFolder.invalidEntryPath") + entryPath, e); //$NON-NLS-1$
        }
    }

    synchronized void openZipForRead() throws IOException {
        if (zipFile != null)
            return;
        if (zipOut != null)
            throw new IOException(SysNLS.getString("ZipResFolder.currentlyWriting") + file); //$NON-NLS-1$
        zipFile = new ZipFile(file);
    }

    synchronized void openZipForWrite(boolean createNew) throws IOException {
        if (zipOut != null)
            if (!createNew)
                return;
        if (zipFile != null)
            throw new IOException(SysNLS.getString("ZipResFolder.currentlyReading") + file); //$NON-NLS-1$
        autoMkdirs();
        FileOutputStream out = new FileOutputStream(file, false);
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
        return 0x1237767b ^ file.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ZipResFolder) {
            ZipResFolder a = (ZipResFolder) obj;
            return file.equals(a.file);
        }
        return false;
    }

    @Override
    public String toString() {
        String s = file.toString();
        if (autoMkdirs)
            s = "* " + s; //$NON-NLS-1$
        return s;
    }

    public class ZipEntryResLink extends _ResLink {

        private String  entryName;
        private boolean entryCreated;

        public ZipEntryResLink(String entry) {
            if (entry == null)
                throw new NullPointerException("entryName"); //$NON-NLS-1$
            this.entryName = entry;
        }

        @Override
        public File getFile() {
            return null;
        }

        @Override
        public URL getURL() {
            String s = "jar:" + file.toURI() + "!" + entryName; //$NON-NLS-1$ //$NON-NLS-2$
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
            InputStream entryIn = zipFile.getInputStream(zipEntry);
            return entryIn;
        }

        @Override
        public OutputStream openOutputStream(boolean append) throws IOException {
            // XXX - !append doesn't exactly means createNew
            // in fact, jdk doesn't support append to zip.
            openZipForWrite(!append);
            assert zipOut != null;
            ZipEntry zipEntry = new ZipEntry(entryName);
            ZipEntryOutputStream entryOut = new ZipEntryOutputStream(zipOut, zipEntry);
            entryCreated = true;
            return entryOut;
        }

        /**
         * XXX - can't open jar file from URL
         */
        @Override
        public JarFile openJarFile() throws IOException {
            InputStream entryIn = openInputStream();
            File file = Files.convertToFile(null, entryIn);
            return new JarFile(file);
        }

        /**
         * XXX - can't open zip file from URL
         */
        @Override
        public ZipFile openZipFile() throws IOException {
            InputStream entryIn = openInputStream();
            File file = Files.convertToFile(null, entryIn);
            return new ZipFile(file);
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
            return file + "!" + entryName; //$NON-NLS-1$
        }

    }

}
