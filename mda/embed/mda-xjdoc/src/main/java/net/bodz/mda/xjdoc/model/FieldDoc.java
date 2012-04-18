package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class FieldDoc
        extends ElementDoc {

    final ClassDoc classDoc;

    public FieldDoc(ClassDoc classDoc, String name) {
        super(name);
        this.classDoc = classDoc;
    }

    public ClassDoc getClassDoc() {
        return classDoc;
    }

    @Override
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException, NegotiationException {
        out.sectionBegin("field:" + getName());
        super.writeObject(out, negotiation);
        out.sectionEnd();
    }

}
