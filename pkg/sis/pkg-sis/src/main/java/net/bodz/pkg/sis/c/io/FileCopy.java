package net.bodz.pkg.sis.c.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.zip.ZipUnarchiver;
import net.bodz.bas.c.java.io.FileDirs;
import net.bodz.bas.c.java.io.FileFinder;
import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.c.java.io.IOConfig;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.bas.ui.model.action.IProgressMonitor;
import net.bodz.pkg.sis.AbstractSisComponent;
import net.bodz.pkg.sis.ISisArchive;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.SisException;

public class FileCopy
        extends AbstractSisComponent {

    private static final long serialVersionUID = 1L;

    static final String TOTAL_FILE_SIZE = "totalFileSize";
    // static final String FILE_COUNT = "fileCount";
    static final String FILE_ENTRY = "file";

    private final String locationName;
    private final String outPath;

    private final Collection<File> localFiles;
    private final String localPrefix;

    private long totalFileSize;
    private List<String> fileList;

    /** pre-construct the full path to the base dir. */
    private final boolean preConstruct = true;

    private final boolean includeEmptyDirs = true;

    public FileCopy(ISisComponent parent, String locationName, String outPath, File startDir, File... files) {
        this(parent, locationName, outPath, startDir, Arrays.asList(files));
    }

    public FileCopy(ISisComponent parent, String locationName, String outPath, File[] files) {
        this(parent, locationName, outPath, null, Arrays.asList(files));
    }

    public FileCopy(ISisComponent parent, String locationName, String outPath, FileFinder finder)
            throws IOException {
        this(parent, locationName, outPath, FileRelation.getCommonParentFile(finder.getStart()), finder.listFiles());
    }

    /**
     * @param outPath
     *            <code>base-variable/sub/path...</code>, must use / as the file separator.
     * @param startDir
     *            used to construct the subpath of local files, by remove localBase from local
     *            paths. using <code>null</code> to ignore the directory names.
     */
    public FileCopy(ISisComponent parent, String locationName, String outPath, File startDir, Collection<File> files) {
        super(parent, "cp");

        if (locationName == null)
            throw new NullPointerException("baseVarName");
        if (files == null)
            throw new NullPointerException("files");

        this.locationName = locationName;
        this.outPath = outPath;

        if (startDir == null)
            localPrefix = null;
        else
            localPrefix = startDir.getPath() + File.separator;

        this.localFiles = files;
    }

    @Override
    public long getSpaceRequired() {
        return totalFileSize;
    }

    @Override
    public int getWorks() {
        return fileList.size();
    }

    @Override
    public void pack(IProgressMonitor monitor, IOptions options) {
        ILogger logger = monitor.getLogger();

        ISisArchive archive = getProject().getArchive();
        IRandomResource attachment;
        try {
            attachment = archive.getDataResource(getId(), true);
        } catch (IOException e) {
            logger.fatal(e, "Failed to get data resource: " + getId());
            return;
        }

        int count = localFiles.size();
        totalFileSize = 0;

        logger.infof(nls.tr("Pack %s files to %s\n"), getId(), attachment);

        ZipOutputStream zout;
        try {
            OutputStream out = attachment.newOutputStream();
            zout = new ZipOutputStream(out);
        } catch (IOException e) {
            logger.fatal(e, "Can't open attachment to write: " + attachment);
            return;
        }

        int index = 0;

        Iterator<File> iterator = localFiles.iterator();
        File file = null;
        boolean retry = false;

        while (retry || iterator.hasNext()) {
            if (!retry) {
                file = iterator.next();

                monitor.addProgress(1);
            }
            retry = false;

            String path = file.getPath();
            if (file.isDirectory())
                if (!includeEmptyDirs)
                    continue;
                else
                    path += File.separator;
            String dest;
            if (localPrefix == null)
                dest = file.getName();
            else {
                // fix: see "path += File.separator" above.
                if (!path.startsWith(localPrefix)) {
                    String mesg = nls.tr("File not within the prefix ") + localPrefix + ": " + path;
                    if (logger.error(new SisException(mesg), mesg))
                        continue; // next file
                    return;
                }
                dest = path.substring(localPrefix.length());
                // zip entry is dir-entry, only if path ends with '/'.
                // but escapes..?
                dest = dest.replace(File.separatorChar, '/');
                if (dest.isEmpty())
                    dest = "/";
            }
            if (preConstruct) {
                if (outPath != null)
                    dest = outPath + "/" + dest;
            }
            try {
                if (file.isDirectory()) {
                    assert dest.endsWith("/");
                    logger.log(nls.tr("Put entry "), dest);
                    JarEntry entry = new JarEntry(dest);
                    zout.putNextEntry(entry);
                    zout.closeEntry();
                    totalFileSize += FileDirs.DIR_FILE_SIZE;
                } else if (file.isFile()) {
                    long fileSize = file.length();
                    logger.logf(nls.tr("Put entry %s (size=%d) "), dest, fileSize);
                    JarEntry entry = new JarEntry(dest);
                    // entry.setSize(fileSize);
                    zout.putNextEntry(entry);
                    long remaining = fileSize;
                    int blockSize = IOConfig.readBlockSize;
                    byte[] block = new byte[blockSize];
                    FileInputStream in = new FileInputStream(file);
                    try {
                        while (remaining > 0) {
                            // progress...?
                            int cb = Math.min(blockSize, (int) remaining);
                            cb = in.read(block, 0, cb);
                            if (cb == -1)
                                break;
                            zout.write(block, 0, cb);
                            remaining -= cb;
                        }
                    } finally {
                        in.close();
                        zout.closeEntry();
                    }
                    if (remaining != 0)
                        logger.warnf("Incorrect file size %d, %d more bytes doesn't exist\n", fileSize, remaining);
                    totalFileSize += fileSize;
                } else {
                    logger.warn(nls.tr("Ignored file of unknown type: ") + file);
                    continue;
                }
            } catch (IOException e) {
                if (logger.error(e, e.getMessage()))
                    continue;
            }
        } // for file
    }

    boolean autoMkdirs = true;

    @Override
    public void install(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);
        IUserDialogs userDialogs = options.get(IUserDialogs.class);

        ISisArchive archive = getProject().getArchive();
        IRandomResource attachment;
        try {
            attachment = archive.getDataResource(getId(), false);
        } catch (IOException e) {
            logger.fatal(e, "Failed to read the data resource: " + getId());
            return;
        }

        File baseDir = getProject().getValue(locationName);
        if (!preConstruct)
            if (outPath != null)
                baseDir = new File(baseDir, outPath);
        logger.infof(nls.tr("Install %s files to %s\n"), getId(), baseDir);

        ZipUnarchiver unzip;
        try {
            IByteIOS ios = attachment.newByteIOS();
            unzip = new ZipUnarchiver(ios);
        } catch (IOException e) {
            logger.fatal(e, e.getMessage());
            return;
        }

        int index = 0;
        try {
            for (String name : fileList) {
                monitor.addProgress(1);

                IArchiveEntry entry;
                try {
                    entry = unzip.getEntry(name);
                } catch (IOException e) {
                    if (logger.error(e, "Failed to get zip entry: " + name))
                        ; // XXX while-loop retry.
                    return;
                }

                if (entry == null) {
                    SisException ex = new SisException(nls.tr("Entry isn\'t existed: ") + name);
                    if (logger.error(ex, ex.getMessage()))
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
                        logger.log(nls.tr("Create  directory "), destParentDir, "/");
                        destParentDir.mkdirs();
                    }
                }
                logger.log(nls.tr("Extract "), destFile);
                FileOutputStream destOut = null;
                try {
                    InputStream entryIn = entry.getInputSource().newInputStream();
                    destOut = new FileOutputStream(destFile);
                    long remaining = entry.getSize();
                    int blockSize = IOConfig.readBlockSize;
                    byte[] block = new byte[blockSize];
                    while (remaining > 0) {
                        int cb = Math.min(blockSize, (int) remaining);
                        cb = entryIn.read(block, 0, cb);
                        if (cb == -1)
                            throw new IOException(nls.tr("Unexpected end of file: ") + name);
                        destOut.write(block, 0, cb);
                        remaining -= cb;
                    }
                } catch (IOException e) {
                    if (!logger.error(e, e.getMessage()))
                        break;
                } finally {
                    if (destOut != null)
                        try {
                            destOut.close();
                        } catch (IOException e) {
                            logger.warn(nls.tr("Can\'t close dest file "), destFile);
                        }
                }
            }
        } finally {
            try {
                unzip.close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void remove(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);

        ISisArchive archive = getProject().getArchive();
        IStreamResource attachment;
        try {
            attachment = archive.getDataResource(getId(), false);
        } catch (IOException e) {
            logger.fatal(e, "Failed to read from data resource: " + getId());
            return;
        }

        File baseDir = getProject().getValue(locationName);
        if (!preConstruct) {
            if (outPath != null)
                baseDir = new File(baseDir, outPath);
        }
        logger.infof(nls.tr("Remove %s files from %s\n"), getId(), baseDir);

        ZipInputStream zin = null;
        try {
            InputStream in = attachment.newInputStream();
            zin = new ZipInputStream(in);
        } catch (IOException e) {
            SisException e2 = new SisException(nls.tr("Failed to read from ") + attachment, e);
            logger.fatal(e2, e2.getMessage());
            return;
        }

        List<String> names = new ArrayList<String>();
        ZipEntry entry;
        try {
            while ((entry = zin.getNextEntry()) != null)
                names.add(entry.getName());
            zin.close();
        } catch (IOException e) {
            SisException e2 = new SisException(nls.tr("Failed to list the archive"), e);
            logger.fatal(e2, e2.getMessage());
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
            if (!dest.exists()) {
                logger.log(nls.tr("File doesn\'t exist: "), dest);
                continue;
            }
            if (isdir) {
                logger.log(nls.tr("Delete directory "), dest);
                dest.delete();
                continue;
            }
            logger.log(nls.tr("Delete file "), dest);
            if (dest.delete())
                FileDirs.removeEmptyParents(dest.getParentFile(), baseDir);
            else {
                logger.logf(nls.tr("Can\'t delete file %s, try at exit\n"), dest);
                dest.deleteOnExit();
                getProject().setRebootRequired(true);
            }
        }
    }

    /** ⇱ Implementation Of {@link IRstForm}. */
    /* _____________________________ */static section.iface __RST__;

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        out.attribute(TOTAL_FILE_SIZE, totalFileSize);
        for (String file : fileList)
            out.attribute(FILE_ENTRY, file);
    }

    @Override
    public boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException {
        switch (name) {
        case TOTAL_FILE_SIZE:
            totalFileSize = codec.parseLong(TOTAL_FILE_SIZE, data);
            return true;
        case FILE_ENTRY:
            fileList.add(data);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(localFiles.size() * 50);
        buf.append("FileCopy(" + getName() + ")\n");
        buf.append("  base=" + locationName //
                + (outPath == null ? "" : ("::" + outPath)) + "\n");
        buf.append("  removal=" + localPrefix + "\n");
        buf.append("  count=" + localFiles.size() + "\n");
        return buf.toString();
    }

}
