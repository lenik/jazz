package net.bodz.uni.shelj.codegen.java.ioforms;

import net.bodz.bas.c.reflect.query.FieldSelection;
import net.bodz.uni.shelj.codegen.java.JavaSourceBuilder;

public abstract class FieldsRelatedSourceBuilder
        extends JavaSourceBuilder {

    protected FieldSelection fields;

    public FieldSelection getFields() {
        return fields;
    }

    public void setFields(FieldSelection fields) {
        this.fields = fields;
    }

}
