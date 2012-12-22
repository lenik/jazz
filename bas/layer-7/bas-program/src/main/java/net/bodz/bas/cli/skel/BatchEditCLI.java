package net.bodz.bas.cli.skel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardCopyOption;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;

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
     * @option --diff =DIFF-ALG default=gnudiff
     */
    DiffComparator diffAlgorithm;

    /**
     * Simdiff, ED, Context, Unified, Normal.
     * 
     * @option =FORMAT
     */
    DiffFormat diffFormat = DiffFormats.Simdiff;

    /**
     * Write diff output to specified file.
     * 
     * @option =FILE
     */
    IPrintOut diffOutput = Stdio.cout;

    /**
     * Diff between src/dst/out, when output to different file.
     * 
     * @option
     */
    boolean diff3 = false;

    /**
     * Diff between src/out rather then src/dst, only used when output directory is different.
     * 
     * @option
     */
    boolean diffWithDest = false;

    private IFile _getOutputFile(String relative, IFile in)
            throws FileResolveException {
        if (outputDirectory == null)
            return in;

        IFile out = outputDirectory.getChild(relative);
        out.setPreferredCharset(outputEncoding);

        IFile outdir = out.getParentFile();
        if (!outdir.isDirectory())
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

    @Override
    protected FileHandler beginFile(String fileName) {
        FileHandler handler = super.beginFile(fileName);
        handler.setOutDir(outputDirectory);
        return handler;
    }

    protected final FileHandleResultStat stat = new FileHandleResultStat();

    private void applyResult(IFile src, IFile dst, IFile edit, EditResult result)
            throws IOException {

        if (result.operation == EditResult.SAVE) {
            assert result.changed == null;
            if (edit == null)
                throw new IllegalUsageError("can\'t save: not a batch editor");
            if (!diff3 && !diffWithDest) {
                result.changed = diff(src, edit);
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
            if (vfs.move(src, dst))
                result.setDone();
            return;

        case EditResult.MOVE:
            if (vfs.move(src, dst, StandardCopyOption.REPLACE_EXISTING))
                result.setDone();
            return;

        case EditResult.COPY:
            if (vfs.copy(src, dst))
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
                dstdir.mkdirs();
            if (!diffPrinted)
                if (diff3) {
                    diff(src, edit);
                    diff(dst, edit);
                } else if (diffWithDest)
                    diff(dst, edit);
                else
                    diff(src, edit);
            vfs.copy(edit, dst);

            result.setDone();
            return;

        case EditResult.SAVE:
        default:
            throw new UnexpectedException("invalid operation: " + result.operation);
        }
    }

}
