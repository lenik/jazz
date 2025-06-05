package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleDataTypedTargetPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.target.SimpleDataTypedTargetPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String compileFnTypeVars = "T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T"));
        String typeVars = String.format("Tp, Param, TK%s, T, TT%s", //
                comma(Naming.typeVars(inputCount, "s", "K")), //
                comma(Naming.typeVars(inputCount, "", "T")) //
        );
        String builderTypeVars = "S, " + typeVars;

        out.printf("package net.bodz.bas.make.pattern.dtkey;\n");
        out.println();
        out.printf("import java.util.function.BiConsumer;\n");
        out.println();
        out.printf("import net.bodz.bas.make.fn.CompileFunction%d;\n", inputCount);
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule%d;\n", inputCount);
        out.printf("import net.bodz.bas.meta.decl.NotNull;\n");

        out.println();
        out.printf("public class SimpleDataTypedTargetPatternMakeRule%d<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //\n", inputCount);
        out.enter();
        {
            out.enter();
            {
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%ss extends IDataTypedParameterizedTarget<Param, %sK, %sT>, %sK, //\n", U, U, U, U);
                }
                out.printf("T extends IKeyData<TK, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends SimpleTargetPatternLikeMakeRule%d<Tp, Param, TK, IDataTypedParameterizedTarget<?, ?, ?>%s, T, TT%s>\n", //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "s", "K")), //
                        comma(Naming.typeVars(inputCount, "", "T")) //
                );
                out.printf("implements IDataTypedTargetPatternMakeRule%d<%s> {\n", inputCount, typeVars);
                out.leave();
            }

            out.println();
            out.printf("public SimpleDataTypedTargetPatternMakeRule%d(int priority, @NotNull Tp pattern", inputCount);
            out.printf(", @NotNull CompileFunction%d<%s> fn", inputCount, compileFnTypeVars);
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
            out.printf("public static <S, Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //\n");
            out.enter();
            {
                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IDataTypedParameterizedTarget<Param, %sK, %sT>, %sK, //\n", U, U, U, U);
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
            out.printf("public static class Builder<S, Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //\n");
            out.enter();
            {
                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IDataTypedParameterizedTarget<Param, %s, %sK, %sT>, %sK, //\n", U, U, U, U, U);
                    }
                    out.printf("T extends IKeyData<TK, TT>, TT");

                    for (int i = 0; i < inputCount; i++) {
                        out.print(", //\n");
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                    }
                    out.print("> //\n");

                    out.printf("extends SimpleTargetPatternLikeMakeRule%d.Builder<Builder<%s>, //\n", inputCount, builderTypeVars);
                    out.printf("%s> {\n", typeVars);
                    out.leave();
                }

                String ruleType = "IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT>";
                applySubject(out, ruleType, builderTypeVars);

                out.println();
                out.printf("public SimpleDataTypedTargetPatternMakeRule%d<%s> build() {\n", inputCount, typeVars);
                out.enter();
                {
                    out.printf("if (pattern == null)\n");
                    out.enter();
                    {
                        out.printf("throw new NullPointerException(\"pattern\");\n");
                        out.leave();
                    }
                    out.printf("if (inputs == null)\n");
                    out.enter();
                    {
                        out.printf("inputs = new IDataTypedParameterizedTarget<?, ?, ?, ?>[%d];\n", 0);
                        out.leave();
                    }
                    out.printf("if (fn == null)\n");
                    out.enter();
                    {
                        out.printf("throw new NullPointerException(\"fn\");\n");
                        out.leave();
                    }
                    out.printf("return new SimpleDataTypedTargetPatternMakeRule<>(priority, pattern, inputs, fn);\n");
                    out.leave();
                }
                out.printf("}\n");

                out.println();
                out.printf("public SimpleDataTypedTargetPatternMakeRule%d<%s> build() {\n", inputCount, builderTypeVars);
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
                    out.printf("return new SimpleDataTypedTargetPatternMakeRule%d<>(priority, pattern, fn%s);\n", //
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
