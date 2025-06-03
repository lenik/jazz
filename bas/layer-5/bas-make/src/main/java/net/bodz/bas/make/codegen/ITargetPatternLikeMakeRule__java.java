package net.bodz.bas.make.codegen;

import java.io.IOException;

import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public class ITargetPatternLikeMakeRule__java
        extends Class__java {

    @Override
    public QualifiedName getQName() {
        return QualifiedName.parse("net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule" + inputCount);
    }

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        String ruleTypeVars = "<T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T")) + ">";
        String _TVars = comma(Naming.typeVars(inputCount, "T"));

        out.printf("package net.bodz.bas.make.pattern.template;\n");
        out.println();
        out.printf("import net.bodz.bas.make.BoundRule;\n");
        out.printf("import net.bodz.bas.make.CompileException;\n");
        out.printf("import net.bodz.bas.make.IDataBinding;\n");
        out.printf("import net.bodz.bas.make.IKeyData;\n");
        out.printf("import net.bodz.bas.make.IParameterizedTarget;\n");
        out.printf("import net.bodz.bas.make.fn.IMakeable%d;\n", inputCount);
        out.printf("import net.bodz.bas.make.fn.MakeFunction;\n");
        out.printf("import net.bodz.bas.make.fn.SimpleMakeRule%d;\n", inputCount);
        out.printf("import net.bodz.bas.meta.decl.NotNull;\n");
        out.println();
        out.printf("public interface ITargetPatternLikeMakeRule%d<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //\n", inputCount);
        out.enter();
        {
            out.enter();
            {
                out.printf("Keys extends IParameterizedTarget<?, ?, ?, ?>, //\n");
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%ss extends IParameterizedTarget<Param, %s, %sK, %sT>, %sK, //\n", U, U, U, U, U);
                }
                out.printf("T extends IKeyData<TK, TT>, TT");
                for (int i = 0; i < inputCount; i++) {
                    out.print(", //\n");
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s extends IKeyData<%sK, %sT>, %sT", U, U, U, U);
                }
                out.print("> //\n");

                out.printf("extends ITargetPatternLikeMakeRule<Tp, Param, TK, Keys, T, TT> {\n");
                out.println();
                out.leave();
            }
            out.printf("@Override\n");
            out.printf("default int getPriority() {\n");
            out.enter();
            {
                out.printf("return %d;\n", 0);
                out.leave();
            }
            out.printf("}\n");

            for (int i = 0; i < inputCount; i++) {
                String U = Naming.typeVar(inputCount, i);
                out.println();
                out.printf("%ss getInput%d();\n", U, i + 1);
            }
            out.println();
            out.printf("@Override\n");
            out.printf("default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)\n");
            out.enter();
            {
                out.enter();
                {
                    out.printf("throws CompileException {\n");
                    out.leave();
                }
                out.printf("Tp pattern = getPattern();\n");

                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("@SuppressWarnings(\"unchecked\")\n");
                    out.printf("%ss input%ds = (%ss) getInputs()[%d];\n", U, i + 1, U, i);
                }
                out.println();
                out.printf("Param param = pattern.match(target);\n");
                out.printf("if (param == null)\n");
                out.enter();
                {
                    out.printf("return null;\n");
                    out.println();
                    out.leave();
                }

                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("%s input%d = input%ds.getTarget(param, binding);\n", U, i + 1, i + 1);
                    out.printf("if (input%d == null)\n", i + 1);
                    out.enter();
                    {
                        out.printf("return null;\n");
                        out.println();
                        out.leave();
                    }
                }

                out.printf("IMakeable%d<TT%s> fn = compile(target%s);\n", inputCount, _TVars, //
                        comma(Naming.inputVars(inputCount)));
                out.printf("if (fn == null)\n");
                out.enter();
                {
                    out.printf("return null;\n");
                    out.println();
                    out.leave();
                }
                out.printf("SimpleMakeRule%d%s rule = SimpleMakeRule%d.%sbuilder()//\n", //
                        inputCount, ruleTypeVars, inputCount, ruleTypeVars);
                out.enter();
                {
                    out.enter();
                    {
                        out.printf(".priority(this.getPriority())//\n");
                        out.printf(".input(%s)//\n", Naming.inputVars(inputCount));
                        out.printf(".fn(fn).build();\n");
                        out.println();
                        out.leave();
                    }
                    out.leave();
                }
                out.printf("BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target%s);\n", //
                        comma(Naming.inputVars(inputCount)));
                out.printf("return instance;\n");
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("@Override\n");
            out.printf("default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)\n");
            out.enter();
            {
                out.enter();
                {
                    out.printf("throws CompileException {\n");
                    out.leave();
                }
                for (int i = 0; i < inputCount; i++) {
                    String U = Naming.typeVar(inputCount, i);
                    out.printf("@SuppressWarnings(\"unchecked\")\n");
                    out.printf("%s input%d = (%s) inputs[%d];\n", U, i + 1, U, i);
                }
                out.printf("IMakeable%d<TT%s> fn = compile(target%s);\n", inputCount, //
                        _TVars, //
                        comma(Naming.inputVars(inputCount)));
                out.printf("return (t, iv) -> {\n");
                out.enter();
                {
                    out.printf("TT tData = fn.make(%s);\n", Naming.inputVars(inputCount, ".getData()"));
                    out.printf("t.setData(tData);\n");
                    out.leave();
                }
                out.printf("};\n");
                out.leave();
            }
            out.printf("}\n");
            out.println();
            out.printf("IMakeable%d<TT%s> compile(@NotNull T target%s)\n", inputCount, _TVars, //
                    comma(Naming.inputParams(inputCount)));
            out.enter();
            {
                out.enter();
                {
                    out.printf("throws CompileException;\n");
                    out.println();
                    out.leave();
                }
                out.leave();
            }
            out.leave();
        }
        out.printf("}\n");

    }

}
