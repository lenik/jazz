package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.codegen.JavaSourceWriter;

public class JsonInImpl
        extends FieldsRelatedSourceBuilder {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
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

}
