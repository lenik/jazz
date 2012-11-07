package net.bodz.bas.cli.skel;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.FileFinder;
import net.bodz.bas.vfs.util.IFileFilter;

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
    void _postInit() {
        if (fileFilter == null)
            fileFilter = new DefaultFileFilter();
    }

    /** canonical file */
    protected IFile currentStartFile;

    protected String getRelativeName(IFile in) {
        // FilePath.getRelativeName(in.getPath().toString(), currentStartFile);
        IPath relativePath = in.getPath().getRelativePath(currentStartFile.getPath());
        return relativePath.getLocalPath();
    }

    class ManagedFileFinder
            extends FileFinder {

        @Override
        public Iterator<IFile> iterator() {
            return super.iterator();
        }

    }

    protected Iterable<IFile> scanFiles(IFile start) {
        FileFinder finder = new FileFinder(fileFilter, prune, recursive, start);
        if (rootLast)
            finder.setOrder(FileFinder.FILE | FileFinder.DIR_POST);
        if (sortComparator != null)
            finder.setComparator(sortComparator);
        return finder;
    }

    /**
     * display progress info and calc stat
     * 
     * @param file
     *            canonical file
     * @throws FileNotFoundException
     */
    protected void _processFile(IFile file) {
        assert file != null;
        try {
            doFile(file);
        } catch (Throwable e) {
            logger.errorf(/* 1, */e, "Failed to process %s: %s", file, e.getMessage());
            if (!errorContinue)
                throw new ControlBreak();
        } finally {
        }
    }

}
