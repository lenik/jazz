package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleKeyPatternLikeMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String ruleTypeVars = "<T, K, TT" + comma(Naming.typeVars(inputCount, "", "K", "T")) + ">";
        String _TVars = comma(Naming.typeVars(inputCount, "T"));

        out.printf("package net.bodz.bas.make.pattern.template;\n");
        out.println();
        out.printf("import net.bodz.bas.make.CompileException;\n");
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedKeys;\n");
        out.printf("import net.bodz.bas.make.fn.CompileFunction%d;\n", inputCount);
        out.printf("import net.bodz.bas.make.fn.IMakeable%d;\n", inputCount);
        out.printf("import net.bodz.bas.meta.decl.NotNull;\n");
        out.println();
        out.printf("public abstract class SimpleKeyPatternLikeMakeRule%d<Tp extends IKeyPatternLike<Param, K>, Param, K, //\n", inputCount);
        out.enter();
        {
            out.enter();
            {
                out.printf("Keys extends IParameterizedKeys<?, ?>, //\n");
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%ss extends IParameterizedKeys<Param, %sK>, %sK, //\n", U, U, U);
                }
                out.printf("T extends IKeyData<K, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                }
                out.print("> //\n");

                out.printf("implements IKeyPatternLikeMakeRule%d<Tp, Param, K, Keys%s, T, TT%s> {\n", inputCount, //
                        comma(Naming.typeVars(inputCount, "s", "K")), //
                        comma(Naming.typeVars(inputCount, "", "T")) //
                );
                out.println();
                out.leave();
            }
            out.printf("int priority;\n");
            out.printf("Tp pattern;\n");
            out.printf("CompileFunction%d%s fn;\n", inputCount, ruleTypeVars);
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.printf("%ss input%ds;\n", U, i + 1);
            }
            out.println();
            out.printf("public SimpleKeyPatternLikeMakeRule%d(int priority, @NotNull Tp pattern", inputCount);
            out.printf(", @NotNull CompileFunction%d%s fn", inputCount, ruleTypeVars);
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.printf(", @NotNull %ss input%ds", U, i + 1);
            }
            out.printf(") {\n");
            out.enter();
            {
                out.printf("this.priority = priority;\n");
                out.printf("this.pattern = pattern;\n");
                out.printf("this.fn = fn;\n");
                for (int i = 0; i < inputCount; i++) {
                    out.printf("this.input%ds = input%ds;\n", i + 1, i + 1);
                }
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("@Override\n");
            out.printf("public int getPriority() {\n");
            out.enter();
            {
                out.printf("return priority;\n");
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("@Override\n");
            out.printf("public Tp getPattern() {\n");
            out.enter();
            {
                out.printf("return pattern;\n");
                out.leave();
            }
            out.printf("}\n");

            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.println();
                out.printf("@Override\n");
                out.printf("public %ss getInput%d() {\n", U, i + 1);
                out.enter();
                {
                    out.printf("return input%ds;\n", i + 1);
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("@Override\n");
            out.printf("public IMakeable%d<TT%s> compile(@NotNull T target%s)\n", inputCount, _TVars, //
                    comma(Naming.inputParams(inputCount)));
            out.enter();
            {
                out.enter();
                {
                    out.printf("throws CompileException {\n");
                    out.leave();
                }
                out.printf("return fn.compile(target%s);\n", //
                        comma(Naming.inputVars(inputCount)));
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("@SuppressWarnings(\"unchecked\")\n");
            out.printf("public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //\n");
            out.enter();
            {
                out.enter();
                {

                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IParameterizedKeys<Param, %sK>, %sK, //\n", U, U, U);
                    }
                    out.printf("T extends IKeyData<K, TT>, TT");

                    for (int i = 0; i < inputCount; i++) {
                        out.print(", //\n");
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%s extends IKeyData<%sK, %sT>, %sT\n", U, U, U, U);
                    }
                    out.print("> {\n");
                    out.println();
                    out.leave();
                }
                out.printf("protected int priority;\n");
                out.printf("protected Tp pattern;\n");
                out.printf("protected CompileFunction%d%s fn;\n", inputCount, ruleTypeVars);
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("protected %ss input%ds;\n", U, i + 1);
                }
                out.println();
                out.printf("public self_t priority(int priority) {\n");
                out.enter();
                {
                    out.printf("this.priority = priority;\n");
                    out.printf("return (self_t) this;\n");
                    out.leave();
                }
                out.printf("}\n");
                out.println();
                out.printf("public self_t pattern(@NotNull Tp pattern) {\n");
                out.enter();
                {
                    out.printf("this.pattern = pattern;\n");
                    out.printf("return (self_t) this;\n");
                    out.leave();
                }
                out.printf("}\n");

                out.println();
                out.printf("public self_t fn(@NotNull CompileFunction%d%s fn) {\n", inputCount, ruleTypeVars);
                out.enter();
                {
                    out.printf("this.fn = fn;\n");
                    out.printf("return (self_t) this;\n");
                    out.leave();
                }
                out.printf("}\n");

                out.println();
                out.printf("@SuppressWarnings(\"unchecked\")\n");
                out.printf("public self_t input(@NotNull IParameterizedKeys<?, ?>... inputss) {\n");
                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("this.input%ds = (%ss) inputss[%d];\n", i + 1, U, i);
                    }
                    out.printf("return (self_t) this;\n");
                    out.leave();
                }
                out.printf("}\n");

                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.println();
                    out.printf("public self_t input%d(@NotNull %ss input%ds) {\n", i + 1, U, i + 1);
                    out.enter();
                    {
                        out.printf("this.input%ds = input%ds;\n", i + 1, i + 1);
                        out.printf("return (self_t) this;\n");
                        out.leave();
                    }
                    out.printf("}\n");
                }

                out.println();
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.leave();
        }
        out.printf("}\n");

    }

}
