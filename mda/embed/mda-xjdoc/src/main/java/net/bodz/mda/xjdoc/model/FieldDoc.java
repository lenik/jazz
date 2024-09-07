package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;

public class FieldDoc
        extends AbstractElementDoc
        implements
            IFieldDoc {

    private final String name;
    private final IClassDoc classDoc;

    public FieldDoc(IClassDoc classDoc, String name) {
        super(classDoc.getTagLibrary());
        this.name = name;
        this.classDoc = classDoc;
    }

    static iString NO_LABEL = null; // iString.fn.val("no label");

    public static FieldDoc n_a(IClassDoc classDoc, String name) {
        FieldDoc fieldDoc = new FieldDoc(classDoc, name);
        fieldDoc.setTag(LABEL, StrFn.wrap(name));
        // fieldDoc.setDescription(iString.fn.val(name));
        return fieldDoc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IClassDoc getClassDoc() {
        return classDoc;
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.fieldDoc(this);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.fmt.flatf.IFlatfForm}. */
    /* _____________________________ */static section.iface __FLATF__;

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException, FormatException {
        out.beginSection("field:" + name);
        super.writeObject(out, options);
        out.endSection();
    }

}
