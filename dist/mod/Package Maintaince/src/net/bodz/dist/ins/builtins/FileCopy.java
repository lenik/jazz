package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.FileFinder;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.PruneFileFilter;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.dist.ins.Attachment;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins.SessionJob;
import net.bodz.dist.ins._Component;
import net.bodz.dist.ins.util.Utils;
import net.bodz.dist.nls.PackNLS;

/**
 * @test FileCopyTest
 */
public class FileCopy extends _Component {

    private final String           baseName;
    private final String           basePath;

    // private File localBase;
    private final String           removal;
    private final Collection<File> files;

    /** pre-construct the full path to the base dir. */
    private final boolean          preConstruct = true;

    /** <code>false</code> to exclude empty parent directories */
    private final boolean          dirStruct    = true;

    /**
     * @param basePath
     *            <code>base-variable/sub/path...</code>, must use / as the file
     *            separator.
     * @param localBase
     *            used to construct the subpath of local files, by remove
     *            localBase from local paths. using <code>null</code> to ignore
     *            the directory names.
     */
    public FileCopy(String baseName, String basePath, File localBase, Collection<File> files) {
        super(false, true);
        if (baseName == null)
            throw new NullPointerException("baseName");
        if (files == null)
            throw new NullPointerException("files");
        this.baseName = baseName;
        this.basePath = basePath;
        // this.localBase = localBase;
        String removal = null;
        if (localBase != null) {
            removal = localBase.getPath();
            if (localBase.isDirectory())
                removal += File.separator;
        }
        this.removal = removal;
        this.files = files;
    }

    public FileCopy(String baseName, String basePath, File localBase, File... files) {
        this(baseName, basePath, localBase, Arrays.asList(files));
    }

    public FileCopy(String baseName, String basePath, File... files) {
        this(baseName, basePath, null, Arrays.asList(files));
    }

    public FileCopy(String baseName, String basePath, FileFinder finder) throws IOException {
        this(baseName, basePath, Files.findBase(finder.getStart()), finder.listFiles());
    }

    private static final String ROOT = null;

    public FileCopy(String baseName, File localBase, Collection<File> files) {
        this(baseName, ROOT, localBase, files);
    }

    public FileCopy(String baseName, File localBase, File... files) {
        this(baseName, ROOT, localBase, files);
    }

    public FileCopy(String baseName, File... files) {
        this(baseName, ROOT, null, files);
    }

    public FileCopy(String baseName, FileFinder finder) throws IOException {
        this(baseName, ROOT, finder);
    }

    @Override
    public String toString() {
        CharOut buf = new BCharOut(files.size() * 50);
        buf.println("FileCopy(" + getName() + ")");
        buf.println("  base=", baseName, //
                basePath == null ? "" : ("::" + basePath));
        buf.println("  removal=", removal);
        buf.println("  count=", files.size());
        return buf.toString();
    }

