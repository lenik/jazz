package net.bodz.bas.functors;

import net.bodz.bas.lang.Control;

public class ControlGoto extends Control {

    private static final long serialVersionUID = -3386772529344946404L;

    public ControlGoto(Object label) {
        super(label);
    }

    public Object getLabel() {
        return this.getDetail();
    }

    public boolean matches(IFunctor<?> f) {
        Object label = getLabel();
        if (label == null)
            return true;
        if (f instanceof _Functor<?>) {
            if (((_Functor<?>) f).getOuter() instanceof Sequence<?>) {
                Sequence<?> outseq = ((Sequence<?>) ((_Functor<?>) f).getOuter());
                if (outseq.labelAt(label) == f)
                    return true;
            }
        }
        return false;
    }

}
