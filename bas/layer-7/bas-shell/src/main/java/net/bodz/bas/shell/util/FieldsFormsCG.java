package net.bodz.bas.shell.util;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.codegen.IJavaCodegen;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Generate fields with various serialization forms.
 */
@ProgramName("mkfields")
public class FieldsFormsCG
        extends BasicCLI
        implements
            IJavaCodegen<Iterable<Field>> {

    /**
     * Specify the class to read.
     *
     * @option -c --class =FQCN
     */
    Class<?> clazz;

    /**
     * Select members declared on the class and specified depth number of superclasses.
     *
     * (default public members)
     *
     * @option -d
     */
    boolean declaredOnly;

    /**
     * The max depth of inheritance to find declared fields. (default 1)
     *
     * @option -D --max-depth =NUM
     */
    int maxDepth = 1;

    /**
     * Select (public) bean properties.
     *
     * @option -p --properties
     */
    boolean beanProperties;

    /**
     * Generate implementation for IJsonForm.
     *
     * @option -j --json
     */
    boolean jsonForm;

    /**
     * Generate implementation for IRstForm.
     *
     * @option -r --rst
     */
    boolean rstForm;

    /**
     * Generate implementation for IXmlForm.
     *
     * @option -x --xml
     */
    boolean xmlForm;

    Iterable<Field> fields;

    public FieldsFormsCG() {
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        if (clazz == null) {
            if (args.length == 0)
                throw new IllegalArgumentException("expect classname");
            String fqcn = args[0];
            clazz = Class.forName(fqcn);
        }

        if (declaredOnly) {
            fields = ReflectQuery//
                    .selectDeclaredFields(clazz)//
                    .maxDepth(maxDepth) //
                    .staticMode(false) //
                    .finalMode(false);
        } else {
            fields = ReflectQuery.selectFields(clazz) //
                    .staticMode(false) //
                    .finalMode(false);
        }

        String packageName = clazz.getPackage().getName();
        BCharOut buf = new BCharOut();
        ITreeOut out = buf.indented();

        out.enterln("class " + clazz.getSimpleName() + "Fields " + "{");
        out.println();
        JavaSourceWriter javaWriter = new JavaSourceWriter(packageName, out);
        generateJavaSource(javaWriter, fields);
        out.println();
        out.leaveln("}");

        out = Stdio.out.indented();
        javaWriter.im.dump(out);
        out.println();
        out.println(buf);
    }

    public static void main(String[] args)
            throws Exception {
        new FieldsFormsCG().execute(args);
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

        if (rstForm) {
            out.println();
            rstInImpl(out);
            out.println();
            rstOutImpl(out);
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

        out.leaveln("}");
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

        out.leaveln("}");
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

        out.leaveln("}");
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

        out.leaveln("}");
    }

    class K
            implements
                IRstForm {

        @Override
        public void writeObject(IRstOutput out)
                throws IOException, FormatException {
            IRstForm.super.writeObject(out);
        }

        @Override
        public IRstHandler getElementHandler() {
            return IRstForm.super.getElementHandler();
        }

    }

    void rstInImpl(JavaSourceWriter out) {
        out.println("@Override");
        out.enterln("public IRstHandler getElementHandler() {");
        out.enterln("return new StackRstHandler() {");
        out.println("@Override");
        out.println("public boolean attribute(String name, String data)");
        out.enterln("        throws ParseException, ElementHandlerException {");

        boolean onlyStrings = true;
        for (Field field : fields)
            if (field.getType() != String.class) {
                onlyStrings = false;
                break;
            }

        if (!onlyStrings)
            out.println("IVariant val = new MutableVariant(data);");

        out.println("switch (name) {");
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

            out.enterln("case " + keyName + ":");

            if (field.getType() == String.class) {
                out.printf("%s = data;\n", nam.fooBar);
            } else {
                String getFn = "get" + Strings.ucfirst(getType);
                if (nullable)
                    out.printf("%s = val.%s();\n", nam.fooBar, getFn);
                else
                    out.printf("%s = val.%s(%s);\n", nam.fooBar, getFn, nam.fooBar);
            }

            out.println("return true;");
            out.leave();
            out.println();
        }

        out.enterln("default:");
        out.lnleave("return false;");

        out.println("}"); // switch
        out.leaveln("}"); // attribute()
        out.leaveln("};"); // return new StackRstHandler
        out.leaveln("}"); // getElementHandler()
    }

    void rstOutImpl(JavaSourceWriter out) {
        out.println("@Override");
        out.println("public void writeObject(IRstOutput out)");
        out.enterln("        throws IOException, FormatException {");

        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;
            boolean nullable = !field.getType().isPrimitive();

            if (nullable)
                out.enterln("if (" + nam.fooBar + " != null)");
            out.printf("out.attribute(%s, this.%s);\n", keyName, nam.fooBar);
            if (nullable)
                out.leave();
        }

        out.leaveln("}");
    }

}
