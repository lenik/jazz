package net.bodz.redist.installer.builtins;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.ISession;

import com.roxes.win32.LnkFile;

public class Shortcut
        extends AbstractComponent {

    private final String srcbase;
    private final String srcpath;
    private final String dstbase;
    private final String dstpath;
    private final String arguments;
    private final String workingDir;
    private final String text;
    private final String icon;
    private final int iconIndex;

    public Shortcut(String srcbase, String srcpath, String dstbase, String dstpath) {
        this(srcbase, srcpath, dstbase, dstpath, null);
    }

    public Shortcut(String srcbase, String srcpath, String dstbase, String dstpath, String arguments) {
        this(srcbase, srcpath, dstbase, dstpath, arguments, null, null, null, 0);
    }

    public Shortcut(String srcbase, String srcpath, String dstbase, String dstpath, String arguments,
            String workingDir, String text, String icon, int iconIndex) {
        super(false, true);
        if (srcbase == null)
            throw new NullPointerException("srcbase");
        if (dstbase == null)
            throw new NullPointerException("dstbase");
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

    class CInstall
            extends CJob {

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
                parentFile = FilePath.canoniOf(parentFile);
                parentFile.mkdirs();
            }
            logger.info(PackNLS.getString("Shortcut.create"), dst);
            if (SystemInfo.isWin32()) {
                File _src = FilePath.canoniOf(src);
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

    class CUninstall
            extends CJob {

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
                logger.info(PackNLS.getString("Shortcut.remove"), file);
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
