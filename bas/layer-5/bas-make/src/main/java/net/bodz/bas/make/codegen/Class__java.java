package net.bodz.bas.make.codegen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.codegen.java.JavaCodeBuilder;
import net.bodz.bas.t.tuple.QualifiedName;

import static net.bodz.bas.make.codegen.Names.comma;

public abstract class Class__java
        extends JavaCodeBuilder {

    int inputCount = 0;
    int maxCount = 0;

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

    public boolean isSingle() {
        return false;
    }

    public abstract QualifiedName getQName();

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
