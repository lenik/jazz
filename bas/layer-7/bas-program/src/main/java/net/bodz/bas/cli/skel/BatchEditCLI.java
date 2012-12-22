package net.bodz.bas.cli.skel;

import java.nio.charset.Charset;

import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.vfs.IFile;

/**
 * Batch File Processor
 * <p>
 * Provide framework for batch file processing.
 */
public abstract class BatchEditCLI
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

    @Override
    protected FileHandler beginFile(String fileName) {
        FileHandler handler = super.beginFile(fileName);
        handler.setOutputDir(outputDirectory);
        return handler;
    }

}
