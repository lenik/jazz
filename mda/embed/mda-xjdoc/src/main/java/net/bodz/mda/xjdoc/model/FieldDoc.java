package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class FieldDoc
        extends MutableElementDoc
        implements IFieldDoc {

    final ClassDoc classDoc;

    public FieldDoc(ClassDoc classDoc, String name) {
        super(name);
        this.classDoc = classDoc;
    }

    public static FieldDoc n_a(ClassDoc classDoc, String name) {
        FieldDoc fieldDoc = new FieldDoc(classDoc, name);
        fieldDoc.setLabel(iString.fn.val("no label"));
        // fieldDoc.setDescription(iString.fn.val(name));
        fieldDoc.setText(iString.fn.val(name));
        return fieldDoc;
    }

    @Override
    public ClassDoc getClassDoc() {
        return classDoc;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.text.flatf.IFlatfSerializable}. */
    /* _____________________________ */static section.iface __FLATF__;

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        out.beginSection("field:" + getName());
        super.writeObject(out, options);
        out.endSection();
    }

}
