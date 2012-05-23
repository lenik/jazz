package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

public class ClassDoc
        extends ElementDoc {

    Map<String, FieldDoc> fieldDocs;
    Map<MethodId, MethodDoc> methodDocs;

    ImportMap imports;

    public ClassDoc(String fqcn) {
        super(fqcn);
        fieldDocs = new LinkedHashMap<String, FieldDoc>();
        methodDocs = new LinkedHashMap<MethodId, MethodDoc>();
    }

    public ImportMap getImports() {
        return imports;
    }

    public synchronized ImportMap getOrCreateImports() {
        if (imports == null) {
            String fqcn = getName();
            String packageName;
            int lastDot = fqcn.lastIndexOf('.');
            if (lastDot == -1)
                packageName = "";
            else
                packageName = fqcn.substring(0, lastDot);
            imports = new ImportMap(packageName);
        }
        return imports;
    }

    public void setImports(ImportMap imports) {
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

    public Map<MethodId, MethodDoc> getMethodDocs() {
        return methodDocs;
    }

    public MethodDoc getMethodDoc(MethodId methodId) {
        return methodDocs.get(methodId);
    }

    public void setMethodDoc(MethodId methodId, MethodDoc methodDoc) {
        if (methodId == null)
            throw new NullPointerException("methodId");
        if (methodDoc == null)
            throw new NullPointerException("methodDoc");
        methodDocs.put(methodId, methodDoc);
    }

    public MethodDoc removeMethodDoc(MethodId methodId) {
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

        BCharOut bodyBuffer = new BCharOut();
        FlatfOutput bodyOut = new FlatfOutput(bodyBuffer);
        {
            super.writeObject(bodyOut, negotiation);

            for (FieldDoc fieldDoc : fieldDocs.values())
                fieldDoc.writeObject(bodyOut, negotiation);

            for (MethodDoc methodDoc : methodDocs.values())
                methodDoc.writeObject(bodyOut, negotiation);
        }

        for (String fqcn : imports.getMap().values())
            out.pi("import", fqcn);

        out.getCharOut().write(bodyBuffer.toString());

        // out.sectionEnd();
    }

    @Override
    protected boolean processInstruction(String command, String data) {
        if ("import".equals(command)) {
            String fqcn = data;
            getOrCreateImports().add(fqcn);
            return true;
        } else
            return super.processInstruction(command, data);
    }

}
