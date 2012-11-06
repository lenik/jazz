package net.bodz.bas.cli.skel;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.util.iter.PrefetchedIterator;
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
     * @option -rl
     */
    boolean rootLast;

    /**
     * Include specified type of files, default non-hidden files
     * 
     * @option -lm =FILEMASK
     */
    FileMaskedModifiers inclusiveMask = new FileMaskedModifiers("f/fH");

    /**
     * Exclude specified type of files.
     * 
     * @option -IM =FILEMASK
     */
    FileMaskedModifiers exclusiveMask;

    /**
     * Include these filenames.
     * 
     * @option -If =WILDCARDS
     */
    GlobPattern fileInclusivePattern;

    /**
     * exclude these filenames
     * 
     * @option -IF =WILDCARDS
     */
    GlobPattern fileExclusivePattern;

    /**
     * Include these pathnames.
     * 
     * @option -Ip =REGEXP
     */
    Pattern pathInclusivePattern;

    /**
     * Exclude these pathnames
     * 
     * @option -IP =REGEXP
     */
    Pattern pathExclusivePattern;

    /**
     * Don't recurse into excluded directories.
     * 
     * @option -rp
     */
    boolean prune;

    /**
     * Using custom file filter, this will override other --I* options.
     * 
     * @option -Ic =CLASS(FileFilter)
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

    /**
     * Set up {@link #fileFilter} and {@link #psh}.
     */
    @Override
    void _postInit() {
        fileFilter = new IFileFilter() {
            @Override
            public boolean accept(IFile file) {
                if (inclusiveMask != null)
                    if (!inclusiveMask.test(file))
                        return false;
                if (exclusiveMask != null)
                    if (exclusiveMask.test(file))
                        return false;
                if (fileInclusivePattern != null)
                    if (!fileInclusivePattern.matcher(file.getName()).matches())
                        return false;
                if (fileExclusivePattern != null)
                    if (fileExclusivePattern.matcher(file.getName()).matches())
                        return false;
                if (pathInclusivePattern != null)
                    if (!pathInclusivePattern.matcher(file.getPath().toString()).find())
                        return false;
                if (pathExclusivePattern != null)
                    if (pathExclusivePattern.matcher(file.getPath().toString()).find())
                        return false;
                return true;
            }
        };
    }

    /** canonical file */
    protected IFile currentStartFile;

    protected String getRelativeName(IFile in) {
        // FilePath.getRelativeName(in.getPath().toString(), currentStartFile);
        IPath relativePath = in.getPath().getRelativePath(currentStartFile.getPath());
        return relativePath.getLocalPath();
    }

    class TreeScanner
            extends PrefetchedIterator<IFile> {

        @Override
        protected IFile fetch() {
            currentStartFile = file;
            FileFinder finder = new FileFinder(fileFilter, prune, recursive, file);
            if (rootLast)
                finder.setOrder(FileFinder.FILE | FileFinder.DIR_POST);
            if (sortComparator != null)
                finder.setComparator(sortComparator);
            for (IFile f : finder)
                _processFile(f);
        }
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
