package net.bodz.bas.text.diff;

import java.io.IOException;
import java.util.List;

public abstract class AbstractDiffFormat
        implements
            IDiffFormat {

    protected List<?> al;
    protected List<?> bl;
    protected Appendable out;

    @Override
    public synchronized void printDiffs(Appendable out, List<?> al, List<?> bl, List<DiffEntry> diffs)
            throws IOException {
        assert al != null;
        assert bl != null;
        assert diffs != null;
        assert out != null;
        this.al = al;
        this.bl = bl;
        this.out = out;
        int len = diffs.size();
        for (int i = 0; i < len; i++) {
            if (i != 0)
                hunkSeparator();
            formatHunk(diffs, i, 1);
        }
        this.al = null;
        this.bl = null;
        this.out = null;
    }

    protected void formatHunk(List<DiffEntry> diffs, int off, int len)
            throws IOException {
        while (len-- > 0) {
            DiffEntry diff = diffs.get(off++);
            for (int d = 0; d < diff.deleted; d++)
                edit0('-', diff.index0 + d);
            for (int i = 0; i < diff.inserted; i++)
                edit1('+', diff.index1 + i);
        }
    }

    protected void hunkSeparator()
            throws IOException {
    }

    protected abstract void edit0(char op, int index)
            throws IOException;

    protected abstract void edit1(char op, int index)
            throws IOException;

}
