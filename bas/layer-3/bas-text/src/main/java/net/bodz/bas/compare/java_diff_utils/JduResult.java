package net.bodz.bas.compare.java_diff_utils;

import java.util.List;

import net.bodz.bas.compare.MutableListCompareResult;
import net.bodz.bas.compare.PatchException;

import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.ChangeDelta;
import com.github.difflib.patch.DeleteDelta;
import com.github.difflib.patch.InsertDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;

public class JduResult<T>
        extends MutableListCompareResult<T, T, JduDelta<T>> {

    private static final long serialVersionUID = 1L;

    Patch<T> patch;

    public JduResult(List<? extends T> source, List<? extends T> target, Patch<T> patch) {
        super(source, target);
        this.patch = patch;
        for (AbstractDelta<T> delta : patch.getDeltas())
            add(new JduDelta<>(delta));
    }

    @Override
    public List<T> apply(List<T> source)
            throws PatchException {
        try {
            List<T> result = patch.applyTo(source);
            return result;
        } catch (PatchFailedException e) {
            throw new PatchException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> unapply(List<T> target)
            throws PatchException {
        return patch.restore(target);
    }

    public List<T> unapply2(List<T> target)
            throws PatchException {
        List<AbstractDelta<T>> deltas = patch.getDeltas();
        Patch<T> reversed = new Patch<>(deltas.size());
        for (AbstractDelta<T> delta : deltas) {
            switch (delta.getType()) {
            case CHANGE:
                delta = new ChangeDelta<>(delta.getTarget(), delta.getSource());
                break;

            case DELETE:
                delta = new InsertDelta<>(delta.getTarget(), delta.getSource());
                break;

            case INSERT:
                delta = new DeleteDelta<>(delta.getTarget(), delta.getSource());
                break;

            case EQUAL:
            }
            reversed.addDelta(delta);
        }
        try {
            List<T> result = reversed.applyTo(target);
            return result;
        } catch (PatchFailedException e) {
            throw new PatchException(e.getMessage(), e);
        }
    }

    @Override
    public void applyTo(List<T> source)
            throws PatchException {
        List<T> result = apply(source);
        source.clear();
        source.addAll(result);
    }

    @Override
    public void unapplyTo(List<T> target)
            throws PatchException {
        List<T> result = unapply(target);
        target.clear();
        target.addAll(result);
    }

}
