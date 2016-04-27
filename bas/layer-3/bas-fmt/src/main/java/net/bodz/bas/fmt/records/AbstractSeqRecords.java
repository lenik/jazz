package net.bodz.bas.fmt.records;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.iterator.immed.AbstractMitablex;

public abstract class AbstractSeqRecords<T>
        extends AbstractMitablex<T, IOException>
        implements ISeqRecords<T> {

    protected final IStreamInputSource source;

    protected AbstractSeqRecords(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

    public List<T> toList() {
        return toList(0, -1);
    }

    public List<T> toList(long offset) {
        return toList(offset, -1);
    }

    public List<T> toList(long offset, long limit) {
        List<T> list = new ArrayList<>();
        long index = 0;
        for (T item : this) {
            long pos = index - offset;
            if (pos < 0)
                continue;
            if (pos >= limit && limit != -1)
                break;
            list.add(item);
            index++;
        }
        return list;
    }

}
