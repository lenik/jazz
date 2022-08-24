package net.bodz.bas.compare.java_diff_utils;

import java.io.IOException;

import net.bodz.bas.compare.IListCompareResult;
import net.bodz.bas.compare.IListCompareResultFormatter;

import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeltaType;

public abstract class JduFormatter
        implements
            IListCompareResultFormatter<Object, Object> {

    @Override
    public void format(Appendable out, IListCompareResult<?, ?> result)
            throws IOException {
        int n = result.getDeltaCount();
        for (int i = 0; i < n; i++) {
            JduDelta<?> delta = (JduDelta<?>) result.getDelta(i);
            format(out, delta.delta);
        }
    }

    protected abstract void format(Appendable out, AbstractDelta<?> delta)
            throws IOException;

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

}
