package net.bodz.bas.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.codegen.java.member.IMember;

public class JsonInImpl
        extends SourceBuilderForMembers {

    boolean oldStyle = false;

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void jsonIn(JsonObject o, JsonFormOptions opts)");
        out.enterln("        throws ParseException {");

        if (IJsonForm.class.isAssignableFrom(members.clazz.getSuperclass()))
            out.println("super.jsonIn(o, opts);");

        for (IMember member : members) {

            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;

            Class<?> type = member.getType();
            String getType = Primitives.unbox(type).getSimpleName();
            boolean nullable = !type.isPrimitive();

            int modifiers = member.getModifiers();
            boolean isFinal = Modifier.isFinal(modifiers);

            // switch (TypeKind.getTypeId(field.getType())) {
            // case TypeId.BIG_INTEGER:
            // case TypeId.BIG_DECIMAL:
            // case TypeId.DATE:
            // case TypeId.SQL_DATE:
            // case TypeId.STRING:
            // }

            if (IJsonForm.class.isAssignableFrom(type)) {
                JsonFormTypeInfo info = JsonFormTypeInfo.ofClass(type);
                if (info.hasFrom_JoKey) {
                    out.printLineForJavaSet(member, //
                            "%s.from(o, %s)", type.getSimpleName(), keyName);
                    continue;
                }

                if (isFinal) {
                    out.printf("if (o.containsKey(%s))", keyName);
                    out.println();
                    out.enter();
                    out.printLineWithJavaGet(member, //
                            "\\?.jsonIn(o.getJsonObject(%s))", keyName);
                    out.leave();
                } else {
                    if (oldStyle) {
                        out.printf("if (o.containsKey(%s))", keyName);
                        out.printLineForJavaSet(member, //
                                "%s.from(o, %s)", type.getSimpleName(), keyName);
                        out.print(" {");
                        out.println();
                        out.enter();
                        out.printLineForJavaSet(member, "new %s()", type.getSimpleName());
                        out.printLineWithJavaGet(member, //
                                "\\?.jsonIn(o.getJsonObject(%s))", keyName);
                        out.leave();
                        out.println("}");
                    } else {
                        if (nullable)
                            out.printLineForJavaSet(member, "o.getObject(%s, %s::new)", keyName, type.getSimpleName());
                        else
                            out.printLineForJavaSet(member, "o.getObject(%s, %s, %s::new)", keyName, member.javaGet(), type.getSimpleName());
                    }
                }
                continue;
            }

            if (type.isEnum()) {
                if (nullable)
                    out.printLineForJavaSet(member, "o.getEnum(%s.class, %s)", //
                            out.importName(type), keyName);
                else
                    out.printLineForJavaSet(member, "o.getEnum(%s.class, %s, %s)", //
                            out.importName(type), keyName, member.javaGet());
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
