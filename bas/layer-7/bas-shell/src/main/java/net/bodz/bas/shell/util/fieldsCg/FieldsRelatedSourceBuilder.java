package net.bodz.bas.shell.util.fieldsCg;

import java.lang.reflect.Field;
import java.util.Collection;

public abstract class FieldsRelatedSourceBuilder
        extends JavaSourceBuilder {

    protected Collection<Field> fields;

    public Collection<Field> getFields() {
        return fields;
    }

    public void setFields(Collection<Field> fields) {
        this.fields = fields;
    }

}
