package net.bodz.bas.repr.position;

import net.bodz.bas.t.ref.Ref;

public class InRef
        extends AbstractObjectOccurrence {

    Ref<?> ref;

    public InRef(Ref<?> ref) {
        if (ref == null)
            throw new NullPointerException("ref");
        this.ref = ref;
    }

    @Override
    public Object getContext() {
        return ref;
    }

    @Override
    public String getPath() {
        return null;
    }

}
