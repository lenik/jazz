package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleKeyPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String compileFnTypeVars = "T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T"));
        String typeVars = String.format("Tp, Param, TK%s, T, TT%s", //
                modeUs ? comma(Naming.typeVars(inputCount, "s")) : "", //
                comma(Naming.typeVars(inputCount, "", "K", "T")));
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
        out.printf("public class SimpleKeyPatternMakeRule%d<Tp extends IKeyPattern<Param, TK>, Param, TK, ", inputCount);
        out.enter();
        {
            out.enter();
            {
                if (modeUs)
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IParameterizedKey<Param, %sK>, //\n", U, U);
                    }
                out.printf("T extends IKeyData<TK, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends SimpleKeyPatternLikeMakeRule%d<Tp, Param, TK, IParameterizedKey<?, ?>%s, T, TT%s>\n", //
                        inputCount, //
                        modeUs ? comma(Naming.typeVars(inputCount, "s")) //
                                : comma(Naming.typeVars(inputCount, U -> String.format("IParameterizedKey<Param, %sK>", U))), //
                        comma(Naming.typeVars(inputCount, "", "K", "T")));
                out.printf("implements IKeyPatternMakeRule%d<%s> {\n", inputCount, typeVars);
                out.leave();
            }

            out.println();
            out.printf("public SimpleKeyPatternMakeRule%d(int priority, @NotNull Tp pattern", inputCount);
            out.printf(", @NotNull CompileFunction%d<%s> fn", inputCount, compileFnTypeVars);
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                if (modeUs)
                    out.printf(", @NotNull %ss input%ds", U, i + 1);
                else
                    out.printf(", @NotNull IParameterizedKey<Param, %sK> input%ds", U, i + 1);
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
            out.printf("public static <S, Tp extends IKeyPattern<Param, TK>, Param, TK, //\n");
            out.enter();
            {
                out.enter();
                {
                    if (modeUs)
                        for (int i = 0; i < inputCount; i++) {
                            String U = Naming.typeVar(inputCount, i);
                            out.printf("%ss extends IParameterizedKey<Param, %sK>, //\n", U, U);
                        }
                    out.printf("T extends IKeyData<TK, TT>, TT");
                    for (int i = 0; i < inputCount; i++) {
                        out.print(", //\n");
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
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
            out.printf("public static class Builder<S, Tp extends IKeyPattern<Param, TK>, Param, TK, //\n");
            out.enter();
            {
                out.enter();
                {
                    if (modeUs)
                        for (int i = 0; i < inputCount; i++) {
                            String U = Naming.typeVar(inputCount, i);
                            out.printf("%ss extends IParameterizedKey<Param, %sK>, //\n", U, U);
                        }
                    out.printf("T extends IKeyData<TK, TT>, TT");
                    for (int i = 0; i < inputCount; i++) {
                        out.print(", //\n");
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
                    }
                    out.print("> //\n");

                    out.printf("extends SimpleKeyPatternLikeMakeRule%d.Builder<Builder<%s>, //\n", inputCount, builderTypeVars);
                    out.printf("Tp, Param, TK%s, T, TT%s> {\n", //
                            modeUs ? comma(Naming.typeVars(inputCount, "s")) //
                                    : comma(Naming.typeVars(inputCount, U -> String.format("IParameterizedKey<Param, %sK>", U))), //
                            comma(Naming.typeVars(inputCount, "", "K", "T")));
                    out.leave();
                }

                String ruleType = "IKeyPatternMakeRule<Tp, Param, TK, T, TT>";
                applySubject(out, ruleType, builderTypeVars);

                out.println();
                out.printf("public SimpleKeyPatternMakeRule%d<%s> build() {\n", inputCount, typeVars);
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
                    out.printf("return new SimpleKeyPatternMakeRule%d<>(priority, pattern, fn%s);\n", //
                            inputCount, //
                            comma(Naming.inputVars(inputCount, "s")));
                    out.leave();
                }
                out.printf("}\n");
                out.leave();
            }

            makeCompileFn(out);

            out.println();
            out.printf("}\n");
            out.println();
            out.leave();
        }
        out.printf("}\n");
    }

}
