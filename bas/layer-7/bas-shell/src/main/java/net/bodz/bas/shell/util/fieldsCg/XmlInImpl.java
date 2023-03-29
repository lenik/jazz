package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.codegen.JavaSourceWriter;

public class XmlInImpl
        extends FieldsRelatedSourceBuilder {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
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

}
