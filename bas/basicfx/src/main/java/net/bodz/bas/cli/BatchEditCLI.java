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
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.types.util.Strings;

@OptionGroup(value = "batch process", rank = -3)
public class BatchEditCLI extends BatchCLI {

    @Option(alias = ".E", vnam = "ENCODING", doc = "default encoding of output files")
    Charset        outputEncoding = Charset.defaultCharset();

    @Option(alias = "O", vnam = "DIR", doc = "put output files under this directory")
    File           outputDirectory;

    @Option(vnam = ".EXT", optional = ".bak", doc = "backup modified files with given extension")
    String         backupExtension;

    @Option(alias = "P", doc = "protected mode, don't modify any files")
    boolean        dryRun;

    @Option(alias = "f", doc = "force overwrite existing files, this includes --error-continue")
    boolean        force;

    @Option(alias = ".X", vnam = "DIFF-ALG", optional = "gnudiff", doc = "show diff between original and modified files, default using gnudiff")
    DiffComparator diffAlgorithm;

    @Option(alias = "Xf", vnam = "FORMAT", doc = "Simdiff, ED, Context, Unified, Normal")
    DiffFormat     diffFormat     = DiffFormats.Simdiff;

    @Option(alias = "Xo", vnam = "FILE", doc = "write diff output to specified file")
    CharOut        diffOutput     = CharOuts.stdout;

    @Option(alias = "X3", doc = "diff between src/dst/out, when output to different file")
    boolean        diff3          = false;

    @Option(alias = "X2", doc = "diff between src/out rather then src/dst, only used when output directory is different")
    boolean        diffWithDest   = false;

    public class Parameters extends BatchCLI.Parameters {

        public Charset getOutputEncoding() {
            return outputEncoding;
        }

        public void setOutputEncoding(Charset outputEncoding) {
            BatchEditCLI.this.outputEncoding = outputEncoding;
        }

        public File getOutputDirectory() {
            return outputDirectory;
        }

        public void setOutputDirectory(File outputDirectory) {
            BatchEditCLI.this.outputDirectory = outputDirectory;
        }

        public String getBackupExtension() {
            return backupExtension;
        }

        public void setBackupExtension(String backupExtension) {
            BatchEditCLI.this.backupExtension = backupExtension;
        }

        public boolean isDryRun() {
            return dryRun;
        }

        public void setDryRun(boolean dryRun) {
            BatchEditCLI.this.dryRun = dryRun;
        }

        public boolean isForce() {
            return force;
        }

        public void setForce(boolean force) {
            BatchEditCLI.this.force = force;
        }

        public DiffComparator getDiffAlgorithm() {
            return diffAlgorithm;
        }

        public void setDiffAlgorithm(DiffComparator diffAlgorithm) {
            BatchEditCLI.this.diffAlgorithm = diffAlgorithm;
        }

        public DiffFormat getDiffFormat() {
            return diffFormat;
        }

        public void setDiffFormat(DiffFormat diffFormat) {
            BatchEditCLI.this.diffFormat = diffFormat;
        }

        public CharOut getDiffOutput() {
            return diffOutput;
        }

        public void setDiffOutput(CharOut diffOutput) {
            BatchEditCLI.this.diffOutput = diffOutput;
        }

        public boolean isDiff3() {
            return diff3;
        }

        public void setDiff3(boolean diff3) {
            BatchEditCLI.this.diff3 = diff3;
        }

        public boolean isDiffWithDest() {
            return diffWithDest;
        }

        public void setDiffWithDest(boolean diffWithDest) {
            BatchEditCLI.this.diffWithDest = diffWithDest;
        }

    }

    @Override
    protected Parameters parameters() {
        if (parameters == null)
            parameters = new Parameters();
        return (Parameters) parameters;
    }

