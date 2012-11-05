package net.bodz.bas.cli.skel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.meta.codehint.OverrideOption;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;

/**
 * Batch File Processor
 * <p>
 * Provide framework for batch file processing.
 */
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
            logger.info("[new ]", a);
            return true;
        } else if (!a.exists() && b.exists()) {
            logger.info("[miss]", a);
            return true;
        }
        if (diffAlgorithm == null) {
            if (FileDiff.equals(a.getInputSource(), b.getInputSource()))
                return false;
            logger.info("[edit] ", a);
            return true;
        }
        List<String> al = a.getInputSource(inputEncoding).tooling()._for(StreamReading.class).listLines();
        List<String> bl = b.getInputSource(outputEncoding).tooling()._for(StreamReading.class).listLines();
        List<DiffInfo> diffs = diffAlgorithm.diffCompare(al, bl);
        if (diffs.size() == 0)
            return false;
        logger.info("[edit] ", a);
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
        out.setPreferredCharset(outputEncoding);

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
            logger.info("[proc] ", file);
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
                logger.error("[fail] ", file, ": ", err);
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
        logger._debugf(1, "FF", "x");
        logger._debug(3, "F", "X");
        if (result == null)
            logger._info(1, "[skip] ", src);
        else {
            if (src != null)
                applyResult(src, dst, edit, result);
            stat.add(result);
            if (result.done)
                logger.info("[", result.getOperationName(), "] ", dst);
            if (result.error) {
                String tags;
                if (result.tags.length == 0)
                    tags = "";
                else
                    tags = "|" + StringArray.join("|", result.tags);
                if (result.cause != null)
                    logger.error("[fail", tags, "] ", src, ": ", result.cause);
                else
                    logger.error("[fail", tags, "] ", src);
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
    protected void mainImpl(String[] args)
            throws Exception {
        super.mainImpl(args);
        if (logger.isInfoEnabled(1))
            stat.dumpDetail(logger.getInfoSink(1));
        else if (logger.isInfoEnabled())
            stat.dumpBrief(logger.getInfoSink());
        // System.exit(stat.errors);
    }

    @Override
    @OverrideOption(group = "batchEdit")
    protected void doFileArgument(final IFile file)
            throws Exception {
        logger.status("[start] ", file);
        super.doFileArgument(file);
    }

}
