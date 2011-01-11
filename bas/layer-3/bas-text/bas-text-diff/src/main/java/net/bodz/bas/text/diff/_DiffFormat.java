package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.sio.IPrintOut;

public abstract class _DiffFormat
        implements DiffFormat {

    protected List<?> al;
    protected List<?> bl;
    protected IPrintOut out;

    @Override
    public synchronized void format(List<?> al, List<?> bl, List<DiffInfo> diffs, IPrintOut out) {
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

    protected void formatHunk(List<DiffInfo> diffs, int off, int len) {
        while (len-- > 0) {
            DiffInfo diff = diffs.get(off++);
            for (int d = 0; d < diff.deleted; d++)
                edit0('-', diff.index0 + d);
            for (int i = 0; i < diff.inserted; i++)
                edit1('+', diff.index1 + i);
        }
    }

    protected void hunkSeparator() {
    }

    protected abstract void edit0(char op, int index);

    protected abstract void edit1(char op, int index);

}
