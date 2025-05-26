package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class IMakeable__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.fn.IMakeable" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("package net.bodz.bas.make.fn;");
        out.println();
        out.println("import net.bodz.bas.make.MakeException;");
        out.println();
        out.println("@FunctionalInterface");
        out.printf("public interface IMakeable%d<T%s> {\n", inputCount, //
                comma(Naming.typeVars(inputCount)));
        out.println();
        out.enter();
        {
            out.printf("T make(%s)\n", Naming.inputParams(inputCount));
            out.enter();
            {
                out.enter();
                {
                    out.println("throws MakeException;");
                    out.println();
                    out.leave();
                }
                out.leave();
            }
            out.leave();
        }
        out.println("}");
    }

}
