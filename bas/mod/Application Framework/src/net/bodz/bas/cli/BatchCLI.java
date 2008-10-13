package net.bodz.bas.cli;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.cli.a.ParseBy;
import net.bodz.bas.io.FileMask;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.FsWalk;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.TypeParsers.GetInstanceParser;
import net.bodz.bas.types.TypeParsers.WildcardsParser;

@OptionGroup(value = "batch", rank = -2)
public class BatchCLI extends BasicCLI {

    @Option(alias = ".e", vnam = "ENCODING", doc = "default encoding of input files")
    protected Charset inputEncoding = Charset.defaultCharset();

    @Option(alias = "i", doc = "prefer to do case-insensitive comparation")
    protected boolean ignoreCase;

    @Option(doc = "always continue when error occurred")
    protected boolean errorContinue;

    @Option(alias = "r", vnam = "[DEPTH]", optional = "65536", doc = "max depth of directories recurse into")
    protected int     recursive;
    @Option(alias = "rl", doc = "process children files before the parent directory")
    boolean           rootLast;

    @Option(alias = "Im", vnam = "FILEMASK", doc = "include specified type of files, default non-hidden files")
    FileMask          inclusiveMask = new FileMask("f/fH");
    @Option(alias = "IM", vnam = "FILEMASK", doc = "exclude specified type of files")
    FileMask          exclusiveMask;

    @Option(alias = "If", vnam = "WILDCARDS", doc = "include these filenames")
    @ParseBy(WildcardsParser.class)
    Pattern           fileInclusivePattern;
    @Option(alias = "IF", vnam = "WILDCARDS", doc = "exclude these filenames")
    @ParseBy(WildcardsParser.class)
    Pattern           fileExclusivePattern;

    @Option(alias = "Ip", vnam = "REGEXP", doc = "include these pathnames")
    Pattern           pathInclusivePattern;
    @Option(alias = "IP", vnam = "REGEXP", doc = "exclude these pathnames")
    Pattern           pathExclusivePattern;

    @Option(alias = "Id", doc = "also apply filters on directories")
    boolean           filterDirectories;

    @Option(alias = "Ic", vnam = "CLASS(FileFilter)", doc = "using custom file filter, this will override other --I* options")
    @ParseBy(GetInstanceParser.class)
    FileFilter        fileFilter;

    @Option(vnam = "CLASS(Comparator)", doc = "sort files in each directory")
    @ParseBy(GetInstanceParser.class)
    Comparator<File>  sortComparator;

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
                if (inclusiveMask != null && inclusiveMask.set())
                    if (!inclusiveMask.test(file))
                        return false;
                if (exclusiveMask != null && exclusiveMask.set())
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
        return Files.getRelativeName(in, currentStartFile);
    }

    @Override
    @OverrideOption(group = "batch")
    protected void doFileArgument(final File file) throws Throwable {
        currentStartFile = file;
        FileFilter walkfilter = fileFilter;
        FsWalk walker = new FsWalk(file, walkfilter, filterDirectories,
                recursive, rootLast, sortComparator) {
            @Override
            public void process(File file) throws IOException {
                _doFile(Files.canoniOf(file));
            }
        };
        walker.walk();
    }

    /**
     * The file-argument is handled for fs-walking.
     * 
     * This method will only be called if no argument is given.
     * 
     * @see #doFile(File)
     */
    @Override
    @Deprecated
    protected void doFileArgument(File file, InputStream in) throws Throwable {
        doFile(file, in);
    }

    /**
     * display progress info and calc stat
     * 
     * @param file
     *            canonical file
     * @throws FileNotFoundException
     */
    protected void _doFile(File file) {
        assert file != null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            doFile(file, in);
        } catch (Throwable e) {
            L.e.P("file ", file, " error: ", e.getMessage());
            if (L.showDetail())
                e.printStackTrace();
            if (!errorContinue)
                throw new ControlBreak();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * if no argument and {@link #_getDefaultIn()} returns a non-null value, a
     * null-file and the default-in will be passed in.
     */
    protected void doFile(File file, InputStream in) throws Throwable {
        throw new NotImplementedException();
    }

}
