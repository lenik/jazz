package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
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
            Class<?> type = member.getType();
            if (type.isPrimitive()) {
                String entryFn = "entry";
                switch (TypeKind.getTypeId(type)) {
                case TypeId._byte:
                case TypeId._short:
                case TypeId._int:
                case TypeId._long:
                case TypeId._float:
                case TypeId._double:
                    entryFn = "entryNot0";
                    break;
                case TypeId._boolean:
                    entryFn = "entryTrue";
                    break;
                }
                out.printLineWithJavaGet(member, //
                        "out.%s(%s, \\?);", entryFn, keyName);
            } else {
                out.printLineWithJavaGet(member, //
                        "out.entryNotNull(%s, \\?);", keyName);
            }
        }

        out.leaveln("}");
    }

}
