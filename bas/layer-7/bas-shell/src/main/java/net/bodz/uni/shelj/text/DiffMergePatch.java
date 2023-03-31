package net.bodz.uni.shelj.text;

import net.bodz.bas.compare.dmp.*;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.text.LinesText;
import net.bodz.bas.text.row.IRow;
import net.bodz.bas.text.row.StringRow;
import net.bodz.bas.text.row.StringView;

/**
 * Diff and patch apply.
 */
@ProgramName("dmp")
public class DiffMergePatch
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(DiffMergePatch.class);

    /**
     * A test option.
     *
     * @option --test
     */
    boolean testOnly;

    /**
     * Context length, default 4.
     *
     * @option -C
     */
    Short contextLen;

    /**
     * Match threshold, default 50%
     *
     * @option -t =PERCENTAGE
     */
    Float threshold;

    /**
     * Compare and patch character by character.
     *
     * @option -c --char-by-char
     */
    boolean byChars;

    /**
     * Compare lines without EOL.
     *
     * @option -k --chop-mode
     */
    boolean chopMode;

    DMPConfig config = new DMPConfig();
    CharsComparator charsComparator;
    LinesComparator linesComparator;

    ITreeOut out = Stdio.cout.indented();

    FileResource f0;
    FileResource f1;
    FileResource f2;
    FileResource f3;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (args.length < 2)
            throw new IllegalArgumentException("expect 2 filenames");

        if (contextLen != null)
            config.Patch_Margin = contextLen;

        if (threshold != null)
            config.Match_Threshold = threshold / 100.0f;

        f0 = new FileResource(args[0]);
        f1 = new FileResource(args[1]);
        f2 = args.length >= 3 ? new FileResource(args[2]) : null;
        f3 = args.length >= 4 ? new FileResource(args[3]) : null;

        if (byChars) {
            charsComparator = new CharsComparator(config);
            byChar();
        } else {
            if (chopMode)
                linesComparator = new TrimmedLinesComparator(config);
            else
                linesComparator = new LinesComparator(config);
            byLine();
        }

    }

    void byChar()
            throws Exception {
        StringView row0 = new StringView(f0.read().readString());
        StringView row1 = new StringView(f1.read().readString());

        EditList<Character> diffs = charsComparator.compareByPack(row0, row1);
        PatchList<Character> patch = diffs.createPatch(row0);
        out.println("diffs.delta:");
        out.println(diffs.toDelta());
        diffs.prettyHtml();

        PatchApplyResult<Character> v0patch = patch.apply(row0);
        check("v0+patch", patch, v0patch, row1);

        if (f2 != null) {
            StringView row2 = new StringView(f2.read().readString());
            StringView row3 = null;
            if (f3 != null) {
                row3 = new StringView(f3.read().readString());
            }

            PatchApplyResult<Character> v2patch = patch.apply(row2);
            check("v2+patch", patch, v2patch, row3);
        }
    }

    void byLine()
            throws Exception {
        StringRow row0 = new StringRow(f0.read().readLines(chopMode));
        StringRow row1 = new StringRow(f1.read().readLines(chopMode));

        EditList<String> diffs = linesComparator.compare(row0, row1);
        PatchList<String> patch = diffs.createPatch(row0);
        out.println("diffs.delta:");
        out.println(diffs.toDelta());
        diffs.prettyHtml();

        PatchApplyResult<String> v0patch = patch.apply(row0);
        check("v0+patch", patch, v0patch, row1);

        if (f2 != null) {
            StringRow row2 = new StringRow(f2.read().readLines(chopMode));
            StringRow row3 = row1;
            if (f3 != null) {
                row3 = new StringRow(f3.read().readLines(chopMode));
            }

            PatchApplyResult<String> v2patch = patch.apply(row2);
            check("v2+patch", patch, v2patch, row3);
        }
    }

    <T> void check(String name, PatchList<T> patchList, PatchApplyResult<T> par, IRow<T> expected) {
        out.enterln(name);
        if (expected != null) {
            if (par.getPatchedRow().equals(expected))
                out.println("same as expected.");
            else
                out.println("not as expected. ");
        }

        if (par.isError()) {
            out.enterln("failed hunks: ");
            int i = 0;
            for (PatchApplyStatus<T> item : par) {
                Patch<T> patch = patchList.get(i);
                if (item.isError()) {
                    out.enterln("#" + i + "\t" + patch.diffs.toDelta());
                    for (RowEdit<T> diff : patch.diffs) {
                        out.println(diff.toString());
                    }
                    out.leave();
                }
                i++;
            }
            out.leave();
        }

        out.enterln("content:");
        {
            if (byChars) {
                String s = par.getPatchedRow().toString();
                for (String line : new LinesText.Builder().text(s).removeEOL().trim().build())
                    out.println(line);
            } else {
                @SuppressWarnings("unchecked")
                IRow<String> row = (IRow<String>) par.getPatchedRow();
                for (String line : row)
                    if (chopMode)
                        out.println("| " + line);
                    else
                        out.print("> " + line);
            }
            out.leave();
        }
        out.leave();
    }

    public static void main(String[] args)
            throws Exception {
        new DiffMergePatch().execute(args);
    }

}
