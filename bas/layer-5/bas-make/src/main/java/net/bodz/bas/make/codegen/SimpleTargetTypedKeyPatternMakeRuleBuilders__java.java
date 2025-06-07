package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class SimpleTargetTypedKeyPatternMakeRuleBuilders__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRuleBuilders");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.pattern.key;\n");

        out.println();
        out.printf("import java.util.function.BiConsumer;\n");

        out.println();
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedKey;\n");

        out.println();
        out.printf("public abstract class SimpleTargetTypedKeyPatternMakeRuleBuilders<S, Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> {\n");

        out.println();
        out.enter();
        {
            out.printf("public abstract BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> getApply();\n");

            out.println();
            out.printf("public abstract S getSubject();\n");

            out.println();
            out.printf("public abstract Tp getPattern();\n");

            for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
                String typeVars = String.format("Tp, Param, TK%s, T, TT%s", //
                        modeUs ? comma(Naming.typeVars(inputCount, "s")) : "", //
                        comma(Naming.typeVars(inputCount, "", "K", "T")) //
                );
                String builderTypeVars = "S, " + typeVars;

                out.println();
                out.printf("public ");
                inputTypeParams(out, true, inputCount, //
                        modeUs ? U -> String.format("%ss extends IParameterizedKey<Param, %sK>", U, U) : null);
                out.printf("SimpleTargetTypedKeyPatternMakeRule%d.Builder<%s> input(%s) {\n", //
                        inputCount, //
                        builderTypeVars, //
                        modeUs ? Naming.inputParams(inputCount, "s", "", "s") //
                                : Naming.inputParams(inputCount, U -> String.format("IParameterizedKey<Param, %sK>", U), "s"));
                out.enter();
                {
                    out.printf("return SimpleTargetTypedKeyPatternMakeRule%d.<%s>builder()//\n", inputCount, builderTypeVars);
                    out.enter();
                    {
                        out.enter();
                        {
                            out.printf(".apply(getApply()).subject(getSubject()) //\n");
                            out.printf(".pattern(getPattern()) //\n");
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
