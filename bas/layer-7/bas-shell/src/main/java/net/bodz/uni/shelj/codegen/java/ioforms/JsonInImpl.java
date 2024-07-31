package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.uni.shelj.codegen.java.JavaCodeWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class JsonInImpl
        extends SourceBuilderForMembers {

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void jsonIn(JsonObject o, JsonFormOptions opts)");
        out.enterln("        throws ParseException {");

        for (IMember member : members) {

            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;

            Class<?> type = member.getType();
            String getType = Primitives.unbox(type).getSimpleName();
            boolean nullable = ! type.isPrimitive();

            int modifiers = member.getModifiers();
            boolean isFinal = Modifier.isFinal(modifiers);

            // switch (TypeKind.getTypeId(field.getType())) {
            // case TypeId.BIG_INTEGER:
            // case TypeId.BIG_DECIMAL:
            // case TypeId.DATE:
            // case TypeId.SQL_DATE:
            // case TypeId.STRING:
            // }

            boolean alloc = ! isFinal;

            if (IJsonForm.class.isAssignableFrom(type)) {
                JsonFormTypeInfo info = JsonFormTypeInfo.ofClass(type);
                if (info.hasFrom_JoKey) {
                    out.printLineForJavaSet(member, //
                            "%s.from(o, %s)", type.getSimpleName(), keyName);
                    continue;
                }

                out.printf("if (o.containsKey(%s))", keyName);
                if (alloc) {
                    out.print(" {");
                }

                out.println();
                out.enter();

                if (alloc) {
                    out.printLineForJavaSet(member, "new %s()", type.getSimpleName());
                }

                out.printLineWithJavaGet(member, //
                        "\\?.jsonIn(o.getJsonObject(%s))", keyName);

                if (alloc) {
                    out.leave();
                    out.println("}");
                }
                continue;
            }

            String getFn = "get" + Strings.ucfirst(getType);
            if (nullable)
                out.printLineForJavaSet(member, "o.%s(%s)", getFn, keyName);
            else
                out.printLineForJavaSet(member, "o.%s(%s, %s)", getFn, keyName, member.javaGet());
        }

        out.leaveln("}");
    }

}
