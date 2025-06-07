package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class ITargetPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.target.ITargetPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.pattern.target;\n");
        out.println();

        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedTarget;\n");
        out.printf("import net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule%d;\n", inputCount);
        out.println();
        out.printf("public interface ITargetPatternMakeRule%d<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //\n", inputCount);
        out.enter();
        {
            out.enter();
            {
                if (modeUs)
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IParameterizedTarget<Param, %s, %sK, %sT>, //\n", U, U, U, U);
                    }
                out.printf("T extends IKeyData<TK, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends ITargetPatternLikeMakeRule%d<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>%s, T, TT%s>,\n", //
                        inputCount, //
                        modeUs ? Naming._typeVars(inputCount, "s") //
                                : comma(Naming.typeVars(inputCount, U -> String.format("IParameterizedTarget<Param, %s, %sK, %sT>", U, U, U))), //
                        Naming._typeVars(inputCount, "", "K", "T"));
                out.enter();
                {
                    out.enter();
                    {
                        out.printf("ITargetPatternMakeRule<Tp, Param, TK, T, TT> {\n");
                        out.println();
                        out.leave();
                    }
                    out.leave();
                }
                out.leave();
            }
            out.printf("@Override\n");
            out.printf("default IParameterizedTarget<?, ?, ?, ?>[] getInputs() {\n");
            out.enter();
            {
                out.printf("return new IParameterizedTarget[] { ");
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
