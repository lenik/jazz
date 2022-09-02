package net.bodz.bas.shell.util;

import net.bodz.bas.compare.dmp.CharsComparator;
import net.bodz.bas.compare.dmp.DMPConfig;
import net.bodz.bas.compare.dmp.EditList;
import net.bodz.bas.compare.dmp.PatchList;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.text.row.IRow;
import net.bodz.bas.text.row.StringView;

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

    DMPConfig config = new DMPConfig();
    CharsComparator comparator = new CharsComparator(config);

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (args.length < 2)
            throw new IllegalArgumentException("expect 2 filenames");

        IPrintOut out = Stdio.cout;

        FileResource f0 = new FileResource(args[0]);
        FileResource f1 = new FileResource(args[1]);
        String text0 = f0.read().readString();
        String text1 = f1.read().readString();

        StringView row0 = new StringView(text0);
        StringView row1 = new StringView(text1);
        EditList<Character> diffs = comparator.compare(row0, row1);
        PatchList<Character> patch = diffs.createPatch(row0);
        System.out.println("DIFF:");
        System.out.println(diffs.toDelta());

        IRow<Character> v0patch = patch.apply(row0).row;
        System.out.println("v0patch := v0+patch: equals v1?  " + row1.equals(v0patch));
        System.out.print(v0patch);
        System.out.println();

        if (args.length >= 3) {
            FileResource f2 = new FileResource(args[2]);
            String text2 = f2.read().readString();
            StringView row2 = new StringView(text2);

            IRow<Character> v2patch = patch.apply(row2).row;
            System.out.println("v2patch := v2+patch: equals v1?  " + row1.equals(v2patch));
            System.out.print(v2patch);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new JDiff().execute(args);
    }

}
