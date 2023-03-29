package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.codegen.JavaSourceWriter;

public class RstInImpl
        extends FieldsRelatedSourceBuilder {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
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

//        if (!onlyStrings)
//            out.println("IVariant val = new MutableVariant(data);");

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
                String getFn = "parse" + Strings.ucfirst(getType);
                if (nullable)
                    out.printf("%s = StringFn.%s(data);\n", nam.fooBar, getFn);
                else
                    out.printf("%s = StringFn.%s(data, %s);\n", nam.fooBar, getFn, nam.fooBar);
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

}
