package net.bodz.bas.cli;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
import net.bodz.bas.types.TypeParsers.ClassInstanceParser;
import net.bodz.bas.types.TypeParsers.WildcardsParser;
import net.bodz.bas.types.diff.DiffComparator;
import net.bodz.bas.types.diff.DiffFormat;
import net.bodz.bas.types.diff.DiffFormats;
import net.bodz.bas.types.diff.DiffInfo;

@OptionGroup(value = "batch process", rank = -2)
public class BatchProcessCLI extends BasicCLI {

    @Option(alias = ".e", vnam = "ENCODING", doc = "default encoding of input files")
    protected Charset          inputEncoding  = Charset.defaultCharset();
    @Option(alias = ".E", vnam = "ENCODING", doc = "default encoding of output files")
    protected Charset          outputEncoding = Charset.defaultCharset();

    @Option(alias = "O", vnam = "DIR", doc = "put output files under this directory")
    protected File             outputDirectory;

    @Option(vnam = ".EXT", optional = ".bak", doc = "backup modified files with given extension")
    protected String           backupExtension;

    @Option(alias = "i", doc = "prefer to do case-insensitive comparation")
    protected boolean          ignoreCase;

    @Option(alias = "P", doc = "protected mode, don't modify any files")
    protected boolean          dryRun;

    @Option(alias = "f", doc = "force overwrite existing files, this includes --error-continue")
    protected boolean          force;

    @Option(doc = "always continue when error occurred")
    protected boolean          errorContinue;

    @Option(alias = "r", vnam = "[DEPTH]", optional = "65536", doc = "max depth of directories recurse into")
    protected int              recursive;
    @Option(alias = "rl", doc = "process children files before the parent directory")
    protected boolean          rootLast;

    @Option(alias = "Im", vnam = "FILEMASK", doc = "include specified type of files, default non-hidden files")
    protected FileMask         inclusiveMask  = new FileMask("f/fH");
    @Option(alias = "IM", vnam = "FILEMASK", doc = "exclude specified type of files")
    protected FileMask         exclusiveMask;

    @Option(alias = "If", vnam = "WILDCARDS", parser = WildcardsParser.class, doc = "include these filenames")
    protected Pattern          fileInclusivePattern;
    @Option(alias = "IF", vnam = "WILDCARDS", parser = WildcardsParser.class, doc = "exclude these filenames")
    protected Pattern          fileExclusivePattern;

    @Option(alias = "Ip", vnam = "REGEXP", doc = "include these pathnames")
    protected Pattern          pathInclusivePattern;
    @Option(alias = "IP", vnam = "REGEXP", doc = "exclude these pathnames")
    protected Pattern          pathExclusivePattern;

    @Option(alias = "Id", doc = "also apply filters on directories")
    protected boolean          filterDirectories;

    @Option(alias = "Ic", vnam = "CLASS(FileFilter)", parser = ClassInstanceParser.class, doc = "using custom file filter, this will override other --I* options")
    protected FileFilter       fileFilter;

    @Option(vnam = "CLASS(Comparator)", parser = ClassInstanceParser.class, doc = "sort files in each directory")
    protected Comparator<File> sortComparator;

    @Option(alias = ".X", vnam = "DIFF-ALG", optional = "gnudiff", doc = "show diff between original and modified files, default using gnudiff")
    protected DiffComparator   diffAlgorithm;

    @Option(alias = "Xf", vnam = "FORMAT", doc = "Simdiff, ED, Context, Unified, Normal")
    protected DiffFormat       diffFormat     = DiffFormats.Simdiff;

    @Option(alias = "Xo", vnam = "FILE", doc = "write diff output to specified file")
    protected CharOut          diffOutput     = CharOuts.stdout;

    @Option(alias = "X3", doc = "diff between src/dst/out, when output to different file")
    protected boolean          diff3          = false;

    @Option(alias = "X2", doc = "diff between src/out rather then src/dst, only used when output directory is different")
    protected boolean          diffWithDest   = false;

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

