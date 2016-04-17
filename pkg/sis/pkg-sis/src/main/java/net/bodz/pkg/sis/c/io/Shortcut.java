package net.bodz.pkg.sis.c.io;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.model.action.IProgressMonitor;
import net.bodz.pkg.sis.AbstractSisComponent;
import net.bodz.pkg.sis.ISisComponent;

import com.roxes.win32.LnkFile;

public class Shortcut
        extends AbstractSisComponent {

    private static final long serialVersionUID = 1L;

    private final String srcbase;
    private final String srcpath;
    private final String dstbase;
    private final String dstpath;
    private final String arguments;
    private final String workingDir;
    private final String text;
    private final String icon;
    private final int iconIndex;

    public Shortcut(ISisComponent parent, String srcbase, String srcpath, String dstbase, String dstpath) {
        this(parent, srcbase, srcpath, dstbase, dstpath, null);
    }

    public Shortcut(ISisComponent parent, String srcbase, String srcpath, String dstbase, String dstpath,
            String arguments) {
        this(parent, srcbase, srcpath, dstbase, dstpath, arguments, null, null, null, 0);
    }

    public Shortcut(ISisComponent parent, String srcbase, String srcpath, String dstbase, String dstpath,
            String arguments, String workingDir, String text, String icon, int iconIndex) {
        super(parent, "shortcut");

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

        setSelected(true);
    }

    @Override
    public void install(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);

        File srcLocation = getProject().getValue(srcbase);
        File dstLocation = getProject().getValue(dstbase);

        File src = new File(srcLocation, srcpath);
        File dst = new File(dstLocation, dstpath);

        if (!dst.exists()) {
            // mkdir -p dst.parent
            File parentFile = dst.getParentFile();
            parentFile = FilePath.canoniOf(parentFile);
            parentFile.mkdirs();
        }

        logger.info(tr._("Create shortcut "), dst);
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

    @Override
    public void remove(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);

        File dstLocation = getProject().getValue(dstbase);
        File dst = new File(dstLocation, dstpath);
        LnkFile lnk = new LnkFile(dst.getParent(), dst.getName());
        String lnkFilePath = lnk.getLinkFilePath();
        File file = new File(lnkFilePath);
        if (file.exists()) {
            logger.info(tr._("Remove shortcut "), file);
            file.delete();
        }
    }

}
