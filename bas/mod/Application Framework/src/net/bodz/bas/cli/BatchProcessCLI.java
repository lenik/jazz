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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import net.bodz.bas.cli.TypeParsers.ClassInstanceParser;
import net.bodz.bas.cli.TypeParsers.WildcardsParser;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.FileMask;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.FsWalk;
import net.bodz.bas.lang.annotations.OverrideOption;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.diff.DiffComparator;
import net.bodz.bas.types.diff.DiffFormat;
import net.bodz.bas.types.diff.DiffFormats;
import net.bodz.bas.types.diff.DiffInfo;

@OptionGroup(value = "batch process", rank = -2)
public class BatchProcessCLI extends BasicCLI {

    @Option(alias = "e", vnam = "ENCODING", doc = "default encoding of input files")
    protected Charset          inputEncoding  = Charset.defaultCharset();
    @Option(alias = "E", vnam = "ENCODING", doc = "default encoding of output files")
    protected Charset          outputEncoding = Charset.defaultCharset();

    @Option(alias = "O", vnam = "DIR", doc = "put output files under this directory")
    protected File             outputDirectory;

    @Option(vnam = ".EXT", optional = ".bak", doc = "backup modified files with given extension")
    protected String           backupExtension;

    @Option(alias = "i", doc = "prefer to do case-insensitive comparation")
    protected boolean          ignoreCase;

    @Option(alias = "P", doc = "protected mode, don't modify any files")
    protected boolean          dryRun;

    @Option(alias = "f", doc = "always answer yes when necessary")
    protected boolean          force;

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

    @Option(alias = "D", vnam = "DIFF-ALG", optional = "gnudiff", doc = "show diff between original and modified files, default using gnudiff")
    protected DiffComparator   diffAlgorithm;

    @Option(alias = "Df", vnam = "FORMAT", doc = "Simdiff, ED, Context, Unified, Normal")
    protected DiffFormat       diffFormat     = DiffFormats.Simdiff;

    @Option(alias = "Do", vnam = "FILE", doc = "write diff output to specified file")
    protected CharOut          diffOutput     = _logout;

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

    protected static final int CLI_BATCHEDIT = 2;

    @Override
    protected int _cliflags() {
        return super._cliflags() | CLI_BATCHEDIT;
    }

    protected static final int PROCESS_UNCHANGED = 0;
    protected static final int PROCESS_SAVED     = 1;
    protected static final int PROCESS_ERROR     = 2;
    protected static final int PROCESS_IGNORE    = 3;
    protected static final int PROCESS_EDIT      = 4;

    protected static final int PROCESS_INCLUDED  = 10;

    protected boolean diff(File a, File b) throws IOException {
        if (diffAlgorithm == null) {
            // TODO Files.fastCompare...
            Iterator<String> ait = Files.readByLine2(//
                    inputEncoding.name(), a).iterator();
            Iterator<String> bit = Files.readByLine2(//
                    outputEncoding.name(), b).iterator();
            while (ait.hasNext()) {
                if (!bit.hasNext())
                    return true;
                String x = ait.next();
                String y = bit.next();
                if (!x.equals(y))
                    return true;
            }
            return false;
        }
        List<String> al = Files.readLines(a, inputEncoding.name());
        List<String> bl = Files.readLines(b, outputEncoding.name());
        List<DiffInfo> diffs = diffAlgorithm.diffCompare(al, bl);
        if (diffs.size() == 0)
            return false;
        _log1("[edit] ", a);
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
        if (!outd.exists())
            outd.mkdirs();
        if (!outd.isDirectory())
            throw new Error("invalid output directory: " + outd);
        String base = in.getName();
        return new File(outd, base);
    }

    /**
     * @return PROCESS_UNCHANGED: ignored <br>
     *         PROCESS_SAVED: diff and successfully saved <br>
     *         PROCESS_ERROR: diff but failed to save
     */
    @OverrideOption(group = "batchProcess")
    protected int process(File file) throws Throwable {
        boolean isBatchEdit = 0 != (_cliflags() & CLI_BATCHEDIT);
        File tmpOut = null;
        if (isBatchEdit) {
            String ext = Files.getExtension(file, true);
            tmpOut = File.createTempFile(tmpPrefix, ext, tmpDir);
        }
        int status = process(file, tmpOut);
        if (status == PROCESS_IGNORE)
            status = PROCESS_UNCHANGED;
        else if (tmpOut != null) {
            boolean diff = diff(file, tmpOut);
            boolean force = this.force || outputDirectory == null;
            status = diff ? PROCESS_SAVED : PROCESS_UNCHANGED;
            if (diff && !dryRun) {
                File dst = getOutputFile(file, currentStartFile);
                if (dst.exists() && !force)
                    status = PROCESS_ERROR;
                else {
                    try {
                        Files.copy(tmpOut, dst);
                    } catch (IOException e) {
                        status = PROCESS_ERROR;
                    }
                }
            }
            tmpOut.delete();
        }
        return status;
    }

    /**
     * @return PROCESS_IGNORE: file skipped <br>
     *         PROCESS_EDIT: have the result written to the out file
     */
    @OverrideOption(group = "batchProcess")
    protected int process(File in, File out) throws Throwable {
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
    protected int process(InputStream in, OutputStream out) throws Throwable {
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
    protected int process(Iterable<String> lines, CharOut out) throws Throwable {
        throw new NotImplementedException();
    }

    protected int statIgnored;
    protected int statSaved;
    protected int statErrored;

    @Override
    protected void _main(String[] args) throws Throwable {
        super._main(args);
        int total = statIgnored + statSaved + statErrored;
        int diffs = statSaved + statErrored;
        String report = String.format(
                "Total %d/%d files changed, %d/%d files saved.", //
                diffs, total, statSaved, diffs);
        _log1(report);
    }

    @Override
    @OverrideOption(group = "batchProcess")
    protected void _main(final File argfile) throws Throwable {
        _log1("[start]", argfile);
        currentStartFile = argfile;
        FileFilter walkfilter = fileFilter;
        FsWalk walker = new FsWalk(argfile, walkfilter, filterDirectories,
                recursive, rootLast, sortComparator) {
            @Override
            public void process(File file) throws IOException {
                try {
                    _sig1("[proc] ", file);
                    int status = BatchProcessCLI.this.process(file);
                    switch (status) {
                    case PROCESS_IGNORE:
                    case PROCESS_UNCHANGED:
                        statIgnored++;
                        break;
                    case PROCESS_SAVED:
                        statSaved++;
                        _log1("[save] ", getOutputFile(file, currentStartFile));
                        break;
                    case PROCESS_ERROR:
                        statErrored++;
                        _log1("[fail] ", file);
                        break;
                    default:
                        throw new UnexpectedException(
                                "illegal status value returned: " + status);
                    }
                } catch (IOException e) {
                    throw e;
                } catch (Throwable e) {
                    throw new RuntimeException(e.getMessage(), e);
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