    Attachment getAttachment(ISession session, boolean autoCreate) {
        String id = getId();
        String aname = baseName + "/" + id + ".jar"; //$NON-NLS-1$ //$NON-NLS-2$
        Attachment a;
        try {
            a = session.getAttachment(aname, autoCreate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    @Override
    public SessionJob pack(ISession session) {
        return new PackJob(session);
    }

    @Override
    public SessionJob install(ISession session) {
        return new InstallJob(session);
    }

    @Override
    public SessionJob uninstall(ISession session) {
        return new UninstallJob(session);
    }

    private static class NoSVN implements PruneFileFilter {
        @Override
        public boolean accept(File pathname) {
            if (".svn".equals(pathname.getName().toLowerCase()))
                return false;
            return true;
        }
    }

    public static final FileFilter NoSVN = new NoSVN();

    class PackJob extends SessionJob {

        public PackJob(ISession session) {
            super(session, FileCopy.this);
        }

        @Override
        protected void _run() {
            int count = files.size();
            List<String> regList = new ArrayList<String>(count);
            Attachment a = getAttachment(session, true);
            L.finfo("Pack %s files to %s\n", getId(), a);

            JarOutputStream jout;
            try {
                jout = a.getJarOut();
            } catch (IOException e) {
                throwException(e);
                return;
            }

            setProgressSize(count);
            int index = 0;
            for (File f : files) {
                setProgressIndex(index++);
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
                    if (!path.startsWith(removal)) {
                        String mesg = PackNLS.getString("FileCopy.notWithinPrefix") + removal //$NON-NLS-1$
                                + ": " + path;
                        if (recoverException(new InstallException(mesg)))
                            continue; // next file
                        return;
                    }
                    dest = path.substring(removal.length());
                    // zip entry is dir-entry, only if path ends with '/'.
                    // but escapes..?
                    dest = dest.replace(File.separatorChar, '/');
                    if (dest.isEmpty())
                        dest = "/";
                }
                if (preConstruct) {
                    if (basePath != null)
                        dest = basePath + "/" + dest;
                }
                try {
                    if (f.isDirectory()) {
                        assert dest.endsWith("/");
                        L.detail("Put entry ", dest);
                        JarEntry entry = new JarEntry(dest);
                        jout.putNextEntry(entry);
                        jout.closeEntry();
                    } else if (f.isFile()) {
                        long fileSize = f.length();
                        L.detail("Put entry ", dest, " (size=", fileSize, ")");
                        JarEntry entry = new JarEntry(dest);
                        jout.putNextEntry(entry);
                        entry.setSize(fileSize);
                        for (byte[] block : Files.readByBlock(Files.blockSize, f)) {
                            jout.write(block);
                        }
                        jout.closeEntry();
                    } else {
                        L.warn("Ignored file of unknown type: ", f);
                        continue;
                    }
                    regList.add(dest);
                } catch (IOException e) {
                    if (!recoverException(e))
                        return;
                }
            } // for file
            String[] registry = regList.toArray(new String[0]);
            setRegistryData(registry);
        }

    }

    boolean autoMkdirs = true;

    class InstallJob extends SessionJob {

        public InstallJob(ISession session) {
            super(session, FileCopy.this);
        }

        @Override
        protected void _run() {
            File baseDir = session.getFile(baseName);
            if (!preConstruct)
                if (basePath != null)
                    baseDir = new File(baseDir, basePath);
            L.finfo("Install %s files to %s\n", getId(), baseDir);
            String[] regList = (String[]) getRegistryData();
            if (regList == null)
                throw new NullPointerException("regList");
            setProgressSize(regList.length);

            Attachment a = getAttachment(session, false);
            ZipFile zipFile;
            try {
                zipFile = a.getZipFile();
            } catch (IOException e) {
                throwException(e);
                return;
            }
            int index = 0;
            try {
                for (String name : regList) {
                    setProgressIndex(index++);
                    ZipEntry entry = zipFile.getEntry(name);
                    if (entry == null) {
                        InstallException ex = new InstallException("Entry isn't existed: " + name);
                        if (recoverException(ex))
                            continue;
                        break;
                    }
                    boolean isdir = false;
                    if (name.endsWith("/")) {
                        isdir = true;
                        name = name.substring(0, name.length() - 1);
                    }
                    File destFile = new File(baseDir, name);
                    if (isdir) {
                        if (autoMkdirs)
                            destFile.mkdirs();
                        else
                            destFile.mkdir();
                        continue;
                    }
                    if (autoMkdirs) {
                        File destParentDir = destFile.getParentFile();
                        if (!destParentDir.isDirectory()) {
                            L.detail(PackNLS.getString("FileCopy.mkdir"), destParentDir, "/"); //$NON-NLS-1$ //$NON-NLS-2$
                            destParentDir.mkdirs();
                        }
                    }
                    L.detail(PackNLS.getString("FileCopy.extract"), destFile); //$NON-NLS-1$
                    FileOutputStream destOut = null;
                    try {
                        destOut = new FileOutputStream(destFile);
                        long remaining = entry.getSize();
                        int blockSize = Files.blockSize;
                        byte[] block = new byte[blockSize];
                        while (remaining > 0) {
                            int cb = (int) Math.min(blockSize, remaining);
                            InputStream entryIn = zipFile.getInputStream(entry);
                            cb = entryIn.read(block, 0, cb);
                            if (cb == -1)
                                throw new IOException(PackNLS.getString("FileCopy.unexpectedEOF") //$NON-NLS-1$
                                        + name);
                            destOut.write(block, 0, cb);
                            remaining -= cb;
                        }
                    } catch (IOException e) {
                        if (!recoverException(e))
                            break;
                    } finally {
                        if (destOut != null)
                            try {
                                destOut.close();
                            } catch (IOException e) {
                                L.warn("Can't close dest file ", destFile);
                            }
                    }
                }
            } finally {
                try {
                    zipFile.close();
                } catch (IOException e) {
                }
            }
        }
    }

    class UninstallJob extends SessionJob {

        public UninstallJob(ISession session) {
            super(session, FileCopy.this);
        }

        @Override
        protected void _run() {
            File baseDir = session.getFile(baseName);
            if (!preConstruct) {
                if (basePath != null)
                    baseDir = new File(baseDir, basePath);
            }
            L.finfo("Remove %s files from %s\n", getId(), baseDir);
            Attachment a = getAttachment(session, false);
            ZipInputStream zin = null;
            try {
                zin = a.getZipIn();
            } catch (IOException e) {
                throwException(new InstallException(PackNLS.getString("FileCopy.errorRead") + a, e)); //$NON-NLS-1$
                return;
            }
            List<String> names = new ArrayList<String>();
            ZipEntry entry;
            try {
                while ((entry = zin.getNextEntry()) != null)
                    names.add(entry.getName());
                zin.close();
            } catch (IOException e) {
                throwException(new InstallException("Failed to list the archive", e));
                return;
            } finally {
                try {
                    zin.close();
                } catch (IOException e) {
                    // log..?
                }
            }
            Collections.reverse(names);
            for (String name : names) {
                boolean isdir = name.endsWith("/");
                if (isdir)
                    name = name.substring(0, name.length() - 1);
                File dest = new File(baseDir, name);
                if (isdir) {
                    L.detail("Delete directory ", dest);
                    dest.delete();
                    continue;
                }
                L.detail("Delete file ", dest);
                if (dest.delete())
                    Utils.removeEmptyParents(dest.getParentFile(), baseDir);
                else {
                    L.fdetail("Can't delete file %s, try at exit\n", dest);
                    dest.deleteOnExit();
                    session.getFlags().set(ISession.REBOOT);
                }
            }
        }
    }

}
