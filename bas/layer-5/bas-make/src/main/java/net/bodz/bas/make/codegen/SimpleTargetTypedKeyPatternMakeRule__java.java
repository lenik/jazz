package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleTargetTypedKeyPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String ruleTypeVars = "<T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T")) + ">";
        String typeVars = String.format("Tp, Param, TK%s, T, TT%s", //
                comma(Naming.typeVars(inputCount, "s", "K")), //
                comma(Naming.typeVars(inputCount, "", "T")));
        String builderTypeVars = "S, " + typeVars;

        out.printf("package net.bodz.bas.make.pattern.key;\n");
        out.println();
        out.printf("import java.util.function.BiConsumer;\n");
        out.println();
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedKey;\n");
        out.printf("import net.bodz.bas.make.fn.CompileFunction%d;\n", inputCount);
        out.printf("import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule%d;\n", inputCount);
        out.printf("import net.bodz.bas.meta.decl.NotNull;\n");
        out.println();
        out.printf("public class SimpleTargetTypedKeyPatternMakeRule%d<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, ", inputCount);
        out.enter();
        {
            out.enter();
            {
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%ss extends IParameterizedKey<Param, %sK>, %sK, //\n", U, U, U);
                }
                out.printf("T extends IKeyData<TK, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends SimpleKeyPatternLikeMakeRule%d<Tp, Param, TK, IParameterizedKey<?, ?>%s, T, TT%s>\n", //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "s", "K")), //
                        comma(Naming.typeVars(inputCount, "", "T")));
                out.printf("implements ITargetTypedKeyPatternMakeRule%d<%s> {\n", inputCount, typeVars);
                out.println();
                out.leave();
            }
            out.printf("public SimpleTargetTypedKeyPatternMakeRule%d(int priority, @NotNull Tp pattern", inputCount);
            out.printf(", @NotNull CompileFunction%d%s fn", inputCount, ruleTypeVars);
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.printf(", @NotNull %ss input%ds", U, i + 1);
            }
            out.print(") {\n");
            out.enter();
            {
                out.printf("super(priority, pattern, fn%s);\n", //
                        comma(Naming.inputVars(inputCount, "s")));
                out.leave();
            }
            out.printf("}\n");

            out.println();
            out.printf("public static <S, Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //\n");
            out.enter();
            {
                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IParameterizedKey<Param, %sK>, %sK, //\n", U, U, U);
                    }
                    out.printf("T extends IKeyData<TK, TT>, TT");
                    for (int i = 0; i < inputCount; i++) {
                        out.print(", //\n");
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                    }
                    out.print("> //\n");
                    out.leave();
                }
                out.leave();
            }
            out.printf("Builder<%s> builder() {\n", builderTypeVars);
            out.enter();
            {
                out.printf("return new Builder<>();\n");
                out.leave();
            }
            out.printf("}\n");

            out.println();
            out.printf("public static class Builder<S, Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //\n");
            out.enter();
            {
                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IParameterizedKey<Param, %sK>, %sK, //\n", U, U, U);
                    }
                    out.printf("T extends IKeyData<TK, TT>, TT");
                    for (int i = 0; i < inputCount; i++) {
                        out.print(", //\n");
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                    }
                    out.print("> //\n");

                    out.printf("extends SimpleKeyPatternLikeMakeRule%d.Builder<Builder<%s>, //\n", inputCount, builderTypeVars);
                    out.printf("%s> {\n", typeVars);
                    out.leave();
                }

                String ruleType =  "ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>";
                applySubject(out, ruleType, builderTypeVars);

                out.println();
                out.printf("public SimpleTargetTypedKeyPatternMakeRule%d<%s> build() {\n", inputCount, typeVars);
                out.enter();
                {
                    out.printf("if (pattern == null)\n");
                    out.enter();
                    {
                        out.printf("throw new NullPointerException(\"pattern\");\n");
                        out.leave();
                    }
                    out.printf("if (fn == null)\n");
                    out.enter();
                    {
                        out.printf("throw new NullPointerException(\"fn\");\n");
                        out.leave();
                    }

                    for (int i = 0; i < inputCount; i++) {
                        out.printf("if (input%ds == null)\n", i + 1);
                        out.enter();
                        {
                            out.printf("throw new NullPointerException(\"input%ds\");\n", i + 1);
                            out.leave();
                        }
                    }
                    out.printf("return new SimpleTargetTypedKeyPatternMakeRule%d<>(priority, pattern, fn%s);\n", //
                            inputCount, //
                            comma(Naming.inputVars(inputCount, "s")));
                    out.leave();
                }
                out.printf("}\n");

                makeCompileFn(out);

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
