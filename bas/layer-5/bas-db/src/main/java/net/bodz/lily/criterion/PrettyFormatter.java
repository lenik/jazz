package net.bodz.lily.criterion;

import net.bodz.bas.io.ITreeOut;

public class PrettyFormatter
        extends AbstractFormatter {

    ITreeOut out;

    public PrettyFormatter(ITreeOut out) {
        this.out = out;
    }

    @Override
    void composite(String name, Composite composite) {
        out.println(name);
        out.enter();
        try {
            for (ICriterion item : composite)
                item.accept(this);
        } finally {
            out.leave();
        }
    }

    @Override
    void field(String op, FieldCriterion field, Object... values) {
        if (! field.yes)
            out.print("~");
        out.print(op);
        out.print(" ");
        out.print(field.fieldName);
        for (Object val : values) {
            out.print(" ");
            out.print(val);
        }
        out.println();
    }

}
