package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class PathFieldsBuilder {

    FieldDeclBuilder fieldDeclBuilder;
    List<PathField> pathFields = new ArrayList<>();

    public PathFieldsBuilder(FieldDeclBuilder fieldDeclBuilder) {
        this.fieldDeclBuilder = fieldDeclBuilder;
    }

    public void fromPropertyPaths(IFormDecl knownStruct, String... paths)
            throws NoSuchPropertyException, ParseException {
        for (String path : paths) {
            int dot = path.indexOf('.');
            String head, remaining;
            if (dot == -1) {
                head = path;
                remaining = null;
            } else {
                head = path.substring(0, dot);
                remaining = path.substring(dot + 1);
            }

            IFieldDecl fieldDecl = knownStruct.getFieldDecl(head);
            if (fieldDecl == null)
                throw new NoSuchPropertyException("Bad head: " + head);

            if (remaining == null)
                pathFields.add(new PathField(head, Arrays.asList(fieldDecl)));
            else {
                Class<?> valueType = fieldDecl.getValueType();
                IType type = PotatoTypes.getInstance().forClass(valueType);
                fromPropertyPaths(path.substring(0, dot + 1), fieldDecl, type, remaining);
            }
        }
    }

    void fromPropertyPaths(String pathPrefix, IFieldDecl fieldDeclPrefix, IType type, String... paths)
            throws NoSuchPropertyException, ParseException {
        for (String path : paths) {
            List<IProperty> propertyVector = type.getPropertyVector(path);
            List<IFieldDecl> fieldVector = new ArrayList<>(propertyVector.size());
            fieldVector.add(fieldDeclPrefix);

            for (IProperty property : propertyVector) {
                IFieldDecl field = fieldDeclBuilder.build(property);
                fieldVector.add(field);
            }

            String pathFull = pathPrefix + path;
            PathField pathField = new PathField(pathFull, fieldVector);
            pathFields.add(pathField);
        }
    }

    public List<PathField> getFields() {
        return pathFields;
    }

}
