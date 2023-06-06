package net.bodz.bas.program.model;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.provider.reflect.FieldProperty;
import net.bodz.mda.xjdoc.model.FieldDoc;

public class FieldOption
        extends AbstractOption {

    private final FieldProperty field;

    public FieldOption(FieldProperty field, FieldDoc doc) {
        super("field:" + field.getName(), //
                field.getName(), field.getField().getGenericType(), doc);
        this.field = field;
    }

    @Override
    public IProperty property() {
        return field;
    }

}
