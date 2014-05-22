package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;

public class JavaPackageDoc
        extends MutableElementDoc {

    String fqpn;

    public JavaPackageDoc(String fqpn) {
        this.fqpn = fqpn;
    }

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        // out.sectionBegin("package:" + getName());
        super.writeObject(out, options);
        // out.sectionEnd();
    }

}
