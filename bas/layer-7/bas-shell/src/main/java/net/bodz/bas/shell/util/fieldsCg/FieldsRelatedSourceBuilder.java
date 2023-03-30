package net.bodz.bas.shell.util.fieldsCg;

import net.bodz.bas.c.reflect.query.FieldSelection;

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
