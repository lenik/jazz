package net.bodz.bas.cli;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.lang.control.ControlBreak;
import net.bodz.bas.meta.program.Option;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.meta.util.OverrideOption;
import net.bodz.bas.util.io.FilePath;
import net.bodz.bas.util.regex.GlobPattern;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.traverse.FileFinder;

@OptionGroup(value = "batch", rank = -2)
public class BatchCLI
        extends BasicCLI {

    @Option(alias = ".e", vnam = "ENCODING", doc = "default encoding of input files")
    Charset inputEncoding = Charset.defaultCharset();

    @Option(alias = "i", doc = "prefer to do case-insensitive comparation")
    boolean ignoreCase;

    @Option(doc = "always continue when error occurred")
    boolean errorContinue;

    @Option(alias = "r", vnam = "[DEPTH]", optional = "65536", doc = "max depth of directories recurse into")
    int recursive;
    @Option(alias = "rl", doc = "process children files before the parent directory")
    boolean rootLast;

    @Option(alias = "Im", vnam = "FILEMASK", doc = "include specified type of files, default non-hidden files")
    FileMaskedModifiers inclusiveMask = new FileMaskedModifiers("f/fH");
    @Option(alias = "IM", vnam = "FILEMASK", doc = "exclude specified type of files")
    FileMaskedModifiers exclusiveMask;

    @Option(alias = "If", vnam = "WILDCARDS", doc = "include these filenames")
    GlobPattern fileInclusivePattern;
    @Option(alias = "IF", vnam = "WILDCARDS", doc = "exclude these filenames")
    GlobPattern fileExclusivePattern;

    @Option(alias = "Ip", vnam = "REGEXP", doc = "include these pathnames")
    Pattern pathInclusivePattern;
    @Option(alias = "IP", vnam = "REGEXP", doc = "exclude these pathnames")
    Pattern pathExclusivePattern;

    @Option(alias = "rp", doc = "don't recurse into excluded directories")
    boolean prune;

    @Option(alias = "Ic", vnam = "CLASS(FileFilter)", doc = "using custom file filter, this will override other --I* options")
// @ParseBy(GetInstanceParser.class)
    FileFilter fileFilter;

    @Option(vnam = "CLASS(Comparator)", doc = "sort files in each directory")
// @ParseBy(GetInstanceParser.class)
    Comparator<File> sortComparator;

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

        public Comparator<File> getSortComparator() {
            return sortComparator;
        }

        public void setSortComparator(Comparator<File> sortComparator) {
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
    protected File currentStartFile;

    protected String getRelativeName(File in) {
        return FilePath.getRelativeName(in, currentStartFile);
    }

    /**
     * Implemented as: walk start from the file argument.
     */
    @Override
    @OverrideOption(group = "batch")
    protected void doFileArgument(final File file)
            throws Exception {
        currentStartFile = file;
        FileFinder finder = new FileFinder(fileFilter, prune, recursive, file);
        if (rootLast)
            finder.setOrder(FileFinder.FILE | FileFinder.DIR_POST);
        if (sortComparator != null)
            finder.setComparator(sortComparator);
        for (File f : finder)
            _processFile(FilePath.canoniOf(f));
    }

    /**
     * The file-argument is handled for fs-walking.
     * 
     * This method will only be called if no argument is given.
     * 
     * @see #_processFile(File)
     */
    @Override
    @Deprecated
    protected void doFileArgument(File file, InputStream in)
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
    protected void _processFile(File file) {
        assert file != null;
        try {
            doFile(file);
        } catch (Throwable e) {
            logger.errorFormat(/* 1, */e, "Failed to process %s: %s", file, e.getMessage());
            if (!errorContinue)
                throw new ControlBreak();
        } finally {
        }
    }

    protected void doFile(File file)
            throws Exception {
        FileInputStream in = new FileInputStream(file);
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
    protected void doFile(File file, InputStream in)
            throws Exception {
        throw new NotImplementedException();
    }

    public class Methods {

        public void doFileArgument(File file)
                throws Exception {
            BatchCLI.this.doFileArgument(file);
        }

        public void doFile(File file)
                throws Exception {
            BatchCLI.this.doFile(file);
        }

        public void doFile(File file, InputStream in)
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
