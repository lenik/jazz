package net.bodz.bas.shell.util;

import java.util.List;

import net.bodz.bas.compare.IListComparator;
import net.bodz.bas.compare.IListCompareResult;
import net.bodz.bas.compare.IListCompareResultFormatter;
import net.bodz.bas.compare.IListPatch;
import net.bodz.bas.compare.java_diff_utils.EdFormat;
import net.bodz.bas.compare.java_diff_utils.JduComparator;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Diff and patch apply.
 */
@ProgramName("jdiff")
public class JDiff
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(JDiff.class);

    /**
     * A test option.
     *
     * @option --test
     */
    boolean testOnly;

    IListComparator<String, String> comparator = new JduComparator<>();
    IListCompareResultFormatter<Object, Object> fmt = new EdFormat();

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (args.length < 2)
            throw new IllegalArgumentException("expect 2 filenames");

        IPrintOut out = Stdio.cout;

        FileResource f0 = new FileResource(args[0]);
        FileResource f1 = new FileResource(args[1]);
        List<String> v0 = f0.read().readLines();
        List<String> v1 = f1.read().readLines();

        IListCompareResult<String, String> result = comparator.compare(v0, v1);
        System.out.println("DIFF:");
        fmt.format(out, result);
        System.out.println();

        IListPatch<String> patch = result.patch();
        List<String> v0patch = patch.apply(v0);
        System.out.println("v0patch := v0+patch: equals v1?  " + v1.equals(v0patch));
        for (String l : v0patch)
            System.out.print(l);

        if (args.length >= 3) {
            FileResource f2 = new FileResource(args[2]);
            List<String> v2 = f2.read().readLines();

            List<String> v2patch = patch.apply(v2);
            System.out.println("v2patch := v2+patch: equals v1?  " + v1.equals(v2patch));
            for (String l : v2patch)
                System.out.print(l);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new JDiff().execute(args);
    }

}
