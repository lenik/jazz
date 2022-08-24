package net.bodz.bas.shell.util;

import java.util.List;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeltaType;
import com.github.difflib.patch.Patch;

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

        Patch<String> patch = DiffUtils.diff(v0, v1);
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            normal(out, delta);
        }

        List<String> v2 = patch.applyTo(v0);
        for (String l : v2)
            System.out.print(l);
    }

    void ed(IPrintOut out, AbstractDelta<String> delta) {
        Chunk<String> s = delta.getSource();
        Chunk<String> t = delta.getTarget();

        out.print(range(s, 1));
        out.print(changeLetter(delta.getType()));
        out.println();

        if (t.size() != 0) {
            boolean inserting = true;

            List<String> lines = t.getLines();
            int s0 = s.getPosition();
            int t0 = t.getPosition();
            int t1 = t.last();

            for (int j = t0; j <= t1; j++) {
                if (!inserting) {
                    out.println(j - t0 + s0 + "a");
                    inserting = true;
                }

                if (".".equals(lines.get(j - t0))) {
                    out.println("..");
                    out.println(".");
                    /* Now change that double dot to the desired single dot. */
                    out.println(j - t0 + s0 + 1 + "s/^\\.\\././");
                    inserting = false;
                } else
                    /* Line is not `.', so output it unmodified. */
                    out.print(lines.get(j - t0));
            }

            if (inserting)
                out.println(".");
        }
    }

    void normal(IPrintOut out, AbstractDelta<String> delta) {
        Chunk<String> s = delta.getSource();
        Chunk<String> t = delta.getTarget();

        out.print(range(s, 1));
        out.print(changeLetter(delta.getType()));
        out.print(range(t, 1));
        out.println();

        for (String line : delta.getSource().getLines())
            out.print("< " + line);

        if (delta.getType() == DeltaType.CHANGE)
            out.println("---");

        for (String line : delta.getTarget().getLines())
            out.print("> " + line);
    }

    String range(Chunk<?> chunk, int base) {
        return range(chunk, base, "%d");
    }

    String range(Chunk<?> chunk, int base, String extraFormat) {
        int first = chunk.getPosition() + base;
        int last = chunk.last() + base;
        if (first < last)
            return first + "," + last;
        else
            return String.format(extraFormat, last);
    }

    char changeLetter(DeltaType type) {
        switch (type) {
        case CHANGE:
            return 'c';
        case DELETE:
            return 'd';
        case EQUAL:
            return '=';
        case INSERT:
            return 'a';
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args)
            throws Exception {
        new JDiff().execute(args);
    }

}
