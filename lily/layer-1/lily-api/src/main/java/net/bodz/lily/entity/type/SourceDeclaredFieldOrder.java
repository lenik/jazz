package net.bodz.lily.entity.type;

import net.bodz.mda.xjdoc.IXjdocProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class SourceDeclaredFieldOrder
        extends AbstractFieldOrder {

    static IXjdocProvider provider = Xjdocs.getDefaultProvider();

    public SourceDeclaredFieldOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected void findFields(Class<?> clazz) {
        ClassDoc classDoc = provider.getClassDoc(clazz);
        if (classDoc == null)
            return;

        for (String fieldName : classDoc.getFieldDocs().keySet()) {
            fieldNames.put(fieldName, ++nextOrder);
        }
    }

}