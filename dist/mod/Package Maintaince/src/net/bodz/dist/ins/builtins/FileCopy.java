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

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.FileFinder;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.log.LogTerm;
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

    private String           base;
    private Collection<File> files;
    private String           removal;
    private boolean          dirStruct = true;

    /**
     * @param keepDirWithin
     *            using {@link File#getPath()} to get the path with correct path
     *            separator.
     */
    public FileCopy(String base, String keepDirWithin, Collection<File> files) {
        super(false, true);
        if (base == null)
            throw new NullPointerException("base"); //$NON-NLS-1$
        if (files == null)
            throw new NullPointerException("files");
        this.base = base;
        this.removal = keepDirWithin;
        this.files = files;
    }

    public FileCopy(String base, String keepDirWithin, File... files) {
        this(base, keepDirWithin, Arrays.asList(files));
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

    @Override
    public String toString() {
        CharOut buf = new BCharOut(files.size() * 50);
        buf.println("FileCopy(" + getName() + ")");
        buf.println("  base=", base);
        buf.println("  removal=", removal);
        int i = 0;
        for (File f : files)
            buf.println("  ", ++i, ". ", f);
        return buf.toString();
    }

    IAttachment getAttachment(ISession session) {
        String id = getId();
        String aname = base + "/" + id + ".jar"; //$NON-NLS-1$ //$NON-NLS-2$
        IAttachment a;
        try {
            a = session.getAttachment(aname);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    @Override
    public void pack(ISession session) throws InstallException {
        LogTerm logger = session.getLogger();
        List<String> regList = new ArrayList<String>(files.size());
        IAttachment a = getAttachment(session);
        try {
            JarOutputStream jout = a.getJarOut();
            for (File f : files) {
                String path = f.getPath();
                if (f.isDirectory())
                    if (!dirStruct)
                        continue;
                    else
                        path += File.separator;
                String dest;
                if (removal == null)
                    dest = f.getName();
                else {
                    // fix: see "path += File.separator" above.
                    if (!path.startsWith(removal))
                        throw new InstallException(
                                PackNLS.getString("FileCopy.notWithinPrefix") + removal //$NON-NLS-1$
                                        + ": " + path); //$NON-NLS-1$
                    dest = path.substring(removal.length());
                    // zip entry is dir-entry, only if path ends with '/'.
                    // but escapes..?
                    dest = dest.replace(File.separatorChar, '/');
                }
                if (f.isDirectory()) {
                    logger.info("Put entry ", dest);
                    JarEntry entry = new JarEntry(dest);
                    jout.putNextEntry(entry);
                    jout.closeEntry();
                } else if (f.isFile()) {
                    long fileSize = f.length();
                    logger.info("Put entry ", dest, " (size=", fileSize, ")");
                    JarEntry entry = new JarEntry(dest);
                    jout.putNextEntry(entry);
                    entry.setSize(fileSize);
                    for (byte[] block : Files.readByBlock(Files.blockSize, f)) {
                        jout.write(block);
                    }
                    jout.closeEntry();
                } else {
                    logger.warn("Ignored file of unknown type: ", f);
                    continue;
                }
                regList.add(dest);
            }
        } catch (IOException e) {
            throw new InstallException(e);
        }
        String[] registry = regList.toArray(new String[0]);
        setRegistryData(registry);
    }

    @Override
    public boolean install(ISession session) throws InstallException {
        LogTerm logger = session.getLogger();
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
                    logger.detail(
                            PackNLS.getString("FileCopy.mkdir"), destdir, "/"); //$NON-NLS-1$ //$NON-NLS-2$
                    destdir.mkdirs();
                }
                logger.detail(PackNLS.getString("FileCopy.extract"), dest); //$NON-NLS-1$
                FileOutputStream out = new FileOutputStream(dest);
                try {
                    long remaining = entry.getSize();
                    int blockSize = Files.blockSize;
                    byte[] block = new byte[blockSize];
                    while (remaining > 0) {
                        int cb = (int) Math.min(blockSize, remaining);
                        cb = zin.read(block, 0, cb);
                        if (cb == -1)
                            throw new IOException(PackNLS
                                    .getString("FileCopy.unexpectedEOF") //$NON-NLS-1$
                                    + name);
                        out.write(block, 0, cb);
                        remaining -= cb;
                    }
                } finally {
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new InstallException(PackNLS
                    .getString("FileCopy.errorExtract") + a, e); //$NON-NLS-1$
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
            throw new InstallException(
                    PackNLS.getString("FileCopy.errorRead") + a, e); //$NON-NLS-1$
        } finally {
            try {
                zin.close();
            } catch (IOException e) {
            }
        }
    }

}
