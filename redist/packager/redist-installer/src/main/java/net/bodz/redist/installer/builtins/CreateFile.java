package net.bodz.redist.installer.builtins;

import java.io.File;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.ISession;

public class CreateFile
        extends AbstractComponent {

    private final String base;
    private final String path;
    private final IStreamInputSource source;
    private final boolean append;

    public CreateFile(String base, String path, IStreamInputSource source) {
        this(base, path, source, false);
    }

    public CreateFile(String base, String path, IStreamInputSource source, boolean append) {
        super(SELECTED);
        if (base == null)
            throw new NullPointerException("base");
        if (path == null)
            throw new NullPointerException("path");
        if (source == null)
            throw new NullPointerException("source");
        this.base = base;
        this.path = path;
        this.append = append;
        this.source = source;
    }

    class CInstall
            extends CJob {

        public CInstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            File dest = new File(session.getFile(base), path);
            PojfFile destFile = new PojfFile(dest);

            try {
                OpenOption[] options = {};
                if (CreateFile.this.append)
                    options = new OpenOption[] { StandardOpenOption.APPEND };

                String abbr = Strings.ellipse(String.valueOf(source), 40);
                if (append)
                    logger.infof(tr._("Append to %s: %s"), dest, abbr);
                else
                    logger.infof(tr._("Create file %s: %s"), dest, abbr);

                destFile.tooling()._for(StreamWriting.class).setOpenOptions(options).write(source);
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
            logger.info(tr._("Delete file "), dest);
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
