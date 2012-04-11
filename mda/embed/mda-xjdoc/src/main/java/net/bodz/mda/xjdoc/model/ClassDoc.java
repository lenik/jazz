package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.text.flatf.IFlatfLoader;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.ISectionHandler;
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

    /**
     * classdoc file format.
     * 
     * The .classdoc file should be located along with the .class file:
     * <ul>
     * <li>foo/Bar$Inner.class
     * <li>foo/Bar$Inner.classdoc
     * </ul>
     * 
     * Example:
     * 
     * <pre>
     * #comments
     * %import f.q.c.Name
     * 
     * # optional "[type]"
     * [field:...]
     * [method(int,String[],...)]
     * . = "..." \
     *      en-us "...\n" \
     *          "multi-lines ..." \
     *      zh-cn "..."
     * author = Author 1
     * author = Author 2
     * param.foo = Foo is a parameter.
     * param.bar = Bar is another parameter.
     * throws.IOException = I/O error occurred.
     * </pre>
     * 
     * Features:
     * <ul>
     * <li>'\' in the end-of-line means continuation.
     * <li>\n, \ (space), \", \\ should work.
     * </ul>
     */
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
            out.sectionBegin("method:" + methodId);
            methodDoc.writeObject(out);
            out.sectionEnd();
        }
    }

    class SectionHandler
            implements ISectionHandler {

        @Override
        public void sectionBegin(String name) {
        }

        @Override
        public void sectionEnd(String name) {
        }

        @Override
        public void attribute(String name, String string) {
        }

    }

    @Override
    public void loadObject(IFlatfLoader loader)
            throws IOException, ParseException {

        while (in.nextSection()) {
            String sectionName = in.getSectionName();
            if (sectionName.startsWith("field:")) {
                String fieldName = sectionName.substring(6);
                FieldDoc fieldDoc = new FieldDoc();
                fieldDoc.readObject(in);
                fieldDocs.put(fieldName, fieldDoc);
            } else if (sectionName.startsWith("method:")) {
                assert sectionName.contains("(") && sectionName.contains(")");
                String methodId = sectionName.substring(7);
                MethodDoc methodDoc = new MethodDoc();
                methodDoc.readObject(in);
                methodDocs.put(methodId, methodDoc);
            } else {
                throw new ParseException("Bad section name: " + sectionName, 0);
            }
        }
    }

}
