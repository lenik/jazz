package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class PathFieldList
        extends ArrayList<PathField> {

    private static final long serialVersionUID = 1L;

    PathFieldAccessOptons options;

    public void parseAndAdd(IFormDecl knownStruct, String... paths)
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

            // TODO pattern matching...
            if ("*".equals(head)) {
                for (IFieldDecl f : knownStruct.getFieldDecls()) {
                    if (!f.getProperty().isReadable())
                        continue;
                    this.add(new PathField(f.getName(), Arrays.asList(f)));
                }
                continue;
            }

            IFieldDecl fieldDecl = knownStruct.getFieldDecl(head);
            if (fieldDecl == null)
                throw new NoSuchPropertyException("Bad head: " + head);

            PathField pathField;
            if (remaining == null)
                pathField = new PathField(head, Arrays.asList(fieldDecl));
            else
                pathField = parsePathField(path.substring(0, dot + 1), fieldDecl, remaining);
            add(pathField);
        }
    }

    public PathField parsePathField(String pathPrefix, IFieldDecl fieldDecl, String pathRemaining)
            throws NoSuchPropertyException, ParseException {
        Class<?> valueType = fieldDecl.getValueType();
        IType fieldValueType = PotatoTypes.getInstance().loadType(valueType);

        List<IProperty> propertyVector = fieldValueType.getPropertyVector(pathRemaining);

        List<IFieldDecl> fieldVector = new ArrayList<IFieldDecl>(propertyVector.size());
        fieldVector.add(fieldDecl);
        for (IProperty property : propertyVector) {
            IFieldDecl field = new MutableFieldDecl().populate(property);
            fieldVector.add(field);
        }

        String pathFull = pathPrefix + pathRemaining;
        return new PathField(pathFull, fieldVector);
    }

}
