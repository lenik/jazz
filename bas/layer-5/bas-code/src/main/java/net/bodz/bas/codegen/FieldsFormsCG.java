package net.bodz.bas.codegen;

import java.lang.reflect.Field;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;

public class FieldsFormsCG
        extends AbstractJavaCodegen<Iterable<Field>> {

    Class<?> clazz;
    Iterable<Field> fields;
    boolean jsonForm = true;
    boolean xmlForm = true;

    public FieldsFormsCG(Class<?> clazz, Iterable<Field> fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public static FieldsFormsCG declared(Class<?> clazz, int maxDepth) {
        FieldsFormsCG cg = new FieldsFormsCG(clazz, ReflectQuery//
                .selectDeclaredFields(clazz)//
                .maxDepth(maxDepth) //
                .staticMode(false) //
                .finalMode(false) //
        );
        return cg;
    }

    @Override
    protected String getPackageName() {
        return clazz.getPackage().getName();
    }

    @Override
    protected Iterable<Field> getModel() {
        return fields;
    }

    @Override
    public void generateJavaSource(JavaSourceWriter out, Iterable<Field> model) {
        if (!K_consts(out))
            return;

        if (jsonForm) {
            out.println();
            jsonInImpl(out);
            out.println();
            jsonOutImpl(out);
        }

        if (xmlForm) {
            out.println();
            xmlInImpl(out);
            out.println();
            xmlOutImpl(out);
        }
    }

    boolean K_consts(JavaSourceWriter out) {
        int count = 0;

        // K_ key name consts
        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;
            out.printf("private static final String %s = \"%s\";\n", keyName, nam.fooBar);
            count++;
        }
        return count != 0;
    }

    void jsonInImpl(JavaSourceWriter out) {
        out.println("@Override");
        out.println("public void jsonIn(JsonObject o, JsonFormOptions opts)");
        out.enterln("        throws ParseException {");

        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;
            String getType = Primitives.unbox(field.getType()).getSimpleName();
            boolean nullable = !field.getType().isPrimitive();

            // switch (TypeKind.getTypeId(field.getType())) {
            // case TypeId.BIG_INTEGER:
            // case TypeId.BIG_DECIMAL:
            // case TypeId.DATE:
            // case TypeId.SQL_DATE:
            // case TypeId.STRING:
            // }

            String getFn = "get" + Strings.ucfirst(getType);
            if (nullable)
                out.printf("%s = o.%s(%s);\n", nam.fooBar, getFn, keyName);
            else
                out.printf("%s = o.%s(%s, %s);\n", nam.fooBar, getFn, keyName, nam.fooBar);
        }

        out.lnleave("}");
    }

    void jsonOutImpl(JavaSourceWriter out) {
        out.println("@Override");
        out.println("public void jsonOut(IJsonOut out, JsonFormOptions opts)");
        out.enterln("        throws IOException, FormatException {");

        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;
            if (field.getType().isPrimitive())
                out.printf("out.entry(%s, this.%s);\n", keyName, nam.fooBar);
            else
                out.printf("out.entryNotNull(%s, this.%s);\n", keyName, nam.fooBar);
        }

        out.lnleave("}");
    }

    void xmlInImpl(JavaSourceWriter out) {
        out.println("@Override");
        out.println("public void readObject(IElement element)");
        out.enterln("        throws ParseException, LoaderException {");

        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;
            String getType = Primitives.unbox(field.getType()).getSimpleName();
            boolean nullable = !field.getType().isPrimitive();

            // switch (TypeKind.getTypeId(field.getType())) {
            // case TypeId.BIG_INTEGER:
            // case TypeId.BIG_DECIMAL:
            // case TypeId.DATE:
            // case TypeId.SQL_DATE:
            // case TypeId.STRING:
            // }

            String getFn = "get" + Strings.ucfirst(getType);
            if (nullable)
                out.printf("%s = o.a(%s).%s();\n", nam.fooBar, keyName, getFn);
            else
                out.printf("%s = o.a(%s).%s(%s);\n", nam.fooBar, keyName, getFn, nam.fooBar);
        }

        out.lnleave("}");
    }

    void xmlOutImpl(JavaSourceWriter out) {
        out.println("@Override");
        out.println("public void writeObject(IXmlOutput out)");
        out.enterln("        throws XMLStreamException, FormatException {");

        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;

            if (field.getType().isPrimitive())
                out.printf("out.attribute(%s, this.%s);\n", keyName, nam.fooBar);
            else
                out.printf("out.attributeNotNull(%s, this.%s);\n", keyName, nam.fooBar);
        }

        out.lnleave("}");
    }

}
