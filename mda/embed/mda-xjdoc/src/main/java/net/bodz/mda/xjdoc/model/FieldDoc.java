package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;

public class FieldDoc
        extends MutableElementDoc
        implements IFieldDoc {

    private String name;
    private final IClassDoc classDoc;

    public FieldDoc(IClassDoc classDoc, String name) {
        super(classDoc.getTagLibrary());
        this.name = name;
        this.classDoc = classDoc;
    }

    static iString NO_LABEL = null; // iString.fn.val("no label");

    public static FieldDoc n_a(IClassDoc classDoc, String name) {
        FieldDoc fieldDoc = new FieldDoc(classDoc, name);
        fieldDoc.setTag(LABEL, NO_LABEL);
        // fieldDoc.setDescription(iString.fn.val(name));
        fieldDoc.setText(iString.fn.val(name));
        return fieldDoc;
    }

    @Override
    public IClassDoc getClassDoc() {
        return classDoc;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.fmt.flatf.IFlatfSerializable}. */
    /* _____________________________ */static section.iface __FLATF__;

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        out.beginSection("field:" + name);
        super.writeObject(out, options);
        out.endSection();
    }

}
