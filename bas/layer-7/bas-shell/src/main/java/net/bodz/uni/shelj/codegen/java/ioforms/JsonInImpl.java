package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.fmt.json.IJsonForm;

public class JsonInImpl
        extends FieldsRelatedSourceBuilder {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void jsonIn(JsonObject o, JsonFormOptions opts)");
        out.enterln("        throws ParseException {");

        for (Field field : fields) {
            String fieldName = "this." + field.getName();

            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;

            Class<?> type = field.getType();
            String getType = Primitives.unbox(type).getSimpleName();
            boolean nullable = !type.isPrimitive();

            int modifiers = field.getModifiers();
            boolean isFinal = Modifier.isFinal(modifiers);

            // switch (TypeKind.getTypeId(field.getType())) {
            // case TypeId.BIG_INTEGER:
            // case TypeId.BIG_DECIMAL:
            // case TypeId.DATE:
            // case TypeId.SQL_DATE:
            // case TypeId.STRING:
            // }

            boolean alloc = !isFinal;

            if (IJsonForm.class.isAssignableFrom(type)) {
                out.printf("if (o.containsKey(%s))", keyName);
                if (alloc)
                    out.print(" {");
                out.println();

                if (alloc)
                    out.printf("    %s = new %s();\n", fieldName, type.getSimpleName());

                out.printf("    %s.jsonIn(o.getJsonObject(%s));\n", fieldName, keyName);
                if (alloc)
                    out.println("}");
                continue;
            }

            String getFn = "get" + Strings.ucfirst(getType);
            if (nullable)
                out.printf("%s = o.%s(%s);\n", fieldName, getFn, keyName);
            else
                out.printf("%s = o.%s(%s, %s);\n", fieldName, getFn, keyName, fieldName);
        }

        out.leaveln("}");
    }

}
