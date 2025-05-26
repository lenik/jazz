package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class IMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.fn.IMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("package net.bodz.bas.make.fn;");
        out.println();
        out.println("import net.bodz.bas.make.IDataTypedKey;");
        out.println("import net.bodz.bas.make.IKeyData;");
        out.println("import net.bodz.bas.make.IMakeRule;");
        out.println("import net.bodz.bas.make.MakeException;");
        out.println("import net.bodz.bas.meta.decl.NotNull;");
        out.println();
        out.printf("public interface IMakeRule%d<T extends IKeyData<TK, TT>, TK, TT%s //\n", inputCount, //
                inputCount == 0 ? ">" : ",");
        out.enter();
        {
            out.enter();
            {
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
                    if (i != inputCount - 1)
                        out.println(", //");
                    else
                        out.println(">");
                }
                out.println("extends IMakeRule<T>");
                out.leave();
            }
            out.leave();
        }
        out.println("{");
        out.println();
        out.enter();
        {
            out.println("@Override");
            out.println("default IDataTypedKey<?, ?>[] getInputs() {");
            out.enter();
            {
                out.print("return new IDataTypedKey<?, ?>[] { ");
                for (int i = 0; i < inputCount; i++) {
                    if (i != 0)
                        out.print(", ");
                    out.printf("getInput%d()", i + 1);
                }
                out.println(" };");
                out.leave();
            }
            out.println("}");
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.println();
                out.printf("IDataTypedKey<%sK, %sT> getInput%d();\n", U, U, i + 1);
            }
            out.println();
            out.println("@Override");
            out.println("default void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)");
            out.enter();
            {
                out.enter();
                {
                    out.println("throws MakeException {");
                    out.leave();
                }
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.println("@SuppressWarnings(\"unchecked\")");
                    out.printf("%s input%d = (%s) inputs[%d];\n", U, i + 1, U, i);
                }
                out.println();
                out.printf("make(target%s);\n", //
                        comma(Naming.inputVars(inputCount)));
                out.leave();
            }
            out.println("}");
            out.println();
            out.printf("default void make(@NotNull T target%s)\n", //
                    comma(Naming.inputParams(inputCount, "", "@NotNull ")));
            out.enter();
            {
                out.enter();
                {
                    out.println("throws MakeException {");
                    out.leave();
                }
                out.printf("TT targetData = make(%s);\n", Naming.inputVars(inputCount, ".getData()"));
                out.println("target.setData(targetData);");
                out.leave();
            }
            out.println("}");
            out.println();
            out.printf("TT make(%s)\n", Naming.inputParams(inputCount, "T"));
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
