package net.bodz.bas.compare;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;

public interface IListCompareResultFormatter<view_t, mutable_t> {

    void format(Appendable out, IListCompareResult<? extends view_t, ? extends mutable_t> result)
            throws IOException;

    default String format(IListCompareResult<? extends view_t, ? extends mutable_t> result) {
        StringBuilder sb = new StringBuilder(4000);
        try {
            format(sb, result);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return sb.toString();
    }

}
