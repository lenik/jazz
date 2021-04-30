package net.bodz.pkg.sis.c.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.model.action.IProgressMonitor;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.pkg.sis.AbstractSisComponent;
import net.bodz.pkg.sis.ISisComponent;

public class CreateFile
        extends AbstractSisComponent {

    private static final long serialVersionUID = 1L;

    private final String locationName;
    private final String path;
    private final IStreamInputSource source;
    private final boolean append;

    public CreateFile(ISisComponent parent, String base, String path, IStreamInputSource source) {
        this(parent, base, path, source, false);
    }

    public CreateFile(ISisComponent parent, String base, String path, IStreamInputSource source, boolean append) {
        super(parent, "create-file");

        if (base == null)
            throw new NullPointerException("base");
        if (path == null)
            throw new NullPointerException("path");
        if (source == null)
            throw new NullPointerException("source");

        this.locationName = base;
        this.path = path;
        this.append = append;
        this.source = source;

        setSelected(true);
    }

    @Override
    public void install(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);

        File location = getProject().getValue(locationName);
        File dest = new File(location, path);
        PojfFile destFile = new PojfFile(dest);

        try {
            OpenOption[] openOptions = {};
            if (CreateFile.this.append)
                openOptions = new OpenOption[] { StandardOpenOption.APPEND };

            String abbr = Strings.ellipsis(String.valueOf(source), 40);
            if (append)
                logger.infof(nls.tr("Append to %s: %s"), dest, abbr);
            else
                logger.infof(nls.tr("Create file %s: %s"), dest, abbr);

            destFile.to(StreamWriting.class).setOpenOptions(openOptions).write(source);
        } catch (IOException e) {
            if (logger.error(e, e.getMessage()))
                ; // XXX while-loop retry
        }
    }

    @Override
    public void remove(IProgressMonitor monitor, IOptions options) {
        ILogger logger = options.get(ILogger.class);

        File location = getProject().getValue(locationName);
        File dest = new File(location, path);
        logger.info(nls.tr("Delete file ") + dest);
        dest.delete();
    }

}
