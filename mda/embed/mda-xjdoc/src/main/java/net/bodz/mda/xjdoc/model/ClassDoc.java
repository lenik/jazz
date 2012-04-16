package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.free.INegotiation;
import javax.free.NegotiationException;

import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.MethodSignature;
import net.bodz.mda.xjdoc.util.TypeNameContext;

public class ClassDoc
        extends ElementDoc {

    Map<String, FieldDoc> fieldDocs;
    Map<MethodSignature, MethodDoc> methodDocs;

    TypeNameContext imports;

    public ClassDoc(String fqcn) {
        super(fqcn);
        fieldDocs = new LinkedHashMap<String, FieldDoc>();
        methodDocs = new LinkedHashMap<MethodSignature, MethodDoc>();
    }

    public TypeNameContext getImports() {
        return imports;
    }

    public synchronized TypeNameContext getOrCreateImports() {
        if (imports == null) {
            String fqcn = getName();
            String packageName;
            int lastDot = fqcn.lastIndexOf('.');
            if (lastDot == -1)
                packageName = "";
            else
                packageName = fqcn.substring(0, lastDot);
            imports = new TypeNameContext(packageName);
        }
        return imports;
    }

    public void setImports(TypeNameContext imports) {
        this.imports = imports;
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

    public Map<MethodSignature, MethodDoc> getMethodDocs() {
        return methodDocs;
    }

    public MethodDoc getMethodDoc(String methodId) {
        return methodDocs.get(methodId);
    }

    public void setMethodDoc(MethodSignature signature, MethodDoc methodDoc) {
        if (signature == null)
            throw new NullPointerException("signature");
        if (methodDoc == null)
            throw new NullPointerException("methodDoc");
        methodDocs.put(signature, methodDoc);
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
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException, NegotiationException {
        // out.sectionBegin("class");

        StringWriter bodyBuffer = new StringWriter();
        FlatfOutput bodyOut = new FlatfOutput(bodyBuffer);
        {
            super.writeObject(bodyOut, negotiation);

            for (FieldDoc fieldDoc : fieldDocs.values())
                fieldDoc.writeObject(bodyOut, negotiation);

            for (MethodDoc methodDoc : methodDocs.values())
                methodDoc.writeObject(bodyOut, negotiation);
        }

        for (String fqcn : imports.getImportMap().values())
            out.pi("import", fqcn);

        out.getWriter().write(bodyBuffer.toString());

        // out.sectionEnd();
    }

    @Override
    protected boolean processInstruction(String command, String data) {
        if ("import".equals(command)) {
            String fqcn = data;
            getOrCreateImports().importTypeName(fqcn);
            return true;
        } else
            return super.processInstruction(command, data);
    }

}
