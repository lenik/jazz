package net.bodz.bas.text.diff;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.text.diff.gnudiff.DiffPrint;

@typer.family(DiffFormatTypers.class)
public interface IDiffFormat {

    IDiffFormat SIMPLE = new SimpleDiffFormat();
    IDiffFormat ED = new EDDiffFormat();
    IDiffFormat CONTEXT = new ContextDiffFormat();
    IDiffFormat NORMAL = new NormalDiffFormat();
    IDiffFormat UNIFIED = new UnifiedDiffFormat();

    void printDiffs(Appendable out, List<?> a, List<?> b, List<DiffEntry> diffs)
            throws IOException;

}

class SimpleDiffFormat
        extends AbstractDiffFormat {

    @Override
    protected void formatHunk(List<DiffEntry> diffs, int off, int len)
            throws IOException {
        super.formatHunk(diffs, off, len);
    }

    @Override
    protected void hunkSeparator()
            throws IOException {
        out.append("\n");
    }

    @Override
    protected void edit0(char op, int index)
            throws IOException {
        out.append(String.format(" %6d%c%s\n", index, op, al.get(index)));
    }

    @Override
    protected void edit1(char op, int index)
            throws IOException {
        out.append(String.format("*%6d%c%s\n", index, op, bl.get(index)));
    }
}

class EDDiffFormat
        implements
            IDiffFormat {
    @Override
    public void printDiffs(Appendable out, List<?> a, List<?> b, List<DiffEntry> diff)
            throws IOException {
        new DiffPrint.EdPrint(a, b, out).print_script(diff);
    }
}

class ContextDiffFormat
        implements
            IDiffFormat {
    @Override
    public void printDiffs(Appendable out, List<?> a, List<?> b, List<DiffEntry> diff)
            throws IOException {
        new DiffPrint.ContextPrint(a, b, out).print_script(diff);
    }
}

class NormalDiffFormat
        implements
            IDiffFormat {
    @Override
    public void printDiffs(Appendable out, List<?> a, List<?> b, List<DiffEntry> diff)
            throws IOException {
        new DiffPrint.NormalPrint(a, b, out).print_script(diff);
    }
}

class UnifiedDiffFormat
        implements
            IDiffFormat {
    @Override
    public void printDiffs(Appendable out, List<?> a, List<?> b, List<DiffEntry> diff)
            throws IOException {
        new DiffPrint.UnifiedPrint(a, b, out).print_script(diff);
    }
}
