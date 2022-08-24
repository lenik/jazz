package net.bodz.bas.compare.java_diff_utils;

import java.io.IOException;

import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeltaType;

public class NormalFormatter
        extends JduFormatter {

    @Override
    protected void format(Appendable out, AbstractDelta<?> delta)
            throws IOException {
        Chunk<?> s = delta.getSource();
        Chunk<?> t = delta.getTarget();

        out.append(range(s, 1));
        out.append(changeLetter(delta.getType()));
        out.append(range(t, 1));
        out.append('\n');

        for (Object line : delta.getSource().getLines())
            out.append("< " + line);

        if (delta.getType() == DeltaType.CHANGE)
            out.append("---\n");

        for (Object line : delta.getTarget().getLines())
            out.append("> " + line);
    }

}
