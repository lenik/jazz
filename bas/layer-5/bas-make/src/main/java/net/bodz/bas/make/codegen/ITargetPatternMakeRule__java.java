package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

public class ITargetPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.key.ITargetPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.pattern.key;\n");
        out.println();
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedKeys;\n");
        out.printf("import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule%d;\n", inputCount);
        out.println();
        out.printf("public interface ITargetPatternMakeRule%d<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //\n", inputCount);
        out.enter();
        {
            out.enter();
            {
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%ss extends IParameterizedKeys<Param, %sK>, %sK, //\n", U, U, U);
                }
                out.printf("T extends IKeyData<TK, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends IKeyPatternLikeMakeRule%d<Tp, Param, TK, IParameterizedKeys<?, ?>%s, T, TT%s>,\n", //
                        inputCount, //
                        Naming._typeVars(inputCount, "s", "K"), //
                        Naming._typeVars(inputCount, "", "T"));
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
            out.printf("default IParameterizedKeys<?, ?>[] getInputs() {\n");
            out.enter();
            {
                out.printf("return new IParameterizedKeys[] { ");
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
