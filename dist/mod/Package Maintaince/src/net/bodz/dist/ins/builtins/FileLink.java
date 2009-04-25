package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;

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
            String src = new File(session.getFile(srcbase), srcpath).getPath();
            String dst = new File(session.getFile(dstbase), dstpath).getPath();
            Path srcpath = Paths.get(src);
            Path dstpath = Paths.get(dst);
            try {
                if (symbolic) {
                    L.finfo("Create symlink %s => %s", src, dst);
                    try {
                        dstpath.createSymbolicLink(srcpath);
                    } catch (UnsupportedOperationException e) {
                        if (SystemInfo.isWin32()) {
                            // using JNA instead...
//                            Desktop.getDesktop().
                        }
                    }
                } else {
                    L.finfo("Create link %s => %s", src, dst);
                    dstpath.createLink(srcpath);
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
            String dst = new File(session.getFile(dstbase), dstpath).getPath();
            Path dstpath = Paths.get(dst);
            try {
                L.finfo("Remove link ", dst);
                dstpath.delete();
            } catch (IOException e) {
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
