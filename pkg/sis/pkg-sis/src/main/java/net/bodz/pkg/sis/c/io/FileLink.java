package net.bodz.pkg.sis.c.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.action.IProgressMonitor;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.pkg.sis.AbstractSisComponent;
import net.bodz.pkg.sis.ISisComponent;

import com.roxes.win32.LnkFile;

public class FileLink
        extends AbstractSisComponent {

    private static final long serialVersionUID = 1L;

    private final String srcBase;
    private final String srcPath;
    private final String dstBase;
    private final String dstPath;

    private final boolean symbolic;

    public FileLink(ISisComponent parent, String srcbase, String srcpath, String dstbase, String dstpath,
            boolean symbolic) {
        super(parent, "ln");

        if (srcbase == null)
            throw new NullPointerException("srcbase");
        if (srcpath == null)
            throw new NullPointerException("srcpath");
        if (dstbase == null)
            throw new NullPointerException("dstbase");
        if (dstpath == null)
            throw new NullPointerException("dstpath");
        this.srcBase = srcbase;
        this.srcPath = srcpath;
        this.dstBase = dstbase;
        this.dstPath = dstpath;
        this.symbolic = symbolic;

        setSelected(true);
    }

    @Override
    public void install(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);
        IUserDialogs userDialogs = options.get(IUserDialogs.class);

        File srcLocation = getProject().getValue(srcBase);
        File dstLocation = getProject().getValue(dstBase);

        File src = new File(srcLocation, srcPath);
        File dst = new File(dstLocation, dstPath);
        Path srcPath = src.toPath();
        Path dstPath = dst.toPath();
        try {
            if (dst.exists()) {
                // IAction.
                boolean confirm = userDialogs.confirm(tr._("FileLink.fileExist_s", dst));
                if (!confirm) {
                    logger.info(tr._("FileLink skipped: ") + dst);
                    return;
                }
                dst.delete();
            } else {
                // mkdir -p dst.parent
                File parentFile = dst.getParentFile();
                parentFile = FilePath.canoniOf(parentFile);
                parentFile.mkdirs();
            }
            if (symbolic) {
                logger.infof(tr._("Create symlink %s => %s\n"), src, dst);
                try {
                    Files.createSymbolicLink(dstPath, srcPath);
                } catch (UnsupportedOperationException e) {
                    if (SystemInfo.isWin32()) {
                        logger.warn(tr._("Symbolic link isn\'t supported, try to create win32 shortcut."), e);
                        File _src = FilePath.canoniOf(src);
                        LnkFile lnk = new LnkFile(dst.getParent(), dst.getName());
                        lnk.setPath(_src.getPath());
                        lnk.setWorkingDirectory(_src.getParent());
                        lnk.save();
                    } else
                        throw e;
                }
            } else {
                logger.infof(tr._("Create link %s => %s\n"), src, dst);
                String errmesg = null;
                try {
                    Files.createLink(dstPath, srcPath);
                } catch (UnsupportedOperationException e) {
                    logger.error(errmesg = tr._("Hard link isn\'t supported, try full copy."), e);
                } catch (FileSystemException e) {
                    logger.error(errmesg = tr._("Can't create hard link."), e);
                }
                if (errmesg != null) {
                    try {
                        File parentFile = dst.getParentFile();
                        parentFile = FilePath.canoniOf(parentFile);
                        parentFile.mkdirs();
                        Files.copy(srcPath, dstPath);
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
            }
        } catch (IOException e) {
            logger.error("IO Exception", e);
        }
    }

    @Override
    public void remove(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);

        // File srcLocation = getProject().getValue(srcbase);
        File dstLocation = getProject().getValue(dstBase);

        File dst = new File(dstLocation, dstPath);
        try {
            if (dst.exists()) {
                logger.info(tr._("Remove link ") + dst);
                dst.delete();
            } else if ((dst = new File(dst.getPath() + ".lnk")).exists()) {
                logger.info(tr._("Remove shortcut file ") + dst);
                dst.delete();
            }
        } catch (Exception e) {
            if (logger.error(e, e.getMessage()))
                ; // XXX while-loop retry
        }
    }

}
