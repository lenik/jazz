package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.rtx.IOptions;
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
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        // out.sectionBegin("package:" + getName());
        super.writeObject(out, options);
        // out.sectionEnd();
    }

}
