package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;

/**
 * Not used.
 */
@Deprecated
public class PropertyDoc
        extends MutableElementDoc {

    final IClassDoc classDoc;
    final IPropertyDescriptor propertyDescriptor;

    public PropertyDoc(IClassDoc classDoc, IPropertyDescriptor propertyDescriptor) {
        super(classDoc.getTagLibrary());
        this.classDoc = classDoc;
        this.propertyDescriptor = propertyDescriptor;
    }

    static iString NO_LABEL = null; // iString.fn.val("no label");

    public static PropertyDoc n_a(IClassDoc classDoc, IPropertyDescriptor propertyDescriptor) {
        PropertyDoc propertyDoc = new PropertyDoc(classDoc, propertyDescriptor);
        propertyDoc.setTag(LABEL, propertyDescriptor.getName());
        return propertyDoc;
    }

    public IClassDoc getClassDoc() {
        return classDoc;
    }

    public IPropertyDescriptor getIPropertyDescriptor() {
        return propertyDescriptor;
    }

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException, FormatException {
        // ImportMap imports = getClassDoc().getOrCreateImports();
        // Method getter = propertyDescriptor.getReadMethod();
        // Method setter = propertyDescriptor.getWriteMethod();
        // MethodId getterId = new MethodId(getter);
        // MethodId setterId = new MethodId(setter);
        // String getterQ = getterId.getImportedForm(imports);
        // String setterQ = setterId.getImportedForm(imports);
        out.beginSection("property:" + propertyDescriptor.getName());
        super.writeObject(out, options);
        out.endSection();
    }

}
