package net.bodz.bas.cli.skel;

import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.util.IFileFilter;

/**
 * Why put getter/setter here?
 * 
 * It's just because there are too many properties/methods in the BasicCLI+ types.
 */
public class CLIAccessor {

    // BatchCLI

    public static Charset getInputEncoding(BatchCLI instance) {
        return instance.inputEncoding;
    }

    public static void setInputEncoding(BatchCLI instance, Charset inputEncoding) {
        instance.inputEncoding = inputEncoding;
    }

    public static boolean isIgnoreCase(BatchCLI instance) {
        return instance.ignoreCase;
    }

    public static void setIgnoreCase(BatchCLI instance, boolean ignoreCase) {
        instance.ignoreCase = ignoreCase;
    }

    public static boolean isErrorContinue(BatchCLI instance) {
        return instance.errorContinue;
    }

    public static void setErrorContinue(BatchCLI instance, boolean errorContinue) {
        instance.errorContinue = errorContinue;
    }

    public static int getRecursive(BatchCLI instance) {
        return instance.recursive;
    }

    public static void setRecursive(BatchCLI instance, int recursive) {
        instance.recursive = recursive;
    }

    public static boolean isRootLast(BatchCLI instance) {
        return instance.rootLast;
    }

    public static void setRootLast(BatchCLI instance, boolean rootLast) {
        instance.rootLast = rootLast;
    }

    public static FileMaskedModifiers getInclusiveMask(BatchCLI instance) {
        return instance.inclusiveMask;
    }

    public static void setInclusiveMask(BatchCLI instance, FileMaskedModifiers inclusiveMask) {
        instance.inclusiveMask = inclusiveMask;
    }

    public static FileMaskedModifiers getExclusiveMask(BatchCLI instance) {
        return instance.exclusiveMask;
    }

    public static void setExclusiveMask(BatchCLI instance, FileMaskedModifiers exclusiveMask) {
        instance.exclusiveMask = exclusiveMask;
    }

    public static GlobPattern getFileInclusivePattern(BatchCLI instance) {
        return instance.fileInclusivePattern;
    }

    public static void setFileInclusivePattern(BatchCLI instance, GlobPattern fileInclusivePattern) {
        instance.fileInclusivePattern = fileInclusivePattern;
    }

    public static GlobPattern getFileExclusivePattern(BatchCLI instance) {
        return instance.fileExclusivePattern;
    }

    public static void setFileExclusivePattern(BatchCLI instance, GlobPattern fileExclusivePattern) {
        instance.fileExclusivePattern = fileExclusivePattern;
    }

    public static Pattern getPathInclusivePattern(BatchCLI instance) {
        return instance.pathInclusivePattern;
    }

    public static void setPathInclusivePattern(BatchCLI instance, Pattern pathInclusivePattern) {
        instance.pathInclusivePattern = pathInclusivePattern;
    }

    public static Pattern getPathExclusivePattern(BatchCLI instance) {
        return instance.pathExclusivePattern;
    }

    public static void setPathExclusivePattern(BatchCLI instance, Pattern pathExclusivePattern) {
        instance.pathExclusivePattern = pathExclusivePattern;
    }

    public static boolean isFilterDirectories(BatchCLI instance) {
        return instance.prune;
    }

    public static void setFilterDirectories(BatchCLI instance, boolean filterDirectories) {
        instance.prune = filterDirectories;
    }

    public static IFileFilter getFileFilter(BatchCLI instance) {
        return instance.fileFilter;
    }

    public static void setFileFilter(BatchCLI instance, IFileFilter fileFilter) {
        instance.fileFilter = fileFilter;
    }

    public static Comparator<IFile> getSortComparator(BatchCLI instance) {
        return instance.sortComparator;
    }

    public static void setSortComparator(BatchCLI instance, Comparator<IFile> sortComparator) {
        instance.sortComparator = sortComparator;
    }

    // BatchEditCLI

    public static Charset getOutputEncoding(BatchEditCLI instance) {
        return instance.outputEncoding;
    }

    public static void setOutputEncoding(BatchEditCLI instance, Charset outputEncoding) {
        instance.outputEncoding = outputEncoding;
    }

    public static IFile getOutputDirectory(BatchEditCLI instance) {
        return instance.outputDirectory;
    }

    public static void setOutputDirectory(BatchEditCLI instance, IFile outputDirectory) {
        instance.outputDirectory = outputDirectory;
    }

    public static String getBackupExtension(BatchEditCLI instance) {
        return instance.backupExtension;
    }

    public static void setBackupExtension(BatchEditCLI instance, String backupExtension) {
        instance.backupExtension = backupExtension;
    }

    public static boolean isDryRun(BatchEditCLI instance) {
        return instance.dryRun;
    }

    public static void setDryRun(BatchEditCLI instance, boolean dryRun) {
        instance.dryRun = dryRun;
    }

    public static boolean isForce(BatchEditCLI instance) {
        return instance.force;
    }

    public static void setForce(BatchEditCLI instance, boolean force) {
        instance.force = force;
    }

    public static DiffComparator getDiffAlgorithm(BatchEditCLI instance) {
        return instance.diffAlgorithm;
    }

    public static void setDiffAlgorithm(BatchEditCLI instance, DiffComparator diffAlgorithm) {
        instance.diffAlgorithm = diffAlgorithm;
    }

    public static DiffFormat getDiffFormat(BatchEditCLI instance) {
        return instance.diffFormat;
    }

    public static void setDiffFormat(BatchEditCLI instance, DiffFormat diffFormat) {
        instance.diffFormat = diffFormat;
    }

    public static IPrintOut getDiffOutput(BatchEditCLI instance) {
        return instance.diffOutput;
    }

    public static void setDiffOutput(BatchEditCLI instance, IPrintOut diffOutput) {
        instance.diffOutput = diffOutput;
    }

    public static boolean isDiff3(BatchEditCLI instance) {
        return instance.diff3;
    }

    public static void setDiff3(BatchEditCLI instance, boolean diff3) {
        instance.diff3 = diff3;
    }

    public static boolean isDiffWithDest(BatchEditCLI instance) {
        return instance.diffWithDest;
    }

    public static void setDiffWithDest(BatchEditCLI instance, boolean diffWithDest) {
        instance.diffWithDest = diffWithDest;
    }

}
