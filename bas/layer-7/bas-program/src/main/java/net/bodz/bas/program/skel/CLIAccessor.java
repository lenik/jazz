package net.bodz.bas.program.skel;

import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.text.diff.IDiffComparator;
import net.bodz.bas.text.diff.IDiffFormat;
import net.bodz.bas.vfs.FileMaskedModifiers;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;

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

    public static FileMaskedModifiers getIncludeMask(BatchCLI instance) {
        return instance.includeMask;
    }

    public static void setIncludeMask(BatchCLI instance, FileMaskedModifiers includeMask) {
        instance.includeMask = includeMask;
    }

    public static FileMaskedModifiers getExcludeMask(BatchCLI instance) {
        return instance.excludeMask;
    }

    public static void setExcludeMask(BatchCLI instance, FileMaskedModifiers excludeMask) {
        instance.excludeMask = excludeMask;
    }

    public static GlobPattern getIncludeName(BatchCLI instance) {
        return instance.includeName;
    }

    public static void setIncludeName(BatchCLI instance, GlobPattern includeName) {
        instance.includeName = includeName;
    }

    public static GlobPattern getExcludeName(BatchCLI instance) {
        return instance.excludeName;
    }

    public static void setExcludeName(BatchCLI instance, GlobPattern excludeName) {
        instance.excludeName = excludeName;
    }

    public static Pattern getIncludePath(BatchCLI instance) {
        return instance.includePath;
    }

    public static void setIncludePath(BatchCLI instance, Pattern includePath) {
        instance.includePath = includePath;
    }

    public static Pattern getExcludePath(BatchCLI instance) {
        return instance.excludePath;
    }

    public static void setExcludePath(BatchCLI instance, Pattern excludePath) {
        instance.excludePath = excludePath;
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

    public static IDiffComparator getDiffAlgorithm(BatchEditCLI instance) {
        return instance.diffAlgorithm;
    }

    public static void setDiffAlgorithm(BatchEditCLI instance, IDiffComparator diffAlgorithm) {
        instance.diffAlgorithm = diffAlgorithm;
    }

    public static IDiffFormat getDiffFormat(BatchEditCLI instance) {
        return instance.diffFormat;
    }

    public static void setDiffFormat(BatchEditCLI instance, IDiffFormat diffFormat) {
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
