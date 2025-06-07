package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleMakeRuleBuilders__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.fn.SimpleMakeRuleBuilders");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.fn;\n");

        out.println();
        out.printf("import java.util.function.BiConsumer;\n");

        out.println();
        out.printf("import net.bodz.bas.make.IDataTypedKey;\n");
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IMakeRule;\n");

        out.println();
        out.printf("public abstract class SimpleMakeRuleBuilders<S, T extends IKeyData<TK, TT>, TK, TT> {\n");

        out.println();
        out.enter();
        {
            out.printf("public abstract BiConsumer<S, IMakeRule<T, TK, TT>> getApply();\n");

            out.println();
            out.printf("public abstract S getSubject();\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                String typeVars = String.format("T, TK, TT%s", //
                        comma(Naming.typeVars(inputCount, "", "K", "T")));
                String builderTypeVars = "S, " + typeVars;

                out.println();
                out.printf("public ");
                inputTypeParams(out, true, inputCount);
                out.printf("SimpleMakeRule%d.Builder<%s> input(", inputCount, builderTypeVars);
                for (int index = 0; index < inputCount; index++) {
                    String U = Naming.typeVar(inputCount, index);
                    if (index != 0)
                        out.printf(", ");
                    out.printf("IDataTypedKey<%sK, %sT> input%d", U, U, index + 1);
                }
                out.printf(") {\n");
                out.enter();
                {
                    out.printf("return SimpleMakeRule%d.<%s>builder()//\n", inputCount, builderTypeVars);
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf(".apply(getApply()).subject(getSubject())//\n");
                            out.printf(".input(%s);\n", Naming.inputVars(inputCount));
                            out.leave();
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.leave();
        }
        out.printf("}\n");
    }

}
