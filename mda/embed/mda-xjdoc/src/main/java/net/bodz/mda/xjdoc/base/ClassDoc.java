package net.bodz.mda.xjdoc.base;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.mda.xjdoc.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.rt.TypeNameContext;


public class ClassDoc
        extends ElementDoc {

    TypeNameContext typeNameContext;

    Map<String, FieldDoc> fields = new HashMap<String, FieldDoc>();
    Map<String, MethodDoc> methods = new HashMap<String, MethodDoc>();

    public ClassDoc() {
        super();
    }

    public ClassDoc(String name) {
        super(name);
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
                fields.put(fieldName, fieldDoc);
            } else if (sectionName.contains("(") && sectionName.contains(")")) {
                String methodId = sectionName;
                MethodDoc methodDoc = new MethodDoc();
                methodDoc.readObject(in);
                methods.put(methodId, methodDoc);
            } else {
                throw new ParseException("Bad section name: " + sectionName, 0);
            }
        }
    }

    @Override
    public void writeObject(IFlatfOutput out)
            throws IOException {
        super.writeObject(out);

        for (Entry<String, FieldDoc> field : fields.entrySet()) {
            String fieldName = field.getKey();
            FieldDoc fieldDoc = field.getValue();
            out.sectionBegin("field:" + fieldName);
            fieldDoc.writeObject(out);
            out.sectionEnd();
        }

        for (Entry<String, MethodDoc> method : methods.entrySet()) {
            String methodId = method.getKey();
            MethodDoc methodDoc = method.getValue();
            out.sectionBegin(methodId);
            methodDoc.writeObject(out);
            out.sectionEnd();
        }
    }

}
