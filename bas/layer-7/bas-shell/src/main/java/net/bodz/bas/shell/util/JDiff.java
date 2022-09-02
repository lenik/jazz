package net.bodz.bas.shell.util;

import net.bodz.bas.compare.dmp.CharsComparator;
import net.bodz.bas.compare.dmp.DMPConfig;
import net.bodz.bas.compare.dmp.EditList;
import net.bodz.bas.compare.dmp.IRowDifference;
import net.bodz.bas.compare.dmp.PatchApplyResult;
import net.bodz.bas.compare.dmp.PatchList;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.text.LinesText;
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

    ITreeOut out = Stdio.cout.indented();

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (args.length < 2)
            throw new IllegalArgumentException("expect 2 filenames");

        FileResource f0 = new FileResource(args[0]);
        FileResource f1 = new FileResource(args[1]);
        StringView row0 = new StringView(f0.read().readString());
        StringView row1 = new StringView(f1.read().readString());

        EditList<Character> diffs = comparator.compareByPack(row0, row1);
        PatchList<Character> patch = diffs.createPatch(row0);
        out.println("diffs.delta:");
        out.println(diffs.toDelta());
        diffs.prettyHtml();

        PatchApplyResult<Character> v0patch = patch.apply(row0);
        check("v0+patch", v0patch, row1);

        if (args.length >= 3) {
            FileResource f2 = new FileResource(args[2]);
            StringView row2 = new StringView(f2.read().readString());
            StringView row3 = row1;
            if (args.length >= 4) {
                FileResource f3 = new FileResource(args[3]);
                row3 = new StringView(f3.read().readString());
            }

            PatchApplyResult<Character> v2patch = patch.apply(row2);
            check("v2+patch", v2patch, row3);
        }
    }

    void check(String name, PatchApplyResult<Character> par, IRow<Character> expected) {
        out.enterln(name);
        if (expected != null) {
            if (par.row.equals(expected))
                out.println("same as expected.");
            else
                out.println("unexpected. ");
        }

        boolean anyFail = false;
        for (Boolean status : par.results)
            if (!status)
                anyFail = true;
        if (anyFail) {
            out.enterln("failed hunks: ");
            int i = 0;
            for (IRowDifference<Character> diff : par.changes) {
                Boolean status = par.results.cellAt(i);
                if (status == Boolean.FALSE)
                    out.printf("#%d: %s\n", i, diff.getDelta());
                i++;
            }
            out.leave();
        }

        out.enterln("content:");
        {
            String s = par.row.toString();
            for (String line : new LinesText.Builder().text(s).removeEOL().trim().build())
                out.println(line);
            out.leave();
        }
        out.leave();
    }

    public static void main(String[] args)
            throws Exception {
        new JDiff().execute(args);
    }

}
