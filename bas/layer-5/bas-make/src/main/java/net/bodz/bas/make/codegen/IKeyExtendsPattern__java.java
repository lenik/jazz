package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class IKeyExtendsPattern__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.type.IKeyExtendsPattern" + inputCount);
    }

    @Override
    public boolean isValid(int inputCount) {
        return inputCount > 0;
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.type;\n");

        out.println();
        out.printf("import net.bodz.bas.make.IParameterType;\n");

        out.println();
        out.printf("public interface IKeyExtendsPattern%d<Param, K%s>\n", //
                inputCount, comma(Naming.vars("T", inputCount)));
        out.enter();
        {
            out.enter();
            {
                out.printf("extends IKeyExtendsPattern<Param, K>,\n");
                out.enter();
                {
                    out.enter();
                    {
                        out.printf("IParameterType<Param> {\n");

                        out.println();
                        out.leave();
                    }
                    out.leave();
                }
                out.leave();
            }
            out.printf("@Override\n");
            out.printf("default Class<?>[] getInterfaces() {\n");
            out.enter();
            {
                out.printf("return new Class<?>[] { %s };\n", Naming.vars("getType", inputCount, "()"));
                out.leave();
            }
            out.printf("}\n");

            out.println();
            out.printf("@SuppressWarnings(\"unchecked\")\n");
            out.printf("@Override\n");
            out.printf("default Param match(Object instance) {\n");
            out.enter();
            {
                for (int i = 1; i <= inputCount; i++)
                    out.printf("T%d type%d = (T%d) instance;\n", i, i, i);
                out.printf("return matchTyped(%s);\n", Naming.vars("type", inputCount));
                out.leave();
            }
            out.printf("}\n");

            out.println();
            for (int i = 1; i <= inputCount; i++)
                out.printf("Class<T%d> getType%d();\n", i, i);

            out.println();
            out.printf("Param matchTyped(%s);\n", Naming.formatVars("T%d type%d", inputCount));

            out.println();
            out.leave();
        }
        out.printf("}\n");
    }

}
