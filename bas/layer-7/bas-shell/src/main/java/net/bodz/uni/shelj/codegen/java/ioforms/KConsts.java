package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.uni.shelj.codegen.java.JavaCodeWriter;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class KConsts
        extends SourceBuilderForMembers {

    int count;

    public int getCount() {
        return count;
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        // K_ key name consts
        for (IMember member : members) {
            Phrase nam = Phrase.fooBar(member.getName());
            String keyName = "K_" + nam.FOO_BAR;
            out.printf("private static final String %s = \"%s\";\n", keyName, nam.fooBar);
            count++;
        }
    }

}
