package net.bodz.redist.installer.builtins;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.ISession;

import com.roxes.win32.LnkFile;

public class FileLink
        extends AbstractComponent {

    private final String srcbase;
    private final String srcpath;
    private final String dstbase;
    private final String dstpath;
    private final boolean symbolic;

    public FileLink(String srcbase, String srcpath, String dstbase, String dstpath, boolean symbolic) {
        super(SELECTED);
        if (srcbase == null)
            throw new NullPointerException("srcbase");
        if (srcpath == null)
            throw new NullPointerException("srcpath");
        if (dstbase == null)
            throw new NullPointerException("dstbase");
        if (dstpath == null)
            throw new NullPointerException("dstpath");
        this.srcbase = srcbase;
        this.srcpath = srcpath;
        this.dstbase = dstbase;
        this.dstpath = dstpath;
        this.symbolic = symbolic;
    }

    class CInstall
            extends CJob {

        public CInstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File src = new File(session.getFile(srcbase), srcpath);
            File dst = new File(session.getFile(dstbase), dstpath);
            Path srcpath = src.toPath();
            Path dstpath = dst.toPath();
            try {
                if (dst.exists()) {
                    boolean confirm = userDialogs.confirm(PackNLS.format("FileLink.fileExist_s", dst));
                    if (!confirm) {
                        logger.info(tr._("FileLink skipped: "), dst);
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
                        dstpath.createSymbolicLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        logger.warn(e);
                        if (SystemInfo.isWin32()) {
                            logger.warn(tr._("Symbolic link isn\'t supported, try to create win32 shortcut."));
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
                        dstpath.createLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        logger.warn(e);
                        errmesg = tr._("Hard link isn\'t supported, try full copy.");
                    } catch (FileSystemException e) {
                        logger.warn(e);
                        errmesg = tr._("Hard link isn\'t supported, try full copy.");
                    }
                    if (errmesg != null) {
                        logger.warn(errmesg);
                        try {
                            File parentFile = dst.getParentFile();
                            parentFile = FilePath.canoniOf(parentFile);
                            parentFile.mkdirs();
                            Files.copy(src, dst);
                        } catch (IOException ex) {
                            throw ex;
                        }
                    }
                }
            } catch (IOException e) {
                recoverException(e);
            }
        }
    }

    class CUninstall
            extends CJob {

        public CUninstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File dst = new File(session.getFile(dstbase), dstpath);
            try {
                if (dst.exists()) {
                    logger.info(tr._("Remove link "), dst);
                    dst.delete();
                } else if ((dst = new File(dst.getPath() + ".lnk")).exists()) {
                    logger.info(tr._("Remove shortcut file "), dst);
                    dst.delete();
                }
            } catch (Exception e) {
                recoverException(e);
            }
        }

    }

    @Override
    protected CJob install(ISession session) {
        return new CInstall(session);
    }

    @Override
    protected CJob uninstall(ISession session) {
        return new CUninstall(session);
    }

}
