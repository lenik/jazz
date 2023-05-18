package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class RstOutImpl
        extends SourceBuilderForMembers {

    public RstOutImpl() {
    }

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IRstOutput out)");
        out.enterln("        throws IOException, FormatException {");

        for (IMember member : members) {
            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;
            boolean nullable = !member.getType().isPrimitive();

            if (nullable)
                out.enterln("if (" + nam.fooBar + " != null)");
            out.printf("out.attribute(%s, this.%s);\n", keyName, nam.fooBar);
            if (nullable)
                out.leave();
        }

        out.leaveln("}");
    }

}
