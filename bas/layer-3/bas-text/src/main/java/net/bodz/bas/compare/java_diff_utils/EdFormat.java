package net.bodz.bas.compare.java_diff_utils;

import java.io.IOException;
import java.util.List;

import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;

public class EdFormat
        extends JduFormatter {

    @Override
    protected void format(Appendable out, AbstractDelta<?> delta)
            throws IOException {
        Chunk<?> s = delta.getSource();
        Chunk<?> t = delta.getTarget();

        out.append(range(s, 1));
        out.append(changeLetter(delta.getType()));
        out.append('\n');

        if (t.size() != 0) {
            boolean inserting = true;

            List<?> lines = t.getLines();
            int s0 = s.getPosition();
            int t0 = t.getPosition();
            int t1 = t.last();

            for (int j = t0; j <= t1; j++) {
                if (!inserting) {
                    out.append(j - t0 + s0 + "a\n");
                    inserting = true;
                }

                if (".".equals(lines.get(j - t0))) {
                    out.append("..\n");
                    out.append(".\n");
                    /* Now change that double dot to the desired single dot. */
                    out.append(j - t0 + s0 + 1 + "s/^\\.\\././\n");
                    inserting = false;
                } else {
                    /* Line is not `.', so output it unmodified. */
                    Object line = lines.get(j - t0);
                    out.append(line.toString());
                }
            }

            if (inserting)
                out.append(".\n");
        }
    }

}
