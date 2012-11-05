package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class FieldDoc
        extends ElementDoc
        implements IFieldDoc {

    final ClassDoc classDoc;

    public FieldDoc(ClassDoc classDoc, String name) {
        super(name);
        this.classDoc = classDoc;
    }

    @Override
    public ClassDoc getClassDoc() {
        return classDoc;
    }

    // --o IFlatfSerializable

    @Override
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException {
        out.sectionBegin("field:" + getName());
        super.writeObject(out, negotiation);
        out.sectionEnd();
    }

}
