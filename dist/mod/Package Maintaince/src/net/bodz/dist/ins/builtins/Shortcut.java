package net.bodz.dist.ins.builtins;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;
import net.bodz.dist.ins.nls.PackNLS;

import com.roxes.win32.LnkFile;

public class Shortcut extends _Component {

    private final String srcbase;
    private final String srcpath;
    private final String dstbase;
    private final String dstpath;
    private final String arguments;
    private final String workingDir;
    private final String text;
    private final String icon;
    private final int    iconIndex;

    public Shortcut(String srcbase, String srcpath, String dstbase, String dstpath) {
        this(srcbase, srcpath, dstbase, dstpath, null);
    }

    public Shortcut(String srcbase, String srcpath, String dstbase, String dstpath, String arguments) {
        this(srcbase, srcpath, dstbase, dstpath, arguments, null, null, null, 0);
    }

    public Shortcut(String srcbase, String srcpath, String dstbase, String dstpath,
            String arguments, String workingDir, String text, String icon, int iconIndex) {
        super(false, true);
        if (srcbase == null)
            throw new NullPointerException("srcbase"); //$NON-NLS-1$
        if (dstbase == null)
            throw new NullPointerException("dstbase"); //$NON-NLS-1$
        this.srcbase = srcbase;
        this.srcpath = srcpath;
        this.dstbase = dstbase;
        this.dstpath = dstpath;
        this.arguments = arguments;
        this.workingDir = workingDir;
        this.text = text;
        this.icon = icon;
        this.iconIndex = iconIndex;
    }

    class CInstall extends CJob {

        public CInstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File src = new File(session.getFile(srcbase), srcpath);
            File dst = new File(session.getFile(dstbase), dstpath);
            if (!dst.exists()) {
                // mkdir -p dst.parent
                File parentFile = dst.getParentFile();
                parentFile = Files.canoniOf(parentFile);
                parentFile.mkdirs();
            }
            L.info(PackNLS.getString("Shortcut.create"), dst); //$NON-NLS-1$
            if (SystemInfo.isWin32()) {
                File _src = Files.canoniOf(src);
                LnkFile lnk = new LnkFile(dst.getParent(), dst.getName());
                lnk.setPath(_src.getPath());
                if (arguments != null)
                    lnk.setArguments(arguments);
                lnk.setWorkingDirectory(workingDir != null ? workingDir : _src.getParent());
                if (text != null)
                    lnk.setDescription(text);
                if (icon != null) {
                    lnk.setIconLocation(icon);
                    lnk.setIconIndex(iconIndex);
                }
                lnk.save();
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
            LnkFile lnk = new LnkFile(dst.getParent(), dst.getName());
            String lnkFilePath = lnk.getLinkFilePath();
            File file = new File(lnkFilePath);
            if (file.exists()) {
                L.info(PackNLS.getString("Shortcut.remove"), file); //$NON-NLS-1$
                file.delete();
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
