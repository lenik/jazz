package net.bodz.uni.shelj.codegen.java.ioforms;

import net.bodz.bas.c.reflect.query.FieldSelection;
import net.bodz.uni.shelj.codegen.java.JavaCodeBuilder;

public abstract class SourceBuilderForFields
        extends JavaCodeBuilder {

    protected FieldSelection fields;

    public FieldSelection getFields() {
        return fields;
    }

    public void setFields(FieldSelection fields) {
        this.fields = fields;
    }

}
