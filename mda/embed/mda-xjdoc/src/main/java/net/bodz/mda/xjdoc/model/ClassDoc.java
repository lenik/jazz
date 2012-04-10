package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.TypeNameContext;

public class ClassDoc
        extends ElementDoc {

    TypeNameContext typeNameContext;

    Map<String, FieldDoc> fieldDocs = new LinkedHashMap<String, FieldDoc>();
    Map<String, MethodDoc> methodDocs = new LinkedHashMap<String, MethodDoc>();

    public ClassDoc(String fqcn) {
        super(fqcn);

        String packageName;
        int lastDot = fqcn.lastIndexOf('.');
        if (lastDot == -1)
            packageName = "";
        else
            packageName = fqcn.substring(0, lastDot);

        typeNameContext = new TypeNameContext(packageName);
    }

    public TypeNameContext getTypeNameContext() {
        return typeNameContext;
    }

    public Map<String, FieldDoc> getFieldDocs() {
        return fieldDocs;
    }

    public FieldDoc getFieldDoc(String fieldName) {
        return fieldDocs.get(fieldName);
    }

    public void setFieldDoc(String fieldName, FieldDoc fieldDoc) {
        if (fieldName == null)
            throw new NullPointerException("fieldName");
        if (fieldDoc == null)
            throw new NullPointerException("fieldDoc");
        fieldDocs.put(fieldName, fieldDoc);
    }

    public FieldDoc removeFieldDoc(String fieldName) {
        return fieldDocs.remove(fieldName);
    }

    public Map<String, MethodDoc> getMethodDocs() {
        return methodDocs;
    }

    public MethodDoc getMethodDoc(String methodId) {
        return methodDocs.get(methodId);
    }

    public void setMethodDoc(String methodId, MethodDoc methodDoc) {
        if (methodId == null)
            throw new NullPointerException("methodId");
        if (methodDoc == null)
            throw new NullPointerException("methodDoc");
        methodDocs.put(methodId, methodDoc);
    }

    public MethodDoc removeMethodDoc(String methodId) {
        return methodDocs.remove(methodId);
    }

    @Override
    public void readObject(IFlatfInput in)
            throws IOException, ParseException {
        super.readObject(in);

        while (in.nextSection()) {
            String sectionName = in.getSectionName();
            if (sectionName.startsWith("field:")) {
                String fieldName = sectionName.substring(6);
                FieldDoc fieldDoc = new FieldDoc();
                fieldDoc.readObject(in);
                fieldDocs.put(fieldName, fieldDoc);
            } else if (sectionName.contains("(") && sectionName.contains(")")) {
                String methodId = sectionName;
                MethodDoc methodDoc = new MethodDoc();
                methodDoc.readObject(in);
                methodDocs.put(methodId, methodDoc);
            } else {
                throw new ParseException("Bad section name: " + sectionName, 0);
            }
        }
    }

    @Override
    public void writeObject(IFlatfOutput out)
            throws IOException {
        super.writeObject(out);

        for (Entry<String, FieldDoc> field : fieldDocs.entrySet()) {
            String fieldName = field.getKey();
            FieldDoc fieldDoc = field.getValue();
            out.sectionBegin("field:" + fieldName);
            fieldDoc.writeObject(out);
            out.sectionEnd();
        }

        for (Entry<String, MethodDoc> method : methodDocs.entrySet()) {
            String methodId = method.getKey();
            MethodDoc methodDoc = method.getValue();
            out.sectionBegin(methodId);
            methodDoc.writeObject(out);
            out.sectionEnd();
        }
    }

}
