package net.bodz.bas.compare.java_diff_utils;

import java.util.List;

import net.bodz.bas.compare.IListEditDelta;
import net.bodz.bas.compare.IListEditDeltaType;
import net.bodz.bas.compare.ListEditDeltaType;
import net.bodz.bas.compare.PatchException;
import net.bodz.bas.err.UnexpectedException;

import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.DeltaUtil;
import com.github.difflib.patch.PatchFailedException;

public class JduDelta<T>
        implements
            IListEditDelta<T> {

    AbstractDelta<T> delta;

    public JduDelta(AbstractDelta<T> delta) {
        this.delta = delta;
    }

    @Override
    public IListEditDeltaType getType() {
        switch (delta.getType()) {
        case CHANGE:
            return ListEditDeltaType.CHANGE;
        case DELETE:
            return ListEditDeltaType.DELETE;
        case INSERT:
            return ListEditDeltaType.INSERT;
        case EQUAL:
            return ListEditDeltaType.EQUAL;
        default:
            throw new UnexpectedException();
        }
    }

    @Override
    public void apply(List<T> source)
            throws PatchException {
        try {
            DeltaUtil.applyTo(delta, source);
        } catch (PatchFailedException e) {
            throw new PatchException(e.getMessage(), e);
        }
    }

    @Override
    public void unapply(List<T> target)
            throws PatchException {
        try {
            DeltaUtil.restore(delta, target);
        } catch (PatchFailedException e) {
            throw new PatchException(e.getMessage(), e);
        }
    }

}
