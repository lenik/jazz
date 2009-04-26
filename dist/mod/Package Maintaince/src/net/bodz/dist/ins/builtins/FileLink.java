package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.io.Files;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;
import net.bodz.dist.ins.builtins.FileLinkTest;
import net.bodz.dist.ins.nls.PackNLS;

import com.roxes.win32.LnkFile;

/**
 * @test {@link FileLinkTest}
 */
public class FileLink extends _Component {

    private final String  srcbase;
    private final String  srcpath;
    private final String  dstbase;
    private final String  dstpath;
    private final boolean symbolic;

    public FileLink(String srcbase, String srcpath, String dstbase, String dstpath, boolean symbolic) {
        super(false, true);
        if (srcbase == null)
            throw new NullPointerException("srcbase"); //$NON-NLS-1$
        if (srcpath == null)
            throw new NullPointerException("srcpath"); //$NON-NLS-1$
        if (dstbase == null)
            throw new NullPointerException("dstbase"); //$NON-NLS-1$
        if (dstpath == null)
            throw new NullPointerException("dstpath"); //$NON-NLS-1$
        this.srcbase = srcbase;
        this.srcpath = srcpath;
        this.dstbase = dstbase;
        this.dstpath = dstpath;
        this.symbolic = symbolic;
    }

    class CInstall extends CJob {

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
                    boolean confirm = UI.confirm(PackNLS.getString("FileLink.fileExist_s"), dst); //$NON-NLS-1$
                    if (!confirm) {
                        L.info(PackNLS.getString("FileLink.skipped"), dst); //$NON-NLS-1$
                        return;
                    }
                } else {
                    // mkdir -p dst.parent
                    File parentFile = dst.getParentFile();
                    parentFile = Files.canoniOf(parentFile);
                    parentFile.mkdirs();
                }
                if (symbolic) {
                    L.finfo(PackNLS.getString("FileLink.createSymlink_ss"), src, dst); //$NON-NLS-1$
                    try {
                        dstpath.createSymbolicLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        if (SystemInfo.isWin32()) {
                            L.warn(PackNLS.getString("FileLink.symlinkIsntSupported")); //$NON-NLS-1$
                            File _src = Files.canoniOf(src);
                            LnkFile lnk = new LnkFile(dst.getParent(), dst.getName());
                            lnk.setPath(_src.getPath());
                            lnk.setWorkingDirectory(_src.getParent());
                            lnk.save();
                        } else
                            throw e;
                    }
                } else {
                    L.finfo(PackNLS.getString("FileLink.createLink_ss"), src, dst); //$NON-NLS-1$
                    try {
                        dstpath.createLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        L.warn(PackNLS.getString("FileLink.hardLinkIsntSupported")); //$NON-NLS-1$
                        try {
                            File parentFile = dst.getParentFile();
                            parentFile = Files.canoniOf(parentFile);
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

    class CUninstall extends CJob {

        public CUninstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File dst = new File(session.getFile(dstbase), dstpath);
            try {
                if (dst.exists()) {
                    L.info(PackNLS.getString("FileLink.remove"), dst); //$NON-NLS-1$
                    dst.delete();
                } else if ((dst = new File(dst.getPath() + ".lnk")).exists()) { //$NON-NLS-1$
                    L.info(PackNLS.getString("FileLink.removeShortcut"), dst); //$NON-NLS-1$
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
