package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public class JavaPackageDoc
        extends AbstractElementDoc {

    final String qName;

    public JavaPackageDoc(ITagLibrary tagLibrary, String qName) {
        super(tagLibrary);
        this.qName = qName;
    }

    @Override
    public String getName() {
        return qName;
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.packageDoc(this);
    }

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException, FormatException {
        // out.sectionBegin("package:" + getName());
        super.writeObject(out, options);
        // out.sectionEnd();
    }

}
