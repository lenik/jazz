package net.bodz.bas.make.codegen;

import net.bodz.bas.codegen.java.JavaCodeBuilder;
import net.bodz.bas.t.tuple.QualifiedName;

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

    public abstract QualifiedName getQName();

}
