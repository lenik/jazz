package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class IMakeRules__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.IMakeRules");
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String typeVars = "<T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T")) + ">";
        String _TVars = comma(Naming.typeVars(inputCount, "T"));

        for (int i = 0; i < inputCount; i++) {
            String U = Naming.typeVar(inputCount, i);
        }

        out.printf("package net.bodz.bas.make;\n");
        out.println();
        for (int inputCount = 0; inputCount <= maxCount; inputCount++)
            out.printf("import net.bodz.bas.make.fn.CompileFunction%d;\n", inputCount);
        for (int inputCount = 0; inputCount <= maxCount; inputCount++)
            out.printf("import net.bodz.bas.make.fn.IMakeable%d;\n", inputCount);
        for (int inputCount = 0; inputCount <= maxCount; inputCount++)
            out.printf("import net.bodz.bas.make.fn.SimpleMakeRule%d;\n", inputCount);
        out.printf("import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;\n");
        out.printf("import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;\n");
        for (int inputCount = 0; inputCount <= maxCount; inputCount++)
            out.printf("import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule%d;\n", inputCount);
        out.printf("import net.bodz.bas.make.pattern.key.IKeyPattern;\n");
        for (int inputCount = 0; inputCount <= maxCount; inputCount++)
            out.printf("import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule%d;\n", inputCount);
        out.printf("import net.bodz.bas.meta.decl.NotNull;\n");
        out.println();
        out.printf("public interface IMakeRules\n");
        out.enter();
        {
            out.enter();
            {
                out.printf("extends IMakeRulesBase {\n");
                out.leave();
            }

            out.println();
            out.printf("// rules: exact match\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                out.println();
                out.printf("default <T extends IKeyData<TK, TT>, TK, TT");

                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf(", %s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
                }
                out.printf("> //\n");
                out.printf("void addRule(@NotNull T target%s, @NotNull IMakeable%d<TT%s> fn) {\n", //
                        comma(Naming.inputParams(inputCount)), inputCount, //
                        comma(Naming.typeVars(inputCount, "T")));
                out.enter();
                {
                    out.printf("addRule(target, //\n");
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf("SimpleMakeRule%d.<T, TK, TT%s>builder() //\n", inputCount, //
                                    comma(Naming.typeVars(inputCount, "", "K", "T")));
                            out.enter();
                            {
                                out.enter();
                                {
                                    if (inputCount != 0)
                                        out.printf(".input(%s) //\n", Naming.inputVars(inputCount));
                                    out.printf(".fn(fn).build());\n");
                                    out.leave();
                                }
                                out.leave();
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("// rules: key match\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                out.println();
                out.printf("default <T extends IKeyData<TK, TT>, TK, TT");
                if (inputCount > 0) {
                    out.println(", //");
                    out.enter();
                    {
                        out.enter();
                        {
                            for (int i = 0; i < inputCount; i++) {
                                String U = Naming.typeVar(inputCount, i);
                                out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT%s", U, U, U, U, U, //
                                        i == inputCount - 1 ? "" : ", //\n");
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                }
                out.print("> //\n");

                out.printf("void addKeyRule(@NotNull TK key%s, @NotNull IMakeable%d<TT%s> fn) {\n", //
                        comma(Naming.inputParams(inputCount, "", "@NotNull ")), //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "T")));
                out.enter();
                {
                    out.printf("addKeyRule(key, //\n");
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf("SimpleMakeRule%d.<T, TK, TT%s>builder() //\n", inputCount, //
                                    comma(Naming.typeVars(inputCount, "", "K", "T")));
                            out.enter();
                            {
                                out.enter();
                                {
                                    if (inputCount != 0)
                                        out.printf(".input(%s) //\n", Naming.inputVars(inputCount));
                                    out.printf(".fn(fn).build());\n");
                                    out.leave();
                                }
                                out.leave();
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("// rules: key type match\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                out.println();
                out.printf("default <T extends IKeyData<TK, TT>, TK, TT");
                if (inputCount > 0) {
                    out.println(", //");
                    out.enter();
                    {
                        out.enter();
                        {
                            for (int i = 0; i < inputCount; i++) {
                                String U = Naming.typeVar(inputCount, i);
                                out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT%s", U, U, U, U, U, //
                                        i == inputCount - 1 ? "" : ", //\n");
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                }
                out.print("> //\n");

                out.printf("void addKeyTypeRule(@NotNull Class<TK> keyType%s, @NotNull IMakeable%d<TT%s> fn) {\n", //
                        comma(Naming.inputParams(inputCount, "", "@NotNull ")), //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "T")));
                out.enter();
                {
                    out.printf("addKeyTypeRule(keyType, //\n");
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf("SimpleMakeRule%d.<T, TK, TT%s>builder() //\n", inputCount, //
                                    comma(Naming.typeVars(inputCount, "", "K", "T")));
                            out.enter();
                            {
                                out.enter();
                                {
                                    if (inputCount != 0)
                                        out.printf(".input(%s) //\n", Naming.inputVars(inputCount));
                                    out.printf(".fn(fn).build());\n");
                                    out.leave();
                                }
                                out.leave();
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("// rules: data type match\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                out.println();
                out.printf("default <T extends IKeyData<TK, TT>, TK, TT");
                if (inputCount > 0) {
                    out.println(", //");
                    out.enter();
                    {
                        out.enter();
                        {
                            for (int i = 0; i < inputCount; i++) {
                                String U = Naming.typeVar(inputCount, i);
                                out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT%s", U, U, U, U, U, //
                                        i == inputCount - 1 ? "" : ", //\n");
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                }
                out.print("> //\n");

                out.printf("void addRule(@NotNull Class<TT> dataType%s, @NotNull IMakeable%d<TT%s> fn) {\n", //
                        comma(Naming.inputParams(inputCount, "", "@NotNull ")), //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "T")));
                out.enter();
                {
                    out.printf("addRule(dataType, //\n");
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf("SimpleMakeRule%d.<T, TK, TT%s>builder() //\n", inputCount, //
                                    comma(Naming.typeVars(inputCount, "", "K", "T")));
                            out.enter();
                            {
                                out.enter();
                                {
                                    if (inputCount != 0)
                                        out.printf(".input(%s) //\n", Naming.inputVars(inputCount));
                                    out.printf(".fn(fn).build());\n");
                                    out.leave();
                                }
                                out.leave();
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("// rules: key pattern\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                out.println();
                out.printf("default <Tp extends IKeyPattern<Param, K>, Param, K, //\n");
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
                            out.printf(", //\n");
                            String U = Naming.typeVar(inputCount, i);
                            out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.print("> //\n");
                out.printf("void addPatternRule(@NotNull Tp pattern%s, @NotNull CompileFunction%d<T, K, TT%s> fn) {\n", //
                        comma(Naming.inputParams(inputCount, "s", "@NotNull ", "s")), //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "", "K", "T")));
                out.enter();
                {
                    out.printf("addPatternRule(pattern, //\n");
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf("SimpleKeyPatternMakeRule%d.<Tp, Param, K%s, T, TT%s>builder()//\n", //
                                    inputCount, //
                                    comma(Naming.typeVars(inputCount, "s", "K")), //
                                    comma(Naming.typeVars(inputCount, "", "T")));
                            out.enter();
                            {
                                out.enter();
                                {
                                    out.printf(".pattern(pattern) //\n");
                                    if (inputCount != 0)
                                        out.printf(".input(%s)//\n", Naming.inputVars(inputCount, "s"));
                                    out.printf(".fn(fn).build());\n");
                                    out.leave();
                                }
                                out.leave();
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("}\n");
            }

            out.println();
            out.printf("// rules: data-typed key pattern\n");
            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                out.println();
                out.printf("default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //\n");
                out.enter();
                {
                    out.enter();
                    {
                        for (int i = 0; i < inputCount; i++) {
                            String U = Naming.typeVar(inputCount, i);
                            out.printf("%ss extends IDataTypedParameterizedKeys<Param, %sK, %sT>, %sK, //\n", U, U, U, U);
                        }
                        out.printf("T extends IKeyData<K, TT>, TT");

                        for (int i = 0; i < inputCount; i++) {
                            out.print(", //\n");
                            String U = Naming.typeVar(inputCount, i);
                            out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                        }
                        out.leave();
                    }
                    out.leave();
                }
                out.print("> //\n");
                out.printf("void addPatternRule(@NotNull Tp pattern%s, @NotNull CompileFunction%d<T, K, TT%s> fn) {\n", //
                        comma(Naming.inputParams(inputCount, "s", "@NotNull ", "s")), //
                        inputCount, //
                        comma(Naming.typeVars(inputCount, "", "K", "T")));
                out.enter();
                {
                    out.printf("addPatternRule(pattern, //\n");
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf("SimpleDataTypedKeyPatternMakeRule%d.<Tp, Param, K%s, T, TT%s>builder()//\n",//
                                    inputCount, //
                                    comma(Naming.typeVars(inputCount, "s", "K")), //
                                    comma(Naming.typeVars(inputCount, "", "T")));
                            out.enter();
                            {
                                out.enter();
                                {
                                    out.printf(".pattern(pattern) //\n");
                                    if (inputCount != 0)
                                        out.printf(".input(%s)//\n", Naming.inputVars(inputCount, "s"));
                                    out.printf(".fn(fn).build());\n");
                                    out.leave();
                                }
                                out.leave();
                            }
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
