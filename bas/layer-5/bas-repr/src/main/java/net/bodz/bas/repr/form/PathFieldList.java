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

    PathFieldAccessOptons optons;

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

            if (remaining == null)
                this.add(new PathField(head, Arrays.asList(fieldDecl)));
            else {
                Class<?> valueType = fieldDecl.getValueType();
                IType type = PotatoTypes.getInstance().loadType(valueType);
                parseAndAdd(path.substring(0, dot + 1), fieldDecl, type, remaining);
            }
        }
    }

    void parseAndAdd(String pathPrefix, IFieldDecl fieldDeclPrefix, IType type, String path)
            throws NoSuchPropertyException, ParseException {
        List<IProperty> propertyVector = type.getPropertyVector(path);

        List<IFieldDecl> fieldVector = new ArrayList<IFieldDecl>(propertyVector.size());
        fieldVector.add(fieldDeclPrefix);
        for (IProperty property : propertyVector) {
            IFieldDecl field = new MutableFieldDecl().populate(property);
            fieldVector.add(field);
        }

        String pathFull = pathPrefix + path;
        PathField pathField = new PathField(pathFull, fieldVector);
        this.add(pathField);
    }

}
