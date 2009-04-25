package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.util.Strings;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;

public class CreateFile extends _Component {

    private final String  base;
    private final String  path;
    private final Object  data;
    private final Object  charset;
    private final boolean append;

    public CreateFile(String base, String path, Object data) {
        this(base, path, data, null, false);
    }

    public CreateFile(String base, String path, Object data, Object charset, boolean append) {
        super(true, false);
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

    class CInstall extends CJob {

        public CInstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File dest = new File(session.getFile(base), path);
            try {
                String abbr = Strings.ellipse(String.valueOf(data), 40);
                if (append)
                    L.finfo("Append to %s: %s", dest, abbr);
                else
                    L.finfo("Write to %s: %s", dest, abbr);
                Files.write(dest, data, charset, append);
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
            File dest = new File(session.getFile(base), path);
            L.info("Delete file ", dest);
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
