package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

public class Function__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.fn.Function" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("package net.bodz.bas.make.fn;");
        out.println();
        out.printf("public interface Function%d<%sR> {\n", inputCount, //
                Naming.typeVars_(inputCount));
        out.enter();
        {
            out.println();
            out.printf("R apply(%s);\n", Naming.inputParams(inputCount));

            out.leave();
        }
        out.println();
        out.println("}");
    }

}
