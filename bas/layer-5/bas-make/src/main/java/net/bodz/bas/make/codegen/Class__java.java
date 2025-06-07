package net.bodz.bas.make.codegen;

import java.util.function.Function;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.codegen.java.JavaCodeBuilder;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public abstract class Class__java
        extends JavaCodeBuilder {

    int inputCount = 0;
    int maxCount = 0;
    boolean modeUs = true;

    public int getInputCount() {
        return inputCount;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isValid(int inputCount) {
        if (isSingle())
            return inputCount == maxCount;
        return true;
    }

    public boolean isSingle() {
        return false;
    }

    public abstract QualifiedName getQName();

    @SafeVarargs
    final void inputTypeParams(JavaSourceWriter out, boolean enclosed, Function<String, String>... befores) {
        inputTypeParams(out, enclosed, inputCount, befores);
    }

    @SafeVarargs
    final void inputTypeParams(JavaSourceWriter out, boolean enclosed, int inputCount, Function<String, String>... befores) {
        boolean indent = enclosed;
        String startTag = enclosed ? "<" : "";
        String endTag = enclosed ? ">" : "";

        boolean started = false;

        if (inputCount != 0) {
            for (Function<String, String> before : befores) {
                if (before == null)
                    continue;
                for (int index = 0; index < inputCount; index++) {
                    String U = Naming.typeVar(inputCount, index);
                    if (started) {
                        out.printf(", //\n");
                    } else {
                        out.print(startTag);
                        if (indent) {
                            out.enter();
                            out.enter();
                        }
                        started = true;
                    }
                    out.print(before.apply(U));
                }
            }

            for (int index = 0; index < inputCount; index++) {
                String U = Naming.typeVar(inputCount, index);
                if (started) {
                    out.printf(", //\n");
                } else {
                    out.print(startTag);
                    if (indent) {
                        out.enter();
                        out.enter();
                    }
                    started = true;
                }
                out.printf("%s extends IKeyData<%sK, %sT>, %sK, %sT", U, U, U, U, U);
            }

            if (started) {
                out.print(endTag);
                if (indent) {
                    out.leave();
                    out.leave();
                }
            }
        }
    }

    void inputParams(JavaSourceWriter out, Function<String, String> typeFn, String varSuffix) {
        inputParams(out, typeFn, varSuffix, inputCount);
    }

    void inputParams(JavaSourceWriter out, Function<String, String> typeFn, String varSuffix, int inputCount) {
        for (int i = 0; i < inputCount; i++) {
            String U = Naming.typeVar(inputCount, i);
            String type = typeFn.apply(U);
            String var = "input" + (i + 1) + varSuffix;
            if (i != 0)
                out.printf(", \n");
            out.printf("%s %s", type, var);
        }
    }

    void applySubject(JavaSourceWriter out, String ruleType, String builderTypeVars) {
        out.println();
        out.printf("BiConsumer<S, %s> apply;\n", ruleType);
        out.printf("S subject;\n");

        out.println();
        out.printf("public Builder<%s> apply(BiConsumer<S, %s> apply) {\n", builderTypeVars, ruleType);
        out.enter();
        {
            out.printf("this.apply = apply;\n");
            out.printf("return this;\n");
            out.leave();
        }
        out.printf("}\n");

        out.println();
        out.printf("public Builder<%s> subject(S subject) {\n", builderTypeVars);
        out.enter();
        {
            out.printf("this.subject = subject;\n");
            out.printf("return this;\n");
            out.leave();
        }
        out.printf("}\n");
    }

    void makeCompileFn(JavaSourceWriter out) {
        String fnTypeVars = "T, TK, TT" + comma(Naming.typeVars(inputCount, "", "K", "T"));

        out.println();
        out.printf("public void make(CompileFunction%d<%s> fn) {\n", inputCount, fnTypeVars);
        out.enter();
        {
            out.printf("make(subject, fn);\n");
            out.leave();
        }
        out.printf("}\n");

        out.println();
        out.printf("public void make(S subject, CompileFunction%d<%s> fn) {\n", inputCount, fnTypeVars);
        out.enter();
        {
            out.printf("if (subject == null)\n");
            out.enter();
            {
                out.printf("throw new NullPointerException(\"subject\");\n");
                out.leave();
            }
            out.printf("this.fn = fn;\n");
            out.printf("apply.accept(subject, build());\n");
            out.leave();
        }
        out.printf("}\n");
    }

}
