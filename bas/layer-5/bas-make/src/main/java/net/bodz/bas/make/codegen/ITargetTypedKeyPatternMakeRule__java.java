package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class ITargetTypedKeyPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.key.ITargetTypedKeyPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.pattern.key;\n");
        out.println();
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedKey;\n");
        out.printf("import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule%d;\n", inputCount);
        out.println();
        out.printf("public interface ITargetTypedKeyPatternMakeRule%d<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //\n", inputCount);
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

                out.printf("extends IKeyPatternLikeMakeRule%d<Tp, Param, TK, IParameterizedKey<?, ?>%s, T, TT%s>,\n", //
                        inputCount, //
                        modeUs ? Naming._typeVars(inputCount, "s") //
                                : comma(Naming.typeVars(inputCount, U -> String.format("IParameterizedKey<Param, %sK>", U))), //
                        Naming._typeVars(inputCount, "", "K", "T"));
                out.enter();
                {
                    out.enter();
                    {
                        out.printf("ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> {\n");
                        out.println();
                        out.leave();
                    }
                    out.leave();
                }
                out.leave();
            }
            out.printf("@Override\n");
            out.printf("default IParameterizedKey<?, ?>[] getInputs() {\n");
            out.enter();
            {
                out.printf("return new IParameterizedKey[] { ");
                for (int i = 0; i < inputCount; i++) {
                    if (i != 0)
                        out.print(", ");
                    out.printf("getInput%d()", i + 1);
                }
                out.printf(" };\n");
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.leave();
        }
        out.printf("}\n");
    }

}
