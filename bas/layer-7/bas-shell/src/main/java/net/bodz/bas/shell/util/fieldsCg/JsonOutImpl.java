package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;

public class JsonOutImpl
        extends FieldsRelatedSourceBuilder {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
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

}
