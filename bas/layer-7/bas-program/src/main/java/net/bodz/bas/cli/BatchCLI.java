package net.bodz.bas.cli;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.io.FileFinder;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.meta.codehint.OverrideOption;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;

@OptionGroup(value = "batch", rank = -2)
public class BatchCLI
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
    FileFilter fileFilter;

    /**
     * Sort files in each directory
     * 
     * @option =CLASS(Comparator)
     */
    // @ParseBy(GetInstanceParser.class)
    Comparator<IFile> sortComparator;

    public class Parameters {

        public Charset getInputEncoding() {
            return inputEncoding;
        }

        public void setInputEncoding(Charset inputEncoding) {
            BatchCLI.this.inputEncoding = inputEncoding;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            BatchCLI.this.ignoreCase = ignoreCase;
        }

        public boolean isErrorContinue() {
            return errorContinue;
        }

        public void setErrorContinue(boolean errorContinue) {
            BatchCLI.this.errorContinue = errorContinue;
        }

        public int getRecursive() {
            return recursive;
        }

        public void setRecursive(int recursive) {
            BatchCLI.this.recursive = recursive;
        }

        public boolean isRootLast() {
            return rootLast;
        }

        public void setRootLast(boolean rootLast) {
            BatchCLI.this.rootLast = rootLast;
        }

        public FileMaskedModifiers getInclusiveMask() {
            return inclusiveMask;
        }

        public void setInclusiveMask(FileMaskedModifiers inclusiveMask) {
            BatchCLI.this.inclusiveMask = inclusiveMask;
        }

        public FileMaskedModifiers getExclusiveMask() {
            return exclusiveMask;
        }

        public void setExclusiveMask(FileMaskedModifiers exclusiveMask) {
            BatchCLI.this.exclusiveMask = exclusiveMask;
        }

        public GlobPattern getFileInclusivePattern() {
            return fileInclusivePattern;
        }

        public void setFileInclusivePattern(GlobPattern fileInclusivePattern) {
            BatchCLI.this.fileInclusivePattern = fileInclusivePattern;
        }

        public GlobPattern getFileExclusivePattern() {
            return fileExclusivePattern;
        }

        public void setFileExclusivePattern(GlobPattern fileExclusivePattern) {
            BatchCLI.this.fileExclusivePattern = fileExclusivePattern;
        }

        public Pattern getPathInclusivePattern() {
            return pathInclusivePattern;
        }

        public void setPathInclusivePattern(Pattern pathInclusivePattern) {
            BatchCLI.this.pathInclusivePattern = pathInclusivePattern;
        }

        public Pattern getPathExclusivePattern() {
            return pathExclusivePattern;
        }

        public void setPathExclusivePattern(Pattern pathExclusivePattern) {
            BatchCLI.this.pathExclusivePattern = pathExclusivePattern;
        }

        public boolean isFilterDirectories() {
            return prune;
        }

        public void setFilterDirectories(boolean filterDirectories) {
            BatchCLI.this.prune = filterDirectories;
        }

        public FileFilter getFileFilter() {
            return fileFilter;
        }

        public void setFileFilter(FileFilter fileFilter) {
            BatchCLI.this.fileFilter = fileFilter;
        }

        public Comparator<IFile> getSortComparator() {
            return sortComparator;
        }

        public void setSortComparator(Comparator<IFile> sortComparator) {
            BatchCLI.this.sortComparator = sortComparator;
        }

    }

    Parameters parameters;

    protected Parameters parameters() {
        if (parameters == null)
            parameters = new Parameters();
        return parameters;
    }

    protected BatchCLI() {

    }

    /**
     * Set up {@link #fileFilter} and {@link #psh}.
     */
    @Override
    void _postInit() {
        fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
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
                    if (!pathInclusivePattern.matcher(file.getPath()).find())
                        return false;
                if (pathExclusivePattern != null)
                    if (pathExclusivePattern.matcher(file.getPath()).find())
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

    /**
     * Implemented as: walk start from the file argument.
     */
    @Override
    @OverrideOption(group = "batch")
    protected void doFileArgument(final IFile file)
            throws Exception {
        currentStartFile = file;
        FileFinder finder = new FileFinder(fileFilter, prune, recursive, file);
        if (rootLast)
            finder.setOrder(FileFinder.FILE | FileFinder.DIR_POST);
        if (sortComparator != null)
            finder.setComparator(sortComparator);
        for (IFile f : finder)
            _processFile(FilePath.canoniOf(f));
    }

    /**
     * The file-argument is handled for fs-walking.
     * 
     * This method will only be called if no argument is given.
     * 
     * @see #_processFile(IFile)
     */
    @Override
    @Deprecated
    protected void doFileArgument(IFile file, InputStream in)
            throws Exception {
        doFile(file, in);
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
            L.errorFormat(/* 1, */e, "Failed to process %s: %s", file, e.getMessage());
            if (!errorContinue)
                throw new ControlBreak();
        } finally {
        }
    }

    protected void doFile(IFile file)
            throws Exception {
        InputStream in = file.getInputSource().newInputStream();
        try {
            doFile(file, in);
        } finally {
            in.close();
        }
    }

    /**
     * if no argument and {@link #_getDefaultIn()} returns a non-null value, a null-file and the
     * default-in will be passed in.
     */
    protected void doFile(IFile file, InputStream in)
            throws Exception {
        throw new NotImplementedException();
    }

    public class Methods {

        public void doFileArgument(IFile file)
                throws Exception {
            BatchCLI.this.doFileArgument(file);
        }

        public void doFile(IFile file)
                throws Exception {
            BatchCLI.this.doFile(file);
        }

        public void doFile(IFile file, InputStream in)
                throws Exception {
            BatchCLI.this.doFile(file, in);
        }

    }

    Methods methods;

    public Methods methods() {
        if (methods == null)
            methods = new Methods();
        return methods;
    }

}
