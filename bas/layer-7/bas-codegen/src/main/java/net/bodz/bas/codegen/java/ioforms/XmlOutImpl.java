package net.bodz.bas.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.codegen.java.member.IMember;

public class XmlOutImpl
        extends SourceBuilderForMembers {

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IXmlOutput out)");
        out.enterln("        throws XMLStreamException, FormatException {");

        for (IMember member : members) {
            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;

            if (member.getType().isPrimitive()) {
                out.printLineWithJavaGet(member, //
                        "out.attribute(%s, \\?);", keyName);
            } else {
                out.printLineWithJavaGet(member, //
                        "out.attributeNotNull(%s, \\?);", keyName);
            }
        }

        out.leaveln("}");
    }

}
