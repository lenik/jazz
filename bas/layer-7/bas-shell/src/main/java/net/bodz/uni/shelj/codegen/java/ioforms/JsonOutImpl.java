package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.uni.shelj.codegen.java.JavaCodeWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class JsonOutImpl
        extends SourceBuilderForMembers {

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void jsonOut(IJsonOut out, JsonFormOptions opts)");
        out.enterln("        throws IOException, FormatException {");
        out.println("super.jsonOut(out, opts);");

        for (IMember member : members) {
            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;
            if (member.getType().isPrimitive())
                out.printLineWithJavaGet(member, //
                        "out.entry(%s, \\?);", keyName);
            else
                out.printLineWithJavaGet(member, //
                        "out.entryNotNull(%s, \\?);", keyName);
        }

        out.leaveln("}");
    }

}
