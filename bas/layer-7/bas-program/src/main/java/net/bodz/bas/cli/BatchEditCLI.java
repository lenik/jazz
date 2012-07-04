package net.bodz.bas.cli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.InputStreamSource;
import net.bodz.bas.meta.codehint.OverrideOption;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.sio.WriterPrintOut;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;

@OptionGroup(value = "batch process", rank = -3)
public class BatchEditCLI
        extends BatchCLI {

    /**
     * Default encoding of output files
     * 
     * @option -E weak =ENCODING
     */
    Charset outputEncoding = Charset.defaultCharset();

    /**
     * put output files under this directory
     * 
     * @option -O =DIR
     */
    IFile outputDirectory;

    /**
     * backup modified files with given extension
     * 
     * @option =.EXT default=.bak
     */
    String backupExtension;

    /**
     * protected mode, don't modify any files
     * 
     * @option -P
     */
    boolean dryRun;

    /**
     * force overwrite existing files, this includes --error-continue
     * 
     * @option -f
     */
    boolean force;

    /**
     * show diff between original and modified files, default using gnudiff.
     * 
     * @option -X weak =DIFF-ALG default=gnudiff
     */
    DiffComparator diffAlgorithm;

    /**
     * Simdiff, ED, Context, Unified, Normal.
     * 
     * @option -Xf =FORMAT
     */
    DiffFormat diffFormat = DiffFormats.Simdiff;

    /**
     * Write diff output to specified file.
     * 
     * @option -Xo =FILE
     */
    IPrintOut diffOutput = Stdio.cout;

    /**
     * Diff between src/dst/out, when output to different file.
     * 
     * @option -X3
     */
    boolean diff3 = false;

    /**
     * Diff between src/out rather then src/dst, only used when output directory is different.
     * 
     * @option -X2
     */
    boolean diffWithDest = false;

    public class Parameters
            extends BatchCLI.Parameters {

        public Charset getOutputEncoding() {
            return outputEncoding;
        }

        public void setOutputEncoding(Charset outputEncoding) {
            BatchEditCLI.this.outputEncoding = outputEncoding;
        }

        public IFile getOutputDirectory() {
            return outputDirectory;
        }

        public void setOutputDirectory(IFile outputDirectory) {
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

        public IPrintOut getDiffOutput() {
            return diffOutput;
        }

        public void setDiffOutput(IPrintOut diffOutput) {
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
     * Set up {@link #fileFilter} and {@link #psh}.
     */
    @Override
    void _postInit() {
        super._postInit();
    }

    /**
     * @return canonical file
     */
    protected IFile _getEditTmp(IFile file)
            throws IOException {
        String dotExt = file.getPath().getExtension(true);
        File tmpFile = File.createTempFile(tmpPrefix, dotExt, tmpDir);
        return new JavaioFile(tmpFile);
    }

    private boolean diff(IFile a, IFile b)
            throws IOException {
        assert a != null;
        assert b != null;

        if (a.exists() && !b.exists()) {
            L.info("[new ]", a);
            return true;
        } else if (!a.exists() && b.exists()) {
            L.info("[miss]", a);
            return true;
        }
        if (diffAlgorithm == null) {
            if (FileDiff.equals(a.getInputSource(), b.getInputSource()))
                return false;
            L.info("[edit] ", a);
            return true;
        }
        List<String> al = a.getInputSource(inputEncoding).forRead().listLines();
        List<String> bl = b.getInputSource(outputEncoding).forRead().listLines();
        List<DiffInfo> diffs = diffAlgorithm.diffCompare(al, bl);
        if (diffs.size() == 0)
            return false;
        L.info("[edit] ", a);
        diffFormat.format(al, bl, diffs, diffOutput);
        return true;
    }

    private File tmpDir = TempFile.getTmpDir();
    private String tmpPrefix = getClass().getSimpleName();

    private IFile _getOutputFile(String relative, IFile in)
            throws FileResolveException {
        if (outputDirectory == null)
            return in;
        IFile out = outputDirectory.getChild(relative);
        IFile outdir = out.getParentFile();
        if (!outdir.isTree())
            throw new Error("Invalid output directory: " + outdir);
        return out;
    }

    protected IFile getOutputFile(String relative, IFile defaultStart)
            throws FileResolveException {
        IFile in = defaultStart.getChild(relative);
        return _getOutputFile(relative, in);
    }

    protected IFile getOutputFile(String relative)
            throws FileResolveException {
        return getOutputFile(relative, currentStartFile);
    }

    protected IFile getOutputFile(IFile in)
            throws FileResolveException {
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
    protected void _processFile(IFile file) {
        Throwable err = null;
        try {
            L.info("[proc] ", file);
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
                L.error("[fail] ", file, ": ", err);
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
    protected EditResult doEdit(IFile file)
            throws Exception {
        IFile editTmp = _getEditTmp(file);
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
    protected void doFile(IFile file, InputStream in)
            throws Exception {
        throw new NotImplementedException();
    }

    /**
     * @param file
     *            canonical file
     * @param editTmp
     *            canonical file
     */
    @OverrideOption(group = "batchEdit")
    protected EditResult doEditWithTemp(IFile file, IFile editTmp)
            throws Exception {
        EditResult result = doEdit(file, editTmp);
        if (result == null) // ignored
            return null;
        IFile dst = getOutputFile(file);
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
    protected EditResult doEdit(IFile inFile, IFile outFile)
            throws Exception {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = inFile.getInputSource(inputEncoding).newInputStream();
            if (outFile != null)
                out = outFile.getOutputTarget(outputEncoding).newOutputStream();
            else
                out = System.out;
            return doEditByIO(in, out);
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }
    }

    /**
     * Implemented as: read lines and pass to {@link #doEditByLine(Iterable, CharOut)}.
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchEdit")
    protected EditResult doEditByIO(InputStream in, OutputStream out)
            throws Exception {
        InputStreamSource source = new InputStreamSource(in);
        source.setCharset(inputEncoding);

        Iterable<String> lines = source.forRead().lines(false);

        IPrintOut cout = Stdio.cout;
        if (out != null) {
            OutputStreamWriter writer = new OutputStreamWriter(out, outputEncoding.name());
            cout = new WriterPrintOut(writer);
        }

        return doEditByLine(lines, cout);
    }

    /**
     * Iterated line includes the line term chars.
     * 
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the output
     */
    @OverrideOption(group = "batchEdit")
    protected EditResult doEditByLine(Iterable<String> lines, IPrintOut out)
            throws Exception {
        throw new NotImplementedException();
    }

    protected final ProcessResultStat stat = new ProcessResultStat();

    protected void addResult(EditResult result)
            throws IOException {
        addResult(null, null, null, result);
    }

    protected void addResult(IFile src, EditResult result)
            throws IOException, CLIException {
        addResult(src, getOutputFile(src), result);
    }

    protected void addResult(IFile src, IFile dst, EditResult result)
            throws IOException {
        addResult(src, dst, null, result);
    }

    protected void addResult(IFile src, IFile dst, IFile edit, EditResult result)
            throws IOException {
        IOException ee = null;
        L._debugFormat(1, "FF", "x");
        L._debug(3, "F", "X");
        if (result == null)
            L._info(1, "[skip] ", src);
        else {
            if (src != null)
                applyResult(src, dst, edit, result);
            stat.add(result);
            if (result.done)
                L.info("[", result.getOperationName(), "] ", dst);
            if (result.error) {
                String tags;
                if (result.tags.length == 0)
                    tags = "";
                else
                    tags = "|" + StringArray.join("|", result.tags);
                if (result.cause != null)
                    L.error("[fail", tags, "] ", src, ": ", result.cause);
                else
                    L.error("[fail", tags, "] ", src);
            }
        }
    }

    private void applyResult(IFile src, IFile dst, IFile edit, EditResult result)
            throws IOException {
        assert result != null;

        boolean diffPrinted = false;
        if (result.operation == EditResult.SAVE) {
            assert result.changed == null;
            if (edit == null)
                throw new IllegalUsageError("can\'t save: not a batch editor");
            if (!diff3 && !diffWithDest) {
                result.changed = diff(src, edit);
                diffPrinted = true;
            } else
                result.changed = FileDiff.equals(src, edit);
            if (result.changed)
                result.operation = EditResult.SAVE_DIFF;
            else
                result.operation = EditResult.SAVE_SAME;
        }

        switch (result.operation) {
        case EditResult.NONE:
            return;

        case EditResult.DELETE:
            if (dst.delete())
                result.setDone();
            return;

        case EditResult.RENAME:
            if (src.renameTo(dst))
                result.setDone();
            return;

        case EditResult.MOVE:
            if (src.move(dst, force))
                result.setDone();
            return;

        case EditResult.COPY:
            if (src.copyTo(dst))
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
                throw new IllegalStateException(String.format("File %s is already existed. ", dst));

            IFile dstdir = dst.getParentFile();
            if (dstdir != null)
                dstdir.createTree();
            if (!diffPrinted)
                if (diff3) {
                    diff(src, edit);
                    diff(dst, edit);
                } else if (diffWithDest)
                    diff(dst, edit);
                else
                    diff(src, edit);
            edit.copyTo(dst);

            result.setDone();
            return;

        case EditResult.SAVE:
        default:
            throw new UnexpectedException("invalid operation: " + result.operation);
        }
    }

    @Override
    protected void doMain(String[] args)
            throws Exception {
        super.doMain(args);
        if (L.isInfoEnabled(1))
            stat.dumpDetail(L.getInfoSink(1));
        else if (L.isInfoEnabled())
            stat.dumpBrief(L.getInfoSink());
        // System.exit(stat.errors);
    }

    @Override
    @OverrideOption(group = "batchEdit")
    protected void doFileArgument(final IFile file)
            throws Exception {
        L.status("[start] ", file);
        super.doFileArgument(file);
    }

    public class Methods
            extends net.bodz.bas.cli.BatchCLI.Methods {

        public EditResult doEdit(IFile file)
                throws Exception {
            return BatchEditCLI.this.doEdit(file);
        }

        public EditResult doEdit(IFile in, IFile out)
                throws Exception {
            return BatchEditCLI.this.doEdit(in, out);
        }

        public EditResult doEditByIO(InputStream in, OutputStream out)
                throws Throwable {
            return BatchEditCLI.this.doEditByIO(in, out);
        }

        public EditResult doEditByLine(Iterable<String> lines, IPrintOut out)
                throws Throwable {
            return BatchEditCLI.this.doEditByLine(lines, out);
        }

        public final byte[] doEditToBuffer(IStreamInputSource source)
                throws IOException {
            InputStream in = source.newInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                // ProcessResult result =
                doEditByIO(in, out);
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                in.close();
            }
            return out.toByteArray();
        }

    }

    @Override
    public Methods methods() {
        if (methods == null)
            methods = new Methods();
        return (Methods) methods;
    }

}
