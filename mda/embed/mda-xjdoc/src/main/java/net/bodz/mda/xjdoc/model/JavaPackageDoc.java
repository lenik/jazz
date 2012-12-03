package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class JavaPackageDoc
        extends JavaElementDoc {

    public JavaPackageDoc() {
        super();
    }

    public JavaPackageDoc(String name) {
        super(name);
    }

    @Override
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException {
        // out.sectionBegin("package:" + getName());
        super.writeObject(out, negotiation);
        // out.sectionEnd();
    }

}
