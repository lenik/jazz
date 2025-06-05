package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleMakeRuleBuilders__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.fn.SimpleMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String typeVars = "T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T"));
        String _TVars = comma(Naming.typeVars(inputCount, "T"));

        out.printf("package net.bodz.bas.make.fn;\n");
        out.println();

        out.printf("import net.bodz.bas.make.IDataTypedKey;\n");
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.MakeException;\n");
        out.printf("import net.bodz.bas.make.SimpleMakeRule;\n");
        out.printf("import net.bodz.bas.meta.decl.NotNull;\n");
        out.println();
        out.printf("public class SimpleMakeRule%d<T extends IKeyData<TK, TT>, TK, TT%s //\n", inputCount, //
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
                out.printf("implements IMakeRule%d<%s> {\n", inputCount, typeVars);
                out.println();
                out.leave();
            }

            out.printf("int priority;\n");
            out.printf("final Class<? extends T> targetType;\n");
            out.printf("final Class<? extends TK> keyType;\n");
            out.printf("final Class<? extends TT> dataType;\n");
            out.printf("IMakeable%d<TT%s> fn;\n", inputCount, _TVars);
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.printf("IDataTypedKey<%sK, %sT> input%d;\n", U, U, i + 1);
            }
            out.println();
            out.printf("public SimpleMakeRule%d(int priority", inputCount);
            out.printf(", @NotNull Class<? extends T> targetType");
            out.printf(", @NotNull Class<? extends TK> keyType");
            out.printf(", @NotNull Class<? extends TT> dataType");
            out.printf(", @NotNull IMakeable%d<TT%s> fn", inputCount, _TVars);
            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.printf(", @NotNull IDataTypedKey<%sK, %sT> input%d", U, U, i + 1);
            }
            out.printf(") {\n");
            out.enter();
            {
                out.printf("this.priority = priority;\n");
                out.printf("this.targetType = targetType;\n");
                out.printf("this.keyType = keyType;\n");
                out.printf("this.dataType = dataType;\n");
                out.printf("this.fn = fn;\n");
                for (int i = 0; i < inputCount; i++) {
                    out.printf("this.input%d = input%d;\n", i + 1, i + 1);
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
            out.printf("@NotNull\n");
            out.printf("@Override\n");
            out.printf("public Class<? extends T> getTargetType() {\n");
            out.enter();
            {
                out.printf("return targetType;\n");
                out.leave();
            }
            out.printf("}\n");

            out.println();
            out.printf("@NotNull\n");
            out.printf("@Override\n");
            out.printf("public Class<? extends TK> getKeyType() {\n");
            out.enter();
            {
                out.printf("return keyType;\n");
                out.leave();
            }
            out.printf("}\n");

            out.println();
            out.printf("@NotNull\n");
            out.printf("@Override\n");
            out.printf("public Class<? extends TT> getDataType() {\n");
            out.enter();
            {
                out.printf("return dataType;\n");
                out.leave();
            }
            out.printf("}\n");

            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.println();
                out.printf("@Override\n");
                out.printf("public IDataTypedKey<%sK, %sT> getInput%d() {\n", U, U, i + 1);
                out.enter();
                {
                    out.printf("return input%d;\n", i + 1);
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("@Override\n");
            out.printf("public TT make(%s)\n", Naming.inputParams(inputCount, "T"));
            out.enter();
            {
                out.enter();
                {
                    out.printf("throws MakeException {\n");
                    out.leave();
                }
                out.printf("return fn.make(%s);\n", Naming.inputVars(inputCount));
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("public static <S, T extends IKeyData<TK, TT>, TK, TT%s //\n", //
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
                    out.leave();
                }
                out.leave();
            }
            String builderType = "Builder<S, " + typeVars + ">";
            out.printf("%s builder() {\n", builderType);
            out.enter();
            {
                out.printf("return new Builder<>();\n");
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT%s //\n",//
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
                    out.printf("extends SimpleMakeRule.AbstractBuilder<Builder<S, %s>, S, T, TK, TT> { \n", typeVars);
                    out.leave();
                }

                out.println();
                out.printf("IMakeable%d<TT%s> fn;\n", inputCount, _TVars);
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("IDataTypedKey<%sK, %sT> input%d;\n", U, U, i + 1);
                }

                out.println();
                out.printf("public %s fn(@NotNull IMakeable%d<TT%s> fn) {\n", builderType, inputCount, _TVars);
                out.enter();
                {
                    out.printf("this.fn = fn;\n");
                    out.printf("return this;\n");
                    out.leave();
                }
                out.printf("}\n");

                out.println();
                out.printf("public %s input(", builderType);
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    if (i != 0)
                        out.print(", ");
                    out.printf("@NotNull IDataTypedKey<%sK, %sT> input%d", U, U, i + 1);
                }
                out.printf(") {\n");

                out.enter();
                {
                    for (int i = 0; i < inputCount; i++) {
                        out.printf("this.input%d = input%d;\n", i + 1, i + 1);
                    }
                    out.printf("return this;\n");
                    out.leave();
                }
                out.printf("}\n");

                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.println();
                    out.printf("public %s input%d(@NotNull IDataTypedKey<%sK, %sT> input%d) {\n", builderType, i + 1, U, U, i + 1);
                    out.enter();
                    {
                        out.printf("this.input%d = input%d;\n", i + 1, i + 1);
                        out.printf("return this;\n");
                        out.leave();
                    }
                    out.printf("}\n");
                }

                out.println();
                out.printf("public SimpleMakeRule%d<%s> build() {\n", inputCount, typeVars);
                out.enter();
                {
                    out.printf("wireUp();\n");
                    out.printf("if (fn == null)\n");
                    out.enter();
                    {
                        out.printf("throw new NullPointerException(\"fn\");\n");
                        out.leave();
                    }

                    for (int i = 0; i < inputCount; i++) {
                        out.printf("if (input%d == null)\n", i + 1);
                        out.enter();
                        {
                            out.printf("throw new NullPointerException(\"input%d\");\n", i + 1);
                            out.leave();
                        }
                    }
                    out.printf("return new SimpleMakeRule%d<>(priority, targetType, keyType, dataType, fn%s);\n", inputCount, //
                            comma(Naming.inputVars(inputCount)));
                    out.leave();
                }
                out.printf("}\n");

                out.println();
                out.printf("public void make(@NotNull IMakeable%d<TT%s> fn) {\n", inputCount, _TVars);
                out.enter();
                {
                    out.printf("make(subject, fn);\n");
                    out.leave();
                }
                out.printf("}\n");

                out.println();
                out.printf("public void make(@NotNull S subject, @NotNull IMakeable%d<TT%s> fn) {\n", inputCount, _TVars);
                out.enter();
                {
                    out.printf("this.fn = fn;\n");
                    out.printf("apply.accept(subject, build());\n");
                    out.leave();
                }
                out.printf("}\n");

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
