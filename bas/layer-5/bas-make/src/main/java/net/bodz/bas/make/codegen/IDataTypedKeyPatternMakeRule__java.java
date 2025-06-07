package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class IDataTypedKeyPatternMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.printf("package net.bodz.bas.make.pattern.dtkey;\n");
        out.println();
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule%d;\n", inputCount);
        out.println();
        out.printf("public interface IDataTypedKeyPatternMakeRule%d<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //\n", inputCount);
        out.enter();
        {
            out.enter();
            {
                if (modeUs)
                    for (int i = 0; i < inputCount; i++) {
                        String U = Naming.typeVar(inputCount, i);
                        out.printf("%ss extends IDataTypedParameterizedKey<Param, %sK, %sT>, //\n", U, U, U);
                    }
                out.printf("T extends IKeyData<K, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends IKeyPatternLikeMakeRule%d<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>%s, T, TT%s>,\n", //
                        inputCount, //
                        modeUs ? comma(Naming.typeVars(inputCount, "s")) //
                                : comma(Naming.typeVars(inputCount, U -> String.format("IDataTypedParameterizedKey<Param, %sK, %sT>", U, U))), //
                        comma(Naming.typeVars(inputCount, "", "K", "T")) //
                );
                out.enter();
                {
                    out.enter();
                    {
                        out.printf("IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {\n");
                        out.println();
                        out.leave();
                    }
                    out.leave();
                }
                out.leave();
            }
            out.printf("@Override\n");
            out.printf("default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {\n");
            out.enter();
            {
                out.printf("return new IDataTypedParameterizedKey[] { ");
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
