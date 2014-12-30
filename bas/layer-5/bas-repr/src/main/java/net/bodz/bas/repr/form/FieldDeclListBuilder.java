package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class FieldDeclListBuilder {

    FieldDeclBuilder fieldDeclBuilder;
    List<IFieldDecl> fieldDecls = new ArrayList<IFieldDecl>();

    public FieldDeclListBuilder(FieldDeclBuilder fieldDeclBuilder) {
        this.fieldDeclBuilder = fieldDeclBuilder;
    }

    public void fromPathProperties(IFormDecl knownStruct, String... pathProperties)
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

            IFieldDecl fieldDecl = knownStruct.getFieldDef(head);
            if (fieldDecl == null)
                throw new NoSuchPropertyException("Bad head: " + head);

            if (remaining == null)
                fieldDecls.add(fieldDecl);
            else {
                Class<?> valueType = fieldDecl.getValueType();
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
            IFieldDecl field = fieldDeclBuilder.build(property);
            fieldDecls.add(field);
        }
    }

    public List<IFieldDecl> getFields() {
        return fieldDecls;
    }

}
