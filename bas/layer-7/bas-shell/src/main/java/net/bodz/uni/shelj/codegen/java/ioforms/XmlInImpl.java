package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.uni.shelj.codegen.java.JavaCodeWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class XmlInImpl
        extends SourceBuilderForMembers {

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void readObject(IElement element)");
        out.enterln("        throws ParseException, LoaderException {");

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

            String getFn = "get" + Strings.ucfirst(getType);
            if (nullable) {
                // out.printf("%s = o.a(%s).%s();\n", nam.fooBar, keyName, getFn);
                out.printLineForJavaSet(member, //
                        "o.a(%s).%s();", keyName, getFn);
            } else {
                // out.printf("%s = o.a(%s).%s(%s);\n", nam.fooBar, keyName, getFn, nam.fooBar);
                out.printLineForJavaSet(member, //
                        "o.a(%s).%s(%s)", keyName, getFn, member.getName());
            }
        }

        out.leaveln("}");
    }

}
