package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class FormFieldListBuilder {

    FormFieldBuilder formFieldBuilder;
    List<IFormField> fields = new ArrayList<IFormField>();

    public FormFieldListBuilder(FormFieldBuilder formFieldBuilder) {
        this.formFieldBuilder = formFieldBuilder;
    }

    public void fromPathProperties(IFormStruct knownStruct, String... pathProperties)
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

            IFormField field = knownStruct.getField(head);
            if (field == null)
                throw new NoSuchPropertyException("Bad head: " + head);

            if (remaining == null)
                fields.add(field);
            else {
                Class<?> valueType = field.getValueType();
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
            MutableFormField field = formFieldBuilder.build(property);
            fields.add(field);
        }
    }

    public List<IFormField> getFields() {
        return fields;
    }

}
