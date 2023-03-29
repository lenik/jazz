package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;

public class KConsts
        extends FieldsRelatedSourceBuilder {

    int count;

    public int getCount() {
        return count;
    }

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        // K_ key name consts
        for (Field field : fields) {
            Phrase nam = Phrase.fooBar(field.getName());
            String keyName = "K_" + nam.FOO_BAR;
            out.printf("private static final String %s = \"%s\";\n", keyName, nam.fooBar);
            count++;
        }
    }

}
