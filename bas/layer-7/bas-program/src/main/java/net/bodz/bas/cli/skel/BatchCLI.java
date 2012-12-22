package net.bodz.bas.cli.skel;

import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.db.stat.StatNode;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.context.VFSColos;
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
    IFileFilter fileFilter;

    /**
     * Sort files in each directory
     * 
     * @option =CLASS(Comparator)
     */
    // @ParseBy(GetInstanceParser.class)
    Comparator<IFile> sortComparator;

    StatNode statRoot;

    class DefaultFileFilter
            implements IFileFilter {

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

            for (IFile childFile : traverse(argFile)) {
                IPath childRelativePath = childFile.getPath().getRelativePathTo(argFile.getPath());

                String filename = arg + "/" + childRelativePath.toString();

                FileHandler handler = beginFile(filename);
                try {
                    processFile(handler);
                } catch (Exception exception) {
                    handler.getExceptionLog().log(exception);
                }
                endFile(handler);
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

    private IFile contextFile;
    private String _tmpPrefix = getClass().getSimpleName();

    protected IFile getContextFile() {
        return contextFile;
    }

    protected synchronized FileHandler beginFile(String fileName) {
        IFile cwd = VFSColos.workdir.get();
        IFile file = cwd.resolve(fileName);

        if (contextFile != null)
            throw new IllegalStateException("beginFile/endFile couldn't be nested.");
        contextFile = file;

        FileHandler handler = new FileHandler(fileName, file);
        handler.setTmpPrefix(_tmpPrefix);
        return handler;
    }

    protected synchronized void endFile(FileHandler handler) {
        if (contextFile != null)
            throw new IllegalStateException("endFile without beginFile.");

        try {
            statRoot.resolveCounter("...");
            if (handler.isErrored() && !errorContinue) {
            }

        } finally {
            contextFile = null;
        }
    }

    protected abstract void processFile(FileHandler handler)
            throws Exception;

}