    protected BatchEditCLI() {
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
            L.info(AppNLS.getString("BatchEditCLI.s.new"), a); //$NON-NLS-1$
            return true;
        } else if (!a.exists() && b.exists()) {
            L.info(AppNLS.getString("BatchEditCLI.s.miss"), a); //$NON-NLS-1$
            return true;
        }
        if (diffAlgorithm == null) {
            if (Files.equals(a, b))
                return false;
            L.info(AppNLS.getString("BatchEditCLI.s.edit"), a); //$NON-NLS-1$
            return true;
        }
        List<String> al = Files.readLines(a, inputEncoding.name());
        List<String> bl = Files.readLines(b, outputEncoding.name());
        List<DiffInfo> diffs = diffAlgorithm.diffCompare(al, bl);
        if (diffs.size() == 0)
            return false;
        L.info(AppNLS.getString("BatchEditCLI.s.edit"), a); //$NON-NLS-1$
        diffFormat.format(al, bl, diffs, diffOutput);
        return true;
    }

    private File   tmpDir    = Files.getTmpDir();
    private String tmpPrefix = getClass().getSimpleName();

    private File _getOutputFile(String relative, File in) {
        if (outputDirectory == null)
            return in;
        File out = Files.getAbsoluteFile(outputDirectory, relative);
        File outd = out.getParentFile();
        if (outd.isFile())
            throw new Error(AppNLS.getString("BatchEditCLI.invalidOutDir") + outd); //$NON-NLS-1$
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
    protected void _processFile(File file) {
        Throwable err = null;
        try {
            L.tinfo(AppNLS.getString("BatchEditCLI.s.proc"), file); //$NON-NLS-1$
            EditResult result = BatchEditCLI.this.doEdit(file);
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
                stat.add(EditResult.err(err));
                L.error(AppNLS.getString("BatchEditCLI.s.fail"), file, ": ", err); //$NON-NLS-1$ //$NON-NLS-2$
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
    @OverrideOption(group = "batchEdit")
    protected EditResult doEdit(File file) throws Exception {
        File editTmp = _getEditTmp(file);
        try {
            EditResult result = doEditWithTemp(file, editTmp);
            addResult(file, getOutputFile(file), editTmp, result);
            return null;
        } finally {
            if (editTmp != null)
                editTmp.delete();
        }
    }

    /**
     * @throws NotImplementedException
     */
    @Override
    @Deprecated
    protected void doFile(File file, InputStream in) throws Exception {
        throw new NotImplementedException();
    }

    /**
     * @param file
     *            canonical file
     * @param editTmp
     *            canonical file
     */
    @OverrideOption(group = "batchEdit")
    protected EditResult doEditWithTemp(File file, File editTmp) throws Exception {
        EditResult result = doEdit(file, editTmp);
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
    @OverrideOption(group = "batchEdit")
    protected EditResult doEdit(File in, File out) throws Exception {
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(in);
            if (out != null)
                outs = new FileOutputStream(out);
            else
                outs = System.out;
            return doEditByIO(ins, outs);
        } finally {
            if (ins != null)
                ins.close();
            if (outs != null)
                outs.close();
        }
    }

    /**
     * Implemented as: read lines and pass to {@link #doEditByLine(Iterable, CharOut)}.
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchEdit")
    protected EditResult doEditByIO(InputStream in, OutputStream out) throws Exception {
        Iterable<String> lines = Files.readByLine2(inputEncoding.name(), in);
        CharOut cout = CharOuts.stdout;
        if (out != null)
            cout = CharOuts.get(out, outputEncoding.name());
        return doEditByLine(lines, cout);
    }

    /**
     * Iterated line includes the line term chars.
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchEdit")
    protected EditResult doEditByLine(Iterable<String> lines, CharOut out) throws Exception {
        throw new NotImplementedException();
    }

    protected ProtectedShell _getShell() {
        if (dryRun)
            // in common case, dry mode is provided for verbose purpose
            return new ProtectedShell(false, L.info());
        else
            // when in real mode, the verbose info goes into debug out
            return new ProtectedShell(true, L.debug());
    }

    protected final ProcessResultStat stat = new ProcessResultStat();

    protected void addResult(EditResult result) throws IOException {
        addResult(null, null, null, result);
    }

    protected void addResult(File src, EditResult result) throws IOException, CLIException {
        addResult(src, getOutputFile(src), result);
    }

    protected void addResult(File src, File dst, EditResult result) throws IOException {
        addResult(src, dst, null, result);
    }

    protected void addResult(File src, File dst, File edit, EditResult result) throws IOException {
        if (result == null)
            L.detail(AppNLS.getString("BatchEditCLI.s.skip"), src); //$NON-NLS-1$
        else {
            if (src != null)
                applyResult(src, dst, edit, result);
            stat.add(result);
            if (result.done)
                L.info("[", result.getOperationName(), "] ", dst); //$NON-NLS-1$ //$NON-NLS-2$
            if (result.error) {
                String tags;
                if (result.tags.length == 0)
                    tags = ""; //$NON-NLS-1$
                else
                    tags = "|" + Strings.join("|", result.tags); //$NON-NLS-1$ //$NON-NLS-2$
                if (result.cause != null)
                    L
                            .error(
                                    AppNLS.getString("BatchEditCLI.s.fail_"), tags, "] ", src, ": ", result.cause); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                else
                    L.error(AppNLS.getString("BatchEditCLI.s.fail_"), tags, "] ", src); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

    private void applyResult(File src, File dst, File edit, EditResult result) throws IOException {
        assert result != null;

        boolean diffPrinted = false;
        if (result.operation == EditResult.SAVE) {
            assert result.changed == null;
            if (edit == null)
                throw new IllegalUsageError(AppNLS
                        .getString("BatchEditCLI.cantSave.notBatchEditor")); //$NON-NLS-1$
            if (!diff3 && !diffWithDest) {
                result.changed = diff(src, edit);
                diffPrinted = true;
            } else
                result.changed = Files.equals(src, edit);
            if (result.changed)
                result.operation = EditResult.SAVE_DIFF;
            else
                result.operation = EditResult.SAVE_SAME;
        }

        switch (result.operation) {
        case EditResult.NONE:
            return;

        case EditResult.DELETE:
            if (psh.delete(dst))
                result.setDone();
            return;

        case EditResult.RENAME:
            if (psh.renameTo(src, dst))
                result.setDone();
            return;

        case EditResult.MOVE:
            if (psh.move(src, dst, force))
                result.setDone();
            return;

        case EditResult.COPY:
            if (psh.copy(src, dst))
                result.setDone();
            return;

        case EditResult.SAVE_DIFF:
            assert result.changed;
        case EditResult.SAVE_SAME:
            boolean saveLocal = dst.equals(src);
            if (!result.changed && saveLocal)
                return;
            boolean canOverwrite = saveLocal || force;
            if (dst.exists() && !canOverwrite)
                // user interaction...
                throw new IllegalStateException(String.format(AppNLS
                        .getString("BatchEditCLI.fileExisted_s"), dst)); //$NON-NLS-1$

            File dstdir = dst.getParentFile();
            if (dstdir != null)
                psh.mkdirs(dstdir);
            if (!diffPrinted)
                if (diff3) {
                    diff(src, edit);
                    diff(dst, edit);
                } else if (diffWithDest)
                    diff(dst, edit);
                else
                    diff(src, edit);
            psh.copy(edit, dst);

            result.setDone();
            return;

        case EditResult.SAVE:
        default:
            throw new UnexpectedException(AppNLS.getString("BatchEditCLI.invalidOperation") //$NON-NLS-1$
                    + result.operation);
        }
    }

    @Override
    protected void doMain(String[] args) throws Exception {
        super.doMain(args);
        if (L.showDetail())
            stat.dumpDetail(L.detail().getCharOut());
        else if (L.showInfo())
            stat.dumpBrief(L.info().getCharOut());
        // System.exit(stat.errors);
    }

    @Override
    @OverrideOption(group = "batchEdit")
    protected void doFileArgument(final File file) throws Exception {
        L.tinfo(AppNLS.getString("BatchEditCLI.s.start"), file); //$NON-NLS-1$
        super.doFileArgument(file);
    }

    public class Methods extends net.bodz.bas.cli.BatchCLI.Methods {

        public EditResult doEdit(File file) throws Exception {
            return BatchEditCLI.this.doEdit(file);
        }

        public EditResult doEdit(File in, File out) throws Exception {
            return BatchEditCLI.this.doEdit(in, out);
        }

        public EditResult doEditByIO(InputStream in, OutputStream out) throws Throwable {
            return BatchEditCLI.this.doEditByIO(in, out);
        }

        public EditResult doEditByLine(Iterable<String> lines, CharOut out) throws Throwable {
            return BatchEditCLI.this.doEditByLine(lines, out);
        }

        public final byte[] doEditToBuffer(Object in) throws IOException {
            InputStream ins = Files.getInputStream(in);
            boolean closeIn = Files.shouldClose(in);
            ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
            try {
                // ProcessResult result =
                doEditByIO(ins, outbuf);
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                if (closeIn)
                    ins.close();
            }
            return outbuf.toByteArray();
        }

    }

    @Override
    public Methods methods() {
        if (methods == null)
            methods = new Methods();
        return (Methods) methods;
    }

}
