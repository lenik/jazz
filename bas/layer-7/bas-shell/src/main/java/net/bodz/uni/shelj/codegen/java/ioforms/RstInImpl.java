package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.uni.shelj.codegen.java.JavaCodeWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class RstInImpl
        extends SourceBuilderForMembers {

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.enterln("public IRstHandler getElementHandler() {");
        out.enterln("return new StackRstHandler() {");
        out.println("@Override");
        out.println("public boolean attribute(String name, String data)");
        out.enterln("        throws ParseException, ElementHandlerException {");

        boolean onlyStrings = true;
        for (IMember member : members)
            if (member.getType() != String.class) {
                onlyStrings = false;
                break;
            }

//        if (!onlyStrings)
//            out.println("IVariant val = new MutableVariant(data);");

        out.println("switch (name) {");
        for (IMember member : members) {
            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;
            String getType = Primitives.unbox(member.getType()).getSimpleName();
            boolean nullable = !member.getType().isPrimitive();

            // switch (TypeKind.getTypeId(field.getType())) {
            // case TypeId.BIG_INTEGER:
            // case TypeId.BIG_DECIMAL:
            // case TypeId.DATE:
            // case TypeId.SQL_DATE:
            // case TypeId.STRING:
            // }

            out.enterln("case " + keyName + ":");

            if (member.getType() == String.class) {
                out.printLineForJavaSet(member, "data;");
            } else {
                String getFn = "parse" + Strings.ucfirst(getType);
                if (nullable) {
                    out.printLineForJavaSet(member, //
                            "StringFn.%s(data)", getFn);
                } else {
                    out.printLineForJavaSet(member, //
                            "StringFn.%s(data, %s)", getFn, member.getName());
                }
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
