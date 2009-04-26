package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.io.Files;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;

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
                    boolean confirm = UI.confirm("File %s is already existed, overwrite?", dst);
                    if (!confirm) {
                        L.info("FileLink skipped: ", dst);
                        return;
                    }
                } else {
                    // mkdir -p dst.parent
                    File parentFile = dst.getParentFile();
                    parentFile = Files.canoniOf(parentFile);
                    parentFile.mkdirs();
                }
                if (symbolic) {
                    L.finfo("Create symlink %s => %s\n", src, dst);
                    try {
                        dstpath.createSymbolicLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        if (SystemInfo.isWin32()) {
                            L.warn("Symbolic link isn't supported, try to create win32 shortcut.");
                            File _src = Files.canoniOf(src);
                            LnkFile lnk = new LnkFile(dst.getParent(), dst.getName());
                            lnk.setPath(_src.getPath());
                            lnk.setWorkingDirectory(_src.getParent());
                            lnk.save();
                        } else
                            throw e;
                    }
                } else {
                    L.finfo("Create link %s => %s\n", src, dst);
                    try {
                        dstpath.createLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        L.warn("Hard link isn't supported, try full copy.");
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
                    L.info("Remove link ", dst);
                    dst.delete();
                } else if ((dst = new File(dst.getPath() + ".lnk")).exists()) {
                    L.info("Remove shortcut file ", dst);
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
