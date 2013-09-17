package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.lang.TyperFamilyClass;
import net.bodz.bas.text.diff.gnudiff.DiffPrint;

@TyperFamilyClass(DiffFormatTypers.class)
public interface IDiffFormat {

    IDiffFormat SIMPLE = new SimpleDiffFormat();
    IDiffFormat ED = new EDDiffFormat();
    IDiffFormat CONTEXT = new ContextDiffFormat();
    IDiffFormat NORMAL = new NormalDiffFormat();
    IDiffFormat UNIFIED = new UnifiedDiffFormat();

    void printDiffs(IPrintOut out, List<?> a, List<?> b, List<DiffEntry> diffs);

}

class SimpleDiffFormat
        extends AbstractDiffFormat {

    @Override
    protected void formatHunk(List<DiffEntry> diffs, int off, int len) {
        super.formatHunk(diffs, off, len);
    }

    @Override
    protected void hunkSeparator() {
        out.println();
    }

    @Override
    protected void edit0(char op, int index) {
        out.printf(" %6d%c%s\n", index, op, al.get(index));
    }

    @Override
    protected void edit1(char op, int index) {
        out.printf("*%6d%c%s\n", index, op, bl.get(index));
    }
}

class EDDiffFormat
        implements IDiffFormat {
    @Override
    public void printDiffs(IPrintOut out, List<?> a, List<?> b, List<DiffEntry> diff) {
        new DiffPrint.EdPrint(a, b, out).print_script(diff);
    }
}

class ContextDiffFormat
        implements IDiffFormat {
    @Override
    public void printDiffs(IPrintOut out, List<?> a, List<?> b, List<DiffEntry> diff) {
        new DiffPrint.ContextPrint(a, b, out).print_script(diff);
    }
}

class NormalDiffFormat
        implements IDiffFormat {
    @Override
    public void printDiffs(IPrintOut out, List<?> a, List<?> b, List<DiffEntry> diff) {
        new DiffPrint.NormalPrint(a, b, out).print_script(diff);
    }
}

class UnifiedDiffFormat
        implements IDiffFormat {
    @Override
    public void printDiffs(IPrintOut out, List<?> a, List<?> b, List<DiffEntry> diff) {
        new DiffPrint.UnifiedPrint(a, b, out).print_script(diff);
    }
}
