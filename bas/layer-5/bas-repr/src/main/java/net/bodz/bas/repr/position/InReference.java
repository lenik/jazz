package net.bodz.bas.repr.position;

import java.lang.ref.Reference;

public class InReference
        extends AbstractObjectOccurrence {

    private Reference<?> reference;

    public InReference(Reference<?> reference) {
        if (reference == null)
            throw new NullPointerException("reference");
        this.reference = reference;
    }

    @Override
    public Object getContext() {
        return reference;
    }

    @Override
    public String getPath() {
        return null;
    }

}
