package net.bodz.art.installer.builtins;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.bodz.art.installer.AbstractComponent;
import net.bodz.art.installer.ISession;
import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.c.string.Strings;

public class CreateFile
        extends AbstractComponent {

    private final String base;
    private final String path;
    private final Object data;
    private final Object charset;
    private final boolean append;

    public CreateFile(String base, String path, Object data) {
        this(base, path, data, null, false);
    }

    public CreateFile(String base, String path, Object data, Object charset, boolean append) {
        super(false, true);
        if (base == null)
            throw new NullPointerException("base");
        if (path == null)
            throw new NullPointerException("path");
        if (data == null)
            throw new NullPointerException("data");
        this.base = base;
        this.path = path;
        this.append = append;
        this.data = data;
        this.charset = charset;
    }

    class CInstall
            extends CJob {

        public CInstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File dest = new File(session.getFile(base), path);
            try {
                boolean append = CreateFile.this.append && dest.exists();
                String abbr = Strings.ellipse(String.valueOf(data), 40);
                if (append) {
                    L.finfo(PackNLS.getString("CreateFile.appendTo_ss"), dest, abbr);
                    Files.append(dest, data, charset);
                } else {
                    L.finfo(PackNLS.getString("CreateFile.createFile_ss"), dest, abbr);
                    Files.createFile(dest, data, charset);
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
            File dest = new File(session.getFile(base), path);
            L.info(PackNLS.getString("CreateFile.deleteFile"), dest);
            dest.delete();
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
