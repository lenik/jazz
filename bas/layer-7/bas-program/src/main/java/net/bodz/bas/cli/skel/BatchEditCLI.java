package net.bodz.bas.cli.skel;

import java.nio.charset.Charset;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.IDiffComparator;
import net.bodz.bas.text.diff.IDiffFormat;
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
     * Put output files under this directory
     * 
     * @option -O =DIR
     */
    IFile outputDirectory;

    /**
     * Recreate directory structure in the output.
     * 
     * @option
     */
    boolean keepDirStruct;

    /**
     * Backup modified files with given extension
     * 
     * @option =.EXT default=.bak
     */
    String backupExtension;

    /**
     * Preview mode, don't modify any files
     * 
     * @option -P
     */
    boolean dryRun;

    /**
     * Force overwrite existing files, this includes --error-continue
     * 
     * @option -f weak
     */
    boolean force;

    /**
     * Show diff between original and modified files, default using gnudiff.
     * 
     * @option --diff =DIFF-ALG default=gnudiff
     */
    IDiffComparator diffAlgorithm;

    /**
     * Simdiff, ED, Context, Unified, Normal.
     * 
     * @option =FORMAT
     */
    IDiffFormat diffFormat = IDiffFormat.SIMPLE;

    /**
     * Write diff output to specified file.
     * 
     * @option =FILE
     */
    IPrintOut diffOutput = Stdio.cout;

    /**
     * Diff between src/tmp/dst.
     * 
     * @option
     */
    boolean diff3 = false;

    /**
     * Diff between tmp/dst rather then src/tmp.
     * 
     * @option
     */
    boolean diffWithDest = false;

    @Override
    protected FileHandler beginFile(IFile file) {
        FileHandler handler = super.beginFile(file);
        String pathSpec = handler.getPathSpec();
        IFile targetFile = getTargetFile(file, pathSpec);
        handler.setTargetFile(targetFile);
        return handler;
    }

    protected IFile getTargetFile(IFile sourceFile, String pathSpec) {
        IFile outputFile;
        if (outputDirectory == null) {
            return sourceFile;
        } else {
            if (keepDirStruct) {
                outputFile = outputDirectory.resolve(pathSpec);
            } else {
                String baseName = StringPart.afterLast(pathSpec, "/", pathSpec);
                outputFile = outputDirectory.resolve(baseName);
            }
        }
        return outputFile;
    }

}