    protected boolean diff(File a, File b) throws IOException {
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

    private File   currentStartFile;

    private File getOutputFile(File in, File start) {
        if (outputDirectory == null)
            return in;
        List<String> tails = new ArrayList<String>();
        for (File look = in;; look = look.getParentFile()) {
            if (look == null)
                throw new UnexpectedException("file " + in
                        + " not in the start dir " + start);
            if (look.equals(start))
                break;
            tails.add(look.getName());
        }
        File outd = outputDirectory;
        for (int i = tails.size() - 1; i >= 1; i--)
            outd = new File(outd, tails.get(i));
        if (outd.isFile())
            throw new Error("invalid output directory: " + outd);
        String base = in.getName();
        return new File(outd, base);
    }

    @OverrideOption(group = "batchProcess")
    protected ProcessResult process(File file) throws Throwable {
        File editTmp = _getEditTmp(file);
        try {
            return _process(file, editTmp);
        } finally {
            if (editTmp != null)
                editTmp.delete();
        }
    }

    private ProcessResult _process(File file, File editTmp) throws Throwable {
        ProcessResult result = process(file, editTmp);
        if (result == null) // ignored
            return null;
        File dst = getOutputFile(file, currentStartFile);
        if (result.dest instanceof String) {
            String relpath = (String) result.dest;
            result.dest = new File(dst, relpath);
        }
        if (result.dest != null)
            dst = (File) result.dest;

        boolean diffPrinted = false;
        if (result.operation == ProcessResult.SAVE) {
            assert result.changed == null;
            if (editTmp == null)
                throw new CLIException("can't save: not a batch editor");
            if (!diff3 && !diffWithDest) {
                result.changed = diff(file, editTmp);
                diffPrinted = true;
            } else
                result.changed = Files.equals(file, editTmp);
            if (result.changed)
                result.operation = ProcessResult.SAVE_DIFF;
            else
                result.operation = ProcessResult.SAVE_SAME;
        }

        switch (result.operation) {
        case ProcessResult.DELETE:
            if (shell.delete(dst))
                result.setDone();
            return result;

        case ProcessResult.RENAME:
            if (shell.renameTo(file, dst))
                result.setDone();
            return result;

        case ProcessResult.MOVE:
            if (shell.move(file, dst, force))
                result.setDone();
            return result;

        case ProcessResult.COPY:
            if (shell.copy(file, dst))
                result.setDone();
            return result;

        case ProcessResult.SAVE_DIFF:
            assert result.changed;
        case ProcessResult.SAVE_SAME:
            boolean saveLocal = dst.equals(file);
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
                shell.mkdirs(dstdir);
            if (!diffPrinted) {
                if (diff3) {
                    diff(file, editTmp);
                    diff(dst, editTmp);
                } else if (diffWithDest) {
                    diff(dst, editTmp);
                } else {
                    diff(file, editTmp);
                }
            }
            shell.copy(editTmp, dst);

            result.setDone();
            return result;

        case ProcessResult.SAVE:
        default:
            throw new UnexpectedException("invalid operation: "
                    + result.operation);
        }
    }

    /**
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the out file
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult process(File in, File out) throws Throwable {
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(in);
            if (out != null)
                outs = new FileOutputStream(out);
            else
                outs = System.out;
            return process(ins, outs);
        } finally {
            if (ins != null)
                ins.close();
            if (outs != null)
                outs.close();
        }
    }

    /**
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult process(InputStream in, OutputStream out)
            throws Throwable {
        Iterable<String> inIter = Files.readByLine2(inputEncoding.name(), in);
        CharOut cout = CharOuts.stdout;
        if (out != null)
            cout = CharOuts.get(out, outputEncoding.name());
        return process(inIter, cout);
    }

    /**
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult process(Iterable<String> lines, CharOut out)
            throws Throwable {
        throw new NotImplementedException();
    }

    ProcessResultStat stat = new ProcessResultStat();

    ProtectedShell    shell;

    @Override
    protected void _boot() throws Throwable {
        super._boot();
        shell = new ProtectedShell(!dryRun, L);
    }

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
    protected void _main(final File argfile) throws Throwable {
        L.i.P("[start]", argfile);
        currentStartFile = argfile;
        FileFilter walkfilter = fileFilter;
        FsWalk walker = new FsWalk(argfile, walkfilter, filterDirectories,
                recursive, rootLast, sortComparator) {
            @Override
            public void process(File file) throws IOException {
                Throwable err = null;
                String[] tags = {};
                try {
                    L.i.sig("[proc] ", file);
                    ProcessResult result = BatchProcessCLI.this.process(file);
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
                        L.u.P("[", result.getOperationName(), "] ",
                                getOutputFile(file, currentStartFile));
                } catch (IOException e) {
                    err = e;
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
        };
        walker.walk();
    }

    @Override
    protected final void _main(File file, InputStream in) throws Throwable {
        throw new UnexpectedException();
    }

}
