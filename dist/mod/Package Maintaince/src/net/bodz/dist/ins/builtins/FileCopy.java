package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.bodz.bas.io.Files;
import net.bodz.dist.ins.IAttachment;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins._Component;

/**
 * @TestBy FileCopyTest
 */
public class FileCopy extends _Component {

    private String     base;
    private List<File> files;
    private String     keepDirWithin;

    public FileCopy(String base, String keepDirWithin, Iterable<File> files,
            int cap) {
        super(false, true);
        if (base == null)
            throw new NullPointerException("base");
        this.base = base;
        this.keepDirWithin = keepDirWithin;
        this.files = new ArrayList<File>(cap);
        for (File f : files)
            this.files.add(f);
    }

    public FileCopy(String base, String keepDirWithin, File... files) {
        this(base, keepDirWithin, Arrays.asList(files), files.length);
    }

    IAttachment getAttachment(ISession session) {
        String id = session.getComponentId();
        String aname = base + "/" + id + ".jar";
        IAttachment a = session.getAttachment(aname);
        return a;
    }

    @Override
    public void dump(ISession session) throws InstallException {
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
                                "file not within the prefix " + keepDirWithin
                                        + ": " + path);
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
                    session.logDetail("Create  directory ", destdir, "/");
                    destdir.mkdirs();
                }
                session.logInfo("Extract ", dest);
                FileOutputStream out = new FileOutputStream(dest);
                try {
                    long remaining = entry.getSize();
                    int blockSize = Files.blockSize;
                    byte[] block = new byte[blockSize];
                    while (remaining > 0) {
                        int cb = (int) Math.min(blockSize, remaining);
                        cb = zin.read(block, 0, cb);
                        if (cb == -1)
                            throw new IOException("Unexpected end of file: "
                                    + name);
                        out.write(block, 0, cb);
                        remaining -= cb;
                    }
                } finally {
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new InstallException("Failed to extract from " + a, e);
        } finally {
            try {
                zin.close();
            } catch (IOException e) {
            }
        }
        return true;
    }

    void removeEmptyParents(File start, File stopDir) {
        start = Files.canoniOf(start);
        stopDir = Files.canoniOf(stopDir);
        do {
            start.delete();
            start = start.getParentFile();
        } while (!start.equals(stopDir));
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
                    removeEmptyParents(dest.getParentFile(), baseDir);
                else {
                    dest.deleteOnExit();
                    session.getFlags().set(ISession.REBOOT);
                }
            }
        } catch (IOException e) {
            throw new InstallException("Failed to read from " + a, e);
        } finally {
            try {
                zin.close();
            } catch (IOException e) {
            }
        }
    }

}
