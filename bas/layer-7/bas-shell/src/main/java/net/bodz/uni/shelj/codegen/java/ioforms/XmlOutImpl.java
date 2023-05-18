package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class XmlOutImpl
        extends SourceBuilderForMembers {

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IXmlOutput out)");
        out.enterln("        throws XMLStreamException, FormatException {");

        for (IMember member : members) {
            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;

            if (member.getType().isPrimitive())
                out.printf("out.attribute(%s, this.%s);\n", keyName, nam.fooBar);
            else
                out.printf("out.attributeNotNull(%s, this.%s);\n", keyName, nam.fooBar);
        }

        out.leaveln("}");
    }

}
