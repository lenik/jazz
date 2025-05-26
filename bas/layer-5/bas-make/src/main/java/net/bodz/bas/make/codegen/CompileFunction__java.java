package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class CompileFunction__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.fn.CompileFunction" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("package net.bodz.bas.make.fn;");
        out.println();
        out.println("import net.bodz.bas.make.IKeyData;");
        out.println("import net.bodz.bas.meta.decl.NotNull;");
        out.println();
        out.printf("public interface CompileFunction%d<T extends IKeyData<TK, TT>, TK, TT%s //\n", inputCount, //
                inputCount == 0 ? ">" : ",");
        out.enter();
        {
            out.enter();
            {
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT%s //\n", U, U, U, U, U, //
                            i == inputCount - 1 ? ">" : ",");
                }

                out.println("extends CompileFunction<T> {");
                out.println();
                out.leave();
            }
            out.println("@Override");
            out.println("default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs) {");
            out.enter();
            {
                out.println("return (t, iv) -> {");
                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.println("@SuppressWarnings(\"unchecked\")");
                        out.printf("%s input%d = (%s) iv[%d];\n", //
                                U, i + 1, U, i);
                        out.println();
                    }

                    out.printf("IMakeable%d<TT%s> fn = compile(target%s);\n", //
                            inputCount, //
                            comma(Naming.typeVars(inputCount, "T")), //
                            comma(Naming.inputVars(inputCount)));
                    out.println();
                    out.printf("TT tVal = fn.make(%s);\n", Naming.inputVars(inputCount, ".getData()"));
                    out.println();
                    out.println("t.setData(tVal);");
                    out.leave();
                }
                out.println("};");
                out.leave();
            }
            out.println("}");
            out.println();
            out.printf("IMakeable%d<TT%s> compile(@NotNull T target%s);\n", //
                    inputCount, //
                    comma(Naming.typeVars(inputCount, "T")), //
                    comma(Naming.inputParams(inputCount)));
            out.println();
            out.leave();
        }
        out.println("}");
    }

}
