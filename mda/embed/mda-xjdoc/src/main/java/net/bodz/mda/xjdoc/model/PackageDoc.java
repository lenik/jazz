package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import javax.free.INegotiation;
import javax.free.NegotiationException;

import net.bodz.bas.text.flatf.IFlatfOutput;

public class PackageDoc
        extends ElementDoc {

    public PackageDoc() {
        super();
    }

    public PackageDoc(String name) {
        super(name);
    }

    @Override
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException, NegotiationException {
        // out.sectionBegin("package:" + getName());
        super.writeObject(out, negotiation);
        // out.sectionEnd();
    }

}
