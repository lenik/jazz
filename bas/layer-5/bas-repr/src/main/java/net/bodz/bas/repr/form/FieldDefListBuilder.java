package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class FieldDefListBuilder {

    FieldDefBuilder formFieldBuilder;
    List<IFieldDef> fieldDefs = new ArrayList<IFieldDef>();

    public FieldDefListBuilder(FieldDefBuilder formFieldBuilder) {
        this.formFieldBuilder = formFieldBuilder;
    }

    public void fromPathProperties(IFormDef knownStruct, String... pathProperties)
            throws NoSuchPropertyException, ParseException {
        for (String pathProperty : pathProperties) {
            int dot = pathProperty.indexOf('.');
            String head, remaining;
            if (dot == -1) {
                head = pathProperty;
                remaining = null;
            } else {
                head = pathProperty.substring(0, dot);
                remaining = pathProperty.substring(dot + 1);
            }

            IFieldDef fieldDef = knownStruct.getFieldDef(head);
            if (fieldDef == null)
                throw new NoSuchPropertyException("Bad head: " + head);

            if (remaining == null)
                fieldDefs.add(fieldDef);
            else {
                Class<?> valueType = fieldDef.getValueType();
                IType type = PotatoTypes.getInstance().forClass(valueType);
                fromPathProperties(type, remaining);
            }
        }
    }

    public void fromPathProperties(IType type, String... pathProperties)
            throws NoSuchPropertyException, ParseException {
        for (String pathProperty : pathProperties) {
            IProperty property = type.getPathProperty(pathProperty);
            if (property == null)
                throw new NoSuchPropertyException(pathProperty);
            MutableFieldDef field = formFieldBuilder.build(property);
            fieldDefs.add(field);
        }
    }

    public List<IFieldDef> getFields() {
        return fieldDefs;
    }

}
