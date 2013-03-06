package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.ISectionHandler;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

public class ClassDoc
        extends JavaElementDoc
        implements IClassDoc {

    private ImportMap imports;
    private Map<String, FieldDoc> fieldDocs;
    private Map<MethodId, MethodDoc> methodDocs;

    public ClassDoc(String fqcn) {
        super(fqcn);
        fieldDocs = new LinkedHashMap<String, FieldDoc>();
        methodDocs = new LinkedHashMap<MethodId, MethodDoc>();
    }

    @Override
    public ImportMap getImports() {
        return imports;
    }

    @Override
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

    @Override
    public void setImports(ImportMap imports) {
        this.imports = imports;
    }

    @Override
    public Map<String, FieldDoc> getFieldDocs() {
        return fieldDocs;
    }

    @Override
    public FieldDoc getFieldDoc(String fieldName) {
        return fieldDocs.get(fieldName);
    }

    @Override
    public void setFieldDoc(String fieldName, FieldDoc fieldDoc) {
        if (fieldName == null)
            throw new NullPointerException("fieldName");
        if (fieldDoc == null)
            throw new NullPointerException("fieldDoc");
        fieldDocs.put(fieldName, fieldDoc);
    }

    @Override
    public FieldDoc removeFieldDoc(String fieldName) {
        return fieldDocs.remove(fieldName);
    }

    @Override
    public Map<MethodId, MethodDoc> getMethodDocs() {
        return methodDocs;
    }

    @Override
    public MethodDoc getMethodDoc(MethodId methodId) {
        return methodDocs.get(methodId);
    }

    @Override
    public void setMethodDoc(MethodId methodId, MethodDoc methodDoc) {
        if (methodId == null)
            throw new NullPointerException("methodId");
        if (methodDoc == null)
            throw new NullPointerException("methodDoc");
        methodDocs.put(methodId, methodDoc);
    }

    @Override
    public MethodDoc removeMethodDoc(MethodId methodId) {
        return methodDocs.remove(methodId);
    }

    // --o IFlatfSerializable

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
            throws IOException {
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
    public ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation) {
        if (sectionName == null)
            return super.getSectionHandler(sectionName, negotiation);

        if (sectionName.startsWith("field:")) {
            String fieldName = sectionName.substring(6);
            FieldDoc fieldDoc = getFieldDoc(fieldName);
            if (fieldDoc == null) {
                fieldDoc = new FieldDoc(this, fieldName);
                setFieldDoc(fieldName, fieldDoc);
            }
            return fieldDoc.getSectionHandler(null, negotiation);
        }

        if (sectionName.startsWith("method:")) {
            String _id = sectionName.substring(7);

            String methodName = StringPart.before(_id, "(");
            String types = StringPart.beforeLast(StringPart.after(_id, "("), ")");

            String typev[] = {};
            if (!types.isEmpty())
                typev = types.split(",");

            MethodId methodId = new MethodId(methodName, typev.length);
            for (int i = 0; i < typev.length; i++) {
                String type = typev[i];
                int dims = 0;
                while (type.endsWith("[]")) {
                    type = type.substring(0, type.length() - 2);
                    dims++;
                }
                String fqcn = imports.normalize(type);
                methodId.setParameterType(i, fqcn, dims);
            }

            MethodDoc methodDoc = getMethodDoc(methodId);
            if (methodDoc == null) {
                methodDoc = new MethodDoc(this, methodId);
                setMethodDoc(methodId, methodDoc);
            }
            return methodDoc.getSectionHandler(null, negotiation);
        }

        throw new IllegalArgumentException("Illegal section name: " + sectionName);
    }

    @Override
    protected boolean processInstruction(String command, String data)
            throws ParseException {
        if ("import".equals(command)) {
            String fqcn = data;
            getOrCreateImports().add(fqcn);
            return true;
        } else
            return super.processInstruction(command, data);
    }

}
