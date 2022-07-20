package net.bodz.bas.program.skel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.err.ExceptionLog;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.fn.IFilter;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.builtin.InputStreamSource;
import net.bodz.bas.io.res.builtin.OutputStreamTarget;
import net.bodz.bas.log.LogRecord;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.util.stat.StatNode;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.find.FileFinder;
import net.bodz.bas.vfs.util.find.FileFoundEvent;
import net.bodz.bas.vfs.util.find.IFileFoundListener;

/**
 * Batch File Scanner
 *
 * @syntax $programName [OPTIONS] [--] FILES...
 */
public abstract class BatchCLI
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(BatchCLI.class);

    /**
     * Ddefault encoding of input files
     *
     * @option -e =ENCODING weak
     */
    Charset inputEncoding = Charset.defaultCharset();

    /**
     * Prefer to do case-insensitive comparation.
     *
     * @option -i
     */
    boolean ignoreCase;

    /**
     * Always continue when error occurred.
     *
     * @option
     */
    boolean errorContinue;

    /**
     * Max depth of directories recurse into.
     *
     * @option -r =DEPTH default=65536
     */
    int recursive;

    /**
     * Process children files before the parent directory.
     *
     * @option
     */
    boolean rootLast;

    /**
     * Include specified type of files, default non-hidden files
     *
     * @option =FILEMASK
     */
    FileMaskedModifiers includeMask = new FileMaskedModifiers("f/fH");

    /**
     * Exclude specified type of files.
     *
     * @option -IM =FILEMASK
     */
    FileMaskedModifiers excludeMask;

    /**
     * Include these filenames.
     *
     * @option =WILDCARDS
     */
    GlobPattern includeName;

    /**
     * exclude these filenames
     *
     * @option =WILDCARDS
     */
    GlobPattern excludeName;

    /**
     * Include these pathnames.
     *
     * @option =REGEXP
     */
    Pattern includePath;

    /**
     * Exclude these pathnames
     *
     * @option =REGEXP
     */
    Pattern excludePath;

    /**
     * Don't recurse into excluded directories.
     *
     * @option
     */
    boolean prune;

    /**
     * Using custom file filter, this will override other --include/exclude* options.
     *
     * @option =CLASS(FileFilter)
     */
    // @ParseBy(GetInstanceParser.class)
    IFilter<IFile> fileFilter;

    /**
     * Sort files in each directory
     *
     * @option =CLASS(Comparator)
     */
    // @ParseBy(GetInstanceParser.class)
    Comparator<IFile> sortComparator;

    private IFile _contextFile;
    private IPath _contextPath;
    private String _tmpPrefix = getClass().getSimpleName() + "-";
    private StatNode _statRoot = new StatNode();

    class DefaultFileFilter
            implements
                IFilter<IFile> {

        @Override
        public boolean accept(IFile file) {
            if (includeMask != null)
                if (!includeMask.test(file))
                    return false;
            if (excludeMask != null)
                if (excludeMask.test(file))
                    return false;
            if (includeName != null)
                if (!includeName.matcher(file.getName()).matches())
                    return false;
            if (excludeName != null)
                if (excludeName.matcher(file.getName()).matches())
                    return false;
            if (includePath != null)
                if (!includePath.matcher(file.getPath().toString()).find())
                    return false;
            if (excludePath != null)
                if (excludePath.matcher(file.getPath().toString()).find())
                    return false;
            return true;
        }

    }

    @Override
    protected void _reconfigure()
            throws Exception {
        super._reconfigure();
        if (fileFilter == null)
            fileFilter = new DefaultFileFilter();
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        for (String arg : expandWildcards(args)) {
            IFile argFile = VFS.resolve(arg);
            setContextFile(argFile);

            for (IFile childFile : traverse(argFile)) {
                FileHandler handler = beginFile(childFile);
                try {
                    processFile(handler);
                } catch (Exception exception) {
                    handler.getExceptionLog().log(exception);
                }
                endFile(handler);

                if (handler.isErrored()) {
                    ExceptionLog log = handler.getExceptionLog();
                    for (LogRecord entry : log)
                        logger.getLogSink().log(entry);
                    if (!errorContinue)
                        break;
                }
            }
        }
    }

    protected Iterable<IFile> traverse(IFile start) {
        FileFinder finder = new FileFinder(fileFilter, prune, recursive, start);

        if (rootLast)
            finder.setOrder(FileFinder.FILE | FileFinder.DIR_POST);

        if (sortComparator != null)
            finder.setComparator(sortComparator);

        finder.addFileFoundListener(new IFileFoundListener() {
            @Override
            public void fileFound(FileFoundEvent event) {
                _fileFound(event);
            }
        });
        return finder;
    }

    protected void _fileFound(FileFoundEvent event) {
    }

    IFile getContextFile() {
        return _contextFile;
    }

    IPath getContextPath() {
        return _contextPath;
    }

    protected void setContextFile(IFile contextFile) {
        if (contextFile == null) {
            this._contextFile = null;
            this._contextPath = null;
        } else {
            this._contextFile = contextFile;
            this._contextPath = contextFile.getPath();
        }
    }

    protected String getPathSpecToContext(IFile file) {
        IPath _pathSpec = file.getPath().getRelativePathTo(_contextPath);
        String pathSpec = _pathSpec.toString();
        return pathSpec;
    }

    protected synchronized FileHandler beginFile(IFile file) {
        String pathSpec = getPathSpecToContext(file);

        FileHandler handler = new FileHandler(pathSpec, file, null);
        handler.setTmpPrefix(_tmpPrefix);

        return handler;
    }

    protected synchronized void endFile(FileHandler handler) {
        _statRoot.resolveCounter("...");
        if (handler.isErrored() && !errorContinue) {
        }
    }

    public abstract void processFile(FileHandler handler)
            throws Exception;

    public void process(IStreamInputSource inputSource, IStreamOutputTarget outputTarget)
            throws Exception {
        FileHandler handler = new FileHandler(inputSource, outputTarget);
        processFile(handler);
    }

    public void processStream(InputStream in, OutputStream out)
            throws Exception {
        InputStreamSource inputSource = new InputStreamSource(in);
        OutputStreamTarget outputTarget = new OutputStreamTarget(out);
        process(inputSource, outputTarget);
    }

    /**
     * The I/O stream rewrite implementation.
     *
     * @return Whether modification to input is made. If any byte written to the output is different
     *         to the input, this method should returns <code>true</code>. If modification is
     *         unknown, returns <code>null</code>.
     */
    @Deprecated
    public Boolean processImpl(InputStream in, OutputStream out)
            throws IOException {
        // FileHandler handler = new FileHandler(null, sourceFile, targetFile);
        // handler.save();
        throw new NotImplementedException();
    }

}
