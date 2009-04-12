package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.bodz.bas.io.FileFinder;
import net.bodz.bas.io.Files;
import net.bodz.dist.ins.IAttachment;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins._Component;
import net.bodz.dist.ins.util.Utils;
import net.bodz.dist.nls.PackNLS;

/**
 * @test FileCopyTest
 */
public class FileCopy extends _Component {

    private String     base;
    private List<File> files;
    private String     keepDirWithin;

    public FileCopy(String base, String keepDirWithin, Collection<File> files) {
        this(base, keepDirWithin, files, 0);
    }

    /**
     * @param keepDirWithin
     *            using {@link File#getPath()} to get the path with correct path
     *            separator.
     */
    public FileCopy(String base, String keepDirWithin, Iterable<File> files,
            int cap) {
        super(false, true);
        if (base == null)
            throw new NullPointerException("base"); //$NON-NLS-1$
        this.base = base;
        this.keepDirWithin = keepDirWithin;
        this.files = new ArrayList<File>(cap);
        for (File f : files)
            this.files.add(f);
    }

    public FileCopy(String base, String keepDirWithin, File... files) {
        this(base, keepDirWithin, Arrays.asList(files), files.length);
    }

    static String _findBase(FileFinder finder) {
        File baseDir = Files.findBase(finder.getStart());
        if (baseDir == null)
            return null;
        return baseDir.getPath() + File.separatorChar;
    }

    public FileCopy(String base, FileFinder finder) throws IOException {
        this(base, _findBase(finder), finder.listFiles());
    }

    IAttachment getAttachment(ISession session) {
        String id = getId();
        String aname = base + "/" + id + ".jar"; //$NON-NLS-1$ //$NON-NLS-2$
        IAttachment a = session.getAttachment(aname);
        return a;
    }

    @Override
    public void pack(ISession session) throws InstallException {
        // TextMap<Object> cr = session.getComponentRegistry();
        IAttachment a = getAttachment(session);
        try {
            JarOutputStream jout = a.getJarOut();
            for (File f : files) {
                String dest;
                if (keepDirWithin == null)
                    dest = f.getName();
                else {
                    String path = f.getPath();
                    if (!path.startsWith(keepDirWithin))
                        throw new InstallException(
                                PackNLS.getString("FileCopy.notWithinPrefix") + keepDirWithin //$NON-NLS-1$
                                        + ": " + path); //$NON-NLS-1$
                    dest = path.substring(keepDirWithin.length());
                }
                JarEntry entry = new JarEntry(dest);
                jout.putNextEntry(entry);
                entry.setSize(f.length());
                for (byte[] block : Files.readByBlock(Files.blockSize, f)) {
                    jout.write(block);
                }
                // jout.closeEntry();
            }
        } catch (IOException e) {
            throw new InstallException(e);
        }
    }

    @Override
    public boolean install(ISession session) throws InstallException {
        File baseDir = session.getBaseDir(base);
        IAttachment a = getAttachment(session);
        ZipInputStream zin = null;
        try {
            zin = a.getZipIn();
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String name = entry.getName();
                File dest = new File(baseDir, name);
                File destdir = dest.getParentFile();
                if (!destdir.isDirectory()) {
                    session.logDetail(PackNLS.getString("FileCopy.createDirectory"), destdir, "/"); //$NON-NLS-1$ //$NON-NLS-2$
                    destdir.mkdirs();
                }
                session.logInfo(PackNLS.getString("FileCopy.extract"), dest); //$NON-NLS-1$
                FileOutputStream out = new FileOutputStream(dest);
                try {
                    long remaining = entry.getSize();
                    int blockSize = Files.blockSize;
                    byte[] block = new byte[blockSize];
                    while (remaining > 0) {
                        int cb = (int) Math.min(blockSize, remaining);
                        cb = zin.read(block, 0, cb);
                        if (cb == -1)
                            throw new IOException(PackNLS.getString("FileCopy.unexpectedEOF") //$NON-NLS-1$
                                    + name);
                        out.write(block, 0, cb);
                        remaining -= cb;
                    }
                } finally {
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new InstallException(PackNLS.getString("FileCopy.errorExtract") + a, e); //$NON-NLS-1$
        } finally {
            try {
                zin.close();
            } catch (IOException e) {
            }
        }
        return true;
    }

    @Override
    public void uninstall(ISession session) throws InstallException {
        File baseDir = session.getBaseDir(base);
        IAttachment a = getAttachment(session);
        ZipInputStream zin = null;
        try {
            zin = a.getZipIn();
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String name = entry.getName();
                File dest = new File(baseDir, name);
                if (dest.delete())
                    Utils.removeEmptyParents(dest.getParentFile(), baseDir);
                else {
                    dest.deleteOnExit();
                    session.getFlags().set(ISession.REBOOT);
                }
            }
        } catch (IOException e) {
            throw new InstallException(PackNLS.getString("FileCopy.errorRead") + a, e); //$NON-NLS-1$
        } finally {
            try {
                zin.close();
            } catch (IOException e) {
            }
        }
    }

}
