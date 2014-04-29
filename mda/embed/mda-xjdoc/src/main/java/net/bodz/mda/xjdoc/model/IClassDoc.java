package net.bodz.mda.xjdoc.model;

import java.util.Map;

import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

public interface IClassDoc
        extends IElementDoc {

    ImportMap getImports();

    ImportMap getOrCreateImports();

    void setImports(ImportMap imports);

    Map<String, FieldDoc> getFieldDocs();

    FieldDoc getFieldDoc(String fieldName);

    void setFieldDoc(String fieldName, FieldDoc fieldDoc);

    FieldDoc removeFieldDoc(String fieldName);

    Map<MethodId, MethodDoc> getMethodDocs();

    MethodDoc getMethodDoc(MethodId methodId);

    void setMethodDoc(MethodId methodId, MethodDoc methodDoc);

    MethodDoc removeMethodDoc(MethodId methodId);

}
