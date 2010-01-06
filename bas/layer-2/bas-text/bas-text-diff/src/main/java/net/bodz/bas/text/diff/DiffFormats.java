package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.io.out.CharOut;
import net.bodz.extern.diff.DiffPrint;

public class DiffFormats {

    public static final DiffFormat Simdiff;

    public static final DiffFormat ED;
    public static final DiffFormat Context;
    public static final DiffFormat Normal;
    public static final DiffFormat Unified;

    static {
        Simdiff = new _DiffFormat() {
            @Override
            protected void formatHunk(List<DiffInfo> diffs, int off, int len) {
                super.formatHunk(diffs, off, len);
            }

            @Override
            protected void hunkSeparator() {
                out.println();
            }

            @Override
            protected void edit0(char op, int index) {
                out.printf(" %6d%c%s\n", index, op, al.get(index)); //$NON-NLS-1$
            }

            @Override
            protected void edit1(char op, int index) {
                out.printf("*%6d%c%s\n", index, op, bl.get(index)); //$NON-NLS-1$
            }
        };

        ED = new DiffFormat() {
            @Override
            public void format(List<?> a, List<?> b, List<DiffInfo> diff, CharOut out) {
                new DiffPrint.EdPrint(a, b, out).print_script(diff);
            }
        };
        Context = new DiffFormat() {
            @Override
            public void format(List<?> a, List<?> b, List<DiffInfo> diff, CharOut out) {
                new DiffPrint.ContextPrint(a, b, out).print_script(diff);
            }
        };
        Normal = new DiffFormat() {
            @Override
            public void format(List<?> a, List<?> b, List<DiffInfo> diff, CharOut out) {
                new DiffPrint.NormalPrint(a, b, out).print_script(diff);
            }
        };
        Unified = new DiffFormat() {
            @Override
            public void format(List<?> a, List<?> b, List<DiffInfo> diff, CharOut out) {
                new DiffPrint.UnifiedPrint(a, b, out).print_script(diff);
            }
        };
    }

}
