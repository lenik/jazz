package net.bodz.mda.xjdoc.model;

import java.util.Map;

import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

public abstract class DecoratedClassDoc
        extends DecoratedElementDoc
        implements IClassDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedClassDoc(IClassDoc _orig) {
        super(_orig);
    }

    @Override
    public IClassDoc getWrapped() {
        return (IClassDoc) super.getWrapped();
    }

    @Override
    public ImportMap getImports() {
        return getWrapped().getImports();
    }

    @Override
    public ImportMap getOrCreateImports() {
        return getWrapped().getOrCreateImports();
    }

    @Override
    public void setImports(ImportMap imports) {
        getWrapped().setImports(imports);
    }

    @Override
    public Map<String, FieldDoc> getFieldDocs() {
        return getWrapped().getFieldDocs();
    }

    @Override
    public FieldDoc getFieldDoc(String fieldName) {
        return getWrapped().getFieldDoc(fieldName);
    }

    @Override
    public void setFieldDoc(String fieldName, FieldDoc fieldDoc) {
        getWrapped().setFieldDoc(fieldName, fieldDoc);
    }

    @Override
    public FieldDoc removeFieldDoc(String fieldName) {
        return getWrapped().removeFieldDoc(fieldName);
    }

    @Override
    public Map<MethodId, MethodDoc> getMethodDocs() {
        return getWrapped().getMethodDocs();
    }

    @Override
    public MethodDoc getMethodDoc(MethodId methodId) {
        return getWrapped().getMethodDoc(methodId);
    }

    @Override
    public void setMethodDoc(MethodId methodId, MethodDoc methodDoc) {
        getWrapped().setMethodDoc(methodId, methodDoc);
    }

    @Override
    public MethodDoc removeMethodDoc(MethodId methodId) {
        return getWrapped().removeMethodDoc(methodId);
    }

}
