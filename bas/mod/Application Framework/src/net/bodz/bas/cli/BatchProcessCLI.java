package net.bodz.bas.cli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.cli.util.ProtectedShell;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.types.util.Strings;

@OptionGroup(value = "batch process", rank = -3)
public class BatchProcessCLI extends BatchCLI {
    @Option(alias = ".E", vnam = "ENCODING", doc = "default encoding of output files")
    protected Charset outputEncoding = Charset.defaultCharset();

    @Option(alias = "O", vnam = "DIR", doc = "put output files under this directory")
    protected File    outputDirectory;

    @Option(vnam = ".EXT", optional = ".bak", doc = "backup modified files with given extension")
    protected String  backupExtension;

    @Option(alias = "P", doc = "protected mode, don't modify any files")
    protected boolean dryRun;

    @Option(alias = "f", doc = "force overwrite existing files, this includes --error-continue")
    protected boolean force;

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
    }

    /**
     * psh is invalid until {@link #_postInit()} (before {@link #_boot()}).
     */
    protected ProtectedShell psh = null;

    /**
     * Set up {@link #fileFilter} and {@link #psh}.
     */
    @Override
    void _postInit() {
        super._postInit();
        psh = _getShell();
    }

    /**
     * @return canonical file
     */
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

    /**
     * display progress info and calc stat
     * 
     * @param file
     *            canonical file
     */
    @Override
    protected void _doFile(File file) {
        Throwable err = null;
        try {
            L.i.sig("[proc] ", file);
            ProcessResult result = BatchProcessCLI.this.doFile(file);
            addResult(file, result);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (IOException e) {
            err = e;
        } catch (Throwable e) {
            err = e;
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            // err before addResult() or raised inside addResult()
            if (err != null) {
                stat.add(ProcessResult.err(err));
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
     * 
     * @param file
     *            canonical file
     */
    @OverrideOption(group = "batchProcess")
    protected ProcessResult doFile(File file) throws Throwable {
        File editTmp = _getEditTmp(file);
        try {
            ProcessResult result = doFile(file, editTmp);
            addResult(file, getOutputFile(file), editTmp, result);
            return null;
        } finally {
            if (editTmp != null)
                editTmp.delete();
        }
    }

    /**
     * 
     */
    @Override
    @Deprecated
    protected void doFile(File file, InputStream in) throws Throwable {
        throw new NotImplementedException();
    }

    /**
     * @param file
     *            canonical file
     * @param editTmp
     *            canonical file
     */
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
            dst = Files.canoniOf(result.dest);

        addResult(file, dst, editTmp, result);
        return null;
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

    public final byte[] doFileEdit(Object in) throws IOException {
        InputStream ins = Files.getInputStream(in);
        boolean closeIn = Files.shouldClose(in);
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
        try {
            // ProcessResult result =
            doFileEdit(ins, outbuf);
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (closeIn)
                ins.close();
        }
        return outbuf.toByteArray();
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

    protected ProtectedShell _getShell() {
        if (dryRun)
            // in common case, dry mode is provided for verbose purpose
            return new ProtectedShell(false, L.m);
        else
            // when in real mode, the verbose info goes into debug out
            return new ProtectedShell(true, L.x);
    }

    protected final ProcessResultStat stat = new ProcessResultStat();

    protected void addResult(ProcessResult result) throws IOException,
            CLIException {
        addResult(null, null, null, result);
    }

    protected void addResult(File src, ProcessResult result)
            throws IOException, CLIException {
        addResult(src, getOutputFile(src), result);
    }

    protected void addResult(File src, File dst, ProcessResult result)
            throws IOException, CLIException {
        addResult(src, dst, null, result);
    }

    protected void addResult(File src, File dst, File edit, ProcessResult result)
            throws IOException, CLIException {
        if (result == null) {
            L.d.P("[skip] ", src);
        } else {
            if (src != null)
                applyResult(src, dst, edit, result);
            stat.add(result);
            if (result.done)
                L.u.P("[", result.getOperationName(), "] ", dst);
            if (result.error) {
                String tags;
                if (result.tags.length == 0)
                    tags = "";
                else
                    tags = "|" + Strings.join("|", result.tags);
                if (result.cause != null)
                    L.e.P("[fail", tags, "] ", src, ": ", result.cause);
                else
                    L.e.P("[fail", tags, "] ", src);
            }
        }
    }

    private void applyResult(File src, File dst, File edit, ProcessResult result)
            throws IOException, CLIException {
        assert result != null;

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
        case ProcessResult.NONE:
            return;

        case ProcessResult.DELETE:
            if (psh.delete(dst))
                result.setDone();
            return;

        case ProcessResult.RENAME:
            if (psh.renameTo(src, dst))
                result.setDone();
            return;

        case ProcessResult.MOVE:
            if (psh.move(src, dst, force))
                result.setDone();
            return;

        case ProcessResult.COPY:
            if (psh.copy(src, dst))
                result.setDone();
            return;

        case ProcessResult.SAVE_DIFF:
            assert result.changed;
        case ProcessResult.SAVE_SAME:
            boolean saveLocal = dst.equals(src);
            if (!result.changed && saveLocal)
                return;
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
            return;

        case ProcessResult.SAVE:
        default:
            throw new UnexpectedException("invalid operation: "
                    + result.operation);
        }
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
    protected void doFileArgument(final File file) throws Throwable {
        L.i.sig("[start] ", file);
        super.doFileArgument(file);
    }

}
