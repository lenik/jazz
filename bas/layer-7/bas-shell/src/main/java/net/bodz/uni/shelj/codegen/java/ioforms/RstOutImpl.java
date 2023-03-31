package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;

public class RstOutImpl
        extends FieldsRelatedSourceBuilder {

    public RstOutImpl() {
    }

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
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
