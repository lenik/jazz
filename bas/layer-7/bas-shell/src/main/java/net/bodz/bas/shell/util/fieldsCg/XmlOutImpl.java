package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;

public class XmlOutImpl
        extends FieldsRelatedSourceBuilder {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IXmlOutput out)");
        out.enterln("        throws XMLStreamException, FormatException {");

        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;

            if (field.getType().isPrimitive())
                out.printf("out.attribute(%s, this.%s);\n", keyName, nam.fooBar);
            else
                out.printf("out.attributeNotNull(%s, this.%s);\n", keyName, nam.fooBar);
        }

        out.leaveln("}");
    }

}
