package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleTargetPatternMakeRuleBuilders__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRuleBuilders");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.pattern.target;\n");

        out.println();
        out.printf("import java.util.function.BiConsumer;\n");

        out.println();
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedTarget;\n");

        out.println();
        out.printf("public abstract class SimpleTargetPatternMakeRuleBuilders<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //\n");
        out.enter();
        {
            out.enter();
            {
                out.printf("T extends IKeyData<TK, TT>, TT> {\n");

                out.println();
                out.leave();
            }
            out.printf("public abstract BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> getApply();\n");

            out.println();
            out.printf("public abstract S getSubject();\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                String typeVars = String.format("Tp, Param, TK%s, T, TT%s", //
                        comma(Naming.typeVars(inputCount, "s", "K")), //
                        comma(Naming.typeVars(inputCount, "", "T")) //
                );
                String builderTypeVars = "S, " + typeVars;

                out.println();
                out.printf("public ");
                if (inputCount != 0) {
                    String U = Naming.typeVar(inputCount, 0);
                    out.printf("<%ss extends IParameterizedTarget<Param, %s, %sK, %sT>, %sK, //\n", U, U, U, U, U);
                    out.enter();
                    {
                        out.enter();
                        {
                            for (int index = 1; index < inputCount; index++) {
                                U = Naming.typeVar(inputCount, index);
                                out.printf("%ss extends IParameterizedTarget<Param, %s, %sK, %sT>, %sK, //\n", U, U, U, U, U);
                            }
                            for (int index = 0; index < inputCount; index++) {
                                U = Naming.typeVar(inputCount, index);
                                out.printf("%s extends IKeyData<%sK, %sT>, %sT%s //\n", U, U, U, U, //
                                        index == inputCount - 1 ? ">" : ",");
                            }
                            out.leave();
                        }
                        out.leave();
                    }
                }

                out.printf("SimpleTargetPatternMakeRule%d.Builder<%s> input(%s) {\n", //
                        inputCount, //
                        builderTypeVars, //
                        Naming.inputParams(inputCount, "s", "", "s"));
                out.enter();
                {
                    out.printf("return SimpleTargetPatternMakeRule%d.<%s>builder()//\n", inputCount, builderTypeVars);
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf(".apply(getApply()).subject(getSubject())//\n");
                            out.printf(".input(%s);\n", Naming.inputVars(inputCount, "s"));
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
