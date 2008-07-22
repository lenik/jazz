package net.bodz.bas.cli;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import net.bodz.bas.cli.util.ProtectedShell;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.FileMask;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.FsWalk;
import net.bodz.bas.lang.annotations.OverrideOption;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.types.TypeParsers.ClassInstanceParser;
import net.bodz.bas.types.TypeParsers.WildcardsParser;

@OptionGroup(value = "batch process", rank = -2)
public class BatchProcessCLI extends BasicCLI {

    @Option(alias = ".e", vnam = "ENCODING", doc = "default encoding of input files")
    protected Charset inputEncoding  = Charset.defaultCharset();
    @Option(alias = ".E", vnam = "ENCODING", doc = "default encoding of output files")
    protected Charset outputEncoding = Charset.defaultCharset();

    @Option(alias = "O", vnam = "DIR", doc = "put output files under this directory")
    protected File    outputDirectory;

    @Option(vnam = ".EXT", optional = ".bak", doc = "backup modified files with given extension")
    protected String  backupExtension;

    @Option(alias = "i", doc = "prefer to do case-insensitive comparation")
    protected boolean ignoreCase;

    @Option(alias = "P", doc = "protected mode, don't modify any files")
    protected boolean dryRun;

    @Option(alias = "f", doc = "force overwrite existing files, this includes --error-continue")
    protected boolean force;

    @Option(doc = "always continue when error occurred")
    protected boolean errorContinue;

    @Option(alias = "r", vnam = "[DEPTH]", optional = "65536", doc = "max depth of directories recurse into")
    protected int     recursive;
    @Option(alias = "rl", doc = "process children files before the parent directory")
    boolean           rootLast;

    @Option(alias = "Im", vnam = "FILEMASK", doc = "include specified type of files, default non-hidden files")
    FileMask          inclusiveMask  = new FileMask("f/fH");
    @Option(alias = "IM", vnam = "FILEMASK", doc = "exclude specified type of files")
    FileMask          exclusiveMask;

    @Option(alias = "If", vnam = "WILDCARDS", parser = WildcardsParser.class, doc = "include these filenames")
    Pattern           fileInclusivePattern;
    @Option(alias = "IF", vnam = "WILDCARDS", parser = WildcardsParser.class, doc = "exclude these filenames")
    Pattern           fileExclusivePattern;

    @Option(alias = "Ip", vnam = "REGEXP", doc = "include these pathnames")
    Pattern           pathInclusivePattern;
    @Option(alias = "IP", vnam = "REGEXP", doc = "exclude these pathnames")
    Pattern           pathExclusivePattern;

    @Option(alias = "Id", doc = "also apply filters on directories")
    boolean           filterDirectories;

    @Option(alias = "Ic", vnam = "CLASS(FileFilter)", parser = ClassInstanceParser.class, doc = "using custom file filter, this will override other --I* options")
    FileFilter        fileFilter;

    @Option(vnam = "CLASS(Comparator)", parser = ClassInstanceParser.class, doc = "sort files in each directory")
    Comparator<File>  sortComparator;

    @Option(alias = ".X", vnam = "DIFF-ALG", optional = "gnudiff", doc = "show diff between original and modified files, default using gnudiff")
    DiffComparator    diffAlgorithm;

    @Option(alias = "Xf", vnam = "FORMAT", doc = "Simdiff, ED, Context, Unified, Normal")
    DiffFormat        diffFormat     = DiffFormats.Simdiff;

    @Option(alias = "Xo", vnam = "FILE", doc = "write diff output to specified file")
    CharOut           diffOutput     = CharOuts.stdout;

    @Option(alias = "X3", doc = "diff between src/dst/out, when output to different file")
    boolean           diff3          = false;

    @Option(alias = "X2", doc = "diff between src/out rather then src/dst, only used when output directory is different")
    boolean           diffWithDest   = false;

    protected BatchProcessCLI() {
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

    protected File _getEditTmp(File file) throws IOException {
        String dotExt = Files.getExtension(file, true);
        return File.createTempFile(tmpPrefix, dotExt, tmpDir);
    }

    private boolean diff(File a, File b) throws IOException {
        assert a != null;
        assert b != null;
        if (a.exists() && !b.exists()) {
            L.i.P("[new ]", a);
            return true;
        } else if (!a.exists() && b.exists()) {
            L.i.P("[miss]", a);
            return true;
        }
        if (diffAlgorithm == null) {
            if (Files.equals(a, b))
                return false;
            L.i.P("[edit] ", a);
            return true;
        }
        List<String> al = Files.readLines(a, inputEncoding.name());
        List<String> bl = Files.readLines(b, outputEncoding.name());
        List<DiffInfo> diffs = diffAlgorithm.diffCompare(al, bl);
        if (diffs.size() == 0)
            return false;
        L.i.P("[edit] ", a);
        diffFormat.format(al, bl, diffs, diffOutput);
        return true;
    }

    private File   tmpDir    = Files.getTmpDir();
    private String tmpPrefix = getClass().getSimpleName();

    protected File currentStartFile;

    protected String getRelativeName(File in) {
        return Files.getRelativeName(in, currentStartFile);
    }

    private File _getOutputFile(String relative, File in) {
        if (outputDirectory == null) {
            return in;
        }
        File out = Files.getAbsoluteFile(outputDirectory, relative);
        File outd = out.getParentFile();
        if (outd.isFile())
            throw new Error("invalid output directory: " + outd);
        return out;
    }

    protected File getOutputFile(String relative, File defaultStart) {
        File in = Files.getAbsoluteFile(defaultStart, relative);
        return _getOutputFile(relative, in);
    }

    protected File getOutputFile(String relative) {
        return getOutputFile(relative, currentStartFile);
    }

    protected File getOutputFile(File in) {
        String relative = getRelativeName(in);
        return _getOutputFile(relative, in);
    }

    /** display progress info and calc stat */
    protected void _doFile(File file) {
        Throwable err = null;
        String[] tags = {};
        try {
            L.i.sig("[proc] ", file);
            ProcessResult result = BatchProcessCLI.this.doFile(file);
            stat.add(result);
            if (result == null) {
                L.d.P("[skip] ", file);
                return;
            }
            tags = result.tags;
            if (result.error)
                if ((err = result.cause) == null)
                    L.e.P("[fail] ", file);
            if (result.done)
                L.u.P("[", result.getOperationName(), "] ", //
                        getOutputFile(file));
        } catch (IOException e) {
            err = e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (Throwable e) {
            err = e;
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (err != null) {
                stat.add(ProcessResult.error(err, tags));
                L.e.P("[fail] ", file + ": " + err.getMessage());
            }
        }
    }

    /**
     * <ul>
     * <li>create edit tmp file
     * <li>display diff
     * <li>do result operation
     * </ul>
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult doFile(File file) throws Throwable {
        File editTmp = _getEditTmp(file);
        try {
            return doFile(file, editTmp);
        } finally {
            if (editTmp != null)
                editTmp.delete();
        }
    }

    @OverrideOption(group = "batchProcess")
    protected ProcessResult doFile(File file, File editTmp) throws Throwable {
        ProcessResult result = doFileEdit(file, editTmp);
        if (result == null) // ignored
            return null;
        File dst = getOutputFile(file);
        if (result.dest instanceof String) {
            String relpath = (String) result.dest;
            result.dest = new File(dst, relpath);
        }
        if (result.dest != null)
            dst = (File) result.dest;

        return applyResult(file, dst, editTmp, result);
    }

    protected ProcessResult applyResult(File src, File dst, File edit,
            ProcessResult result) throws IOException, CLIException {
        boolean diffPrinted = false;
        if (result.operation == ProcessResult.SAVE) {
            assert result.changed == null;
            if (edit == null)
                throw new CLIException("can't save: not a batch editor");
            if (!diff3 && !diffWithDest) {
                result.changed = diff(src, edit);
                diffPrinted = true;
            } else
                result.changed = Files.equals(src, edit);
            if (result.changed)
                result.operation = ProcessResult.SAVE_DIFF;
            else
                result.operation = ProcessResult.SAVE_SAME;
        }

        switch (result.operation) {
        case ProcessResult.DELETE:
            if (psh.delete(dst))
                result.setDone();
            return result;

        case ProcessResult.RENAME:
            if (psh.renameTo(src, dst))
                result.setDone();
            return result;

        case ProcessResult.MOVE:
            if (psh.move(src, dst, force))
                result.setDone();
            return result;

        case ProcessResult.COPY:
            if (psh.copy(src, dst))
                result.setDone();
            return result;

        case ProcessResult.SAVE_DIFF:
            assert result.changed;
        case ProcessResult.SAVE_SAME:
            boolean saveLocal = dst.equals(src);
            if (!result.changed && saveLocal)
                return result;
            boolean canOverwrite = saveLocal || force;
            if (dst.exists() && !canOverwrite) {
                // user interaction...
                throw new IllegalStateException("file " + dst
                        + " already existed");
            }

            File dstdir = dst.getParentFile();
            if (dstdir != null)
                psh.mkdirs(dstdir);
            if (!diffPrinted) {
                if (diff3) {
                    diff(src, edit);
                    diff(dst, edit);
                } else if (diffWithDest) {
                    diff(dst, edit);
                } else {
                    diff(src, edit);
                }
            }
            psh.copy(edit, dst);

            result.setDone();
            return result;

        case ProcessResult.SAVE:
        default:
            throw new UnexpectedException("invalid operation: "
                    + result.operation);
        }
    }

    /**
     * open file
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the out file
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult doFileEdit(File in, File out) throws Throwable {
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(in);
            if (out != null)
                outs = new FileOutputStream(out);
            else
                outs = System.out;
            return doFileEdit(ins, outs);
        } finally {
            if (ins != null)
                ins.close();
            if (outs != null)
                outs.close();
        }
    }

    /**
     * read lines
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult doFileEdit(InputStream in, OutputStream out)
            throws Throwable {
        Iterable<String> inIter = Files.readByLine2(inputEncoding.name(), in);
        CharOut cout = CharOuts.stdout;
        if (out != null)
            cout = CharOuts.get(out, outputEncoding.name());
        return doFileEdit(inIter, cout);
    }

    /**
     * for each line
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult doFileEdit(Iterable<String> lines, CharOut out)
            throws Throwable {
        throw new NotImplementedException();
    }

    protected ProtectedShell psh;

    @Override
    protected void _boot() throws Throwable {
        super._boot();
        psh = new ProtectedShell(!dryRun, L);
    }

    protected final ProcessResultStat stat = new ProcessResultStat();

    @Override
    protected void _main(String[] args) throws Throwable {
        super._main(args);
        if (L.showDetail())
            stat.dumpDetail(L.d);
        else if (L.showMessage())
            stat.dumpBrief(L.m);
        // System.exit(stat.errors);
    }

    @Override
    @OverrideOption(group = "batchProcess")
    protected void doFileArgument(final File startFile) throws Throwable {
        L.i.sig("[start] ", startFile);
        currentStartFile = startFile;
        FileFilter walkfilter = fileFilter;
        FsWalk walker = new FsWalk(startFile, walkfilter, filterDirectories,
                recursive, rootLast, sortComparator) {
            @Override
            public void process(File file) throws IOException {
                _doFile(file);
            }
        };
        walker.walk();
    }

    @Override
    protected final void doFileArgument(File file, InputStream in)
            throws Throwable {
        throw new NotImplementedException();
    }

}
