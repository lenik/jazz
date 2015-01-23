package net.bodz.bas.repr.form;

import java.util.List;

import net.bodz.bas.potato.element.IPropertyAccessor;

public class PathField
        implements IPropertyAccessor {

    private final String path;
    private final List<IFieldDecl> fieldVector;

    public PathField(String path, List<IFieldDecl> fieldVector) {
        this.path = path;
        this.fieldVector = fieldVector;
    }

    public String getPath() {
        return path;
    }

    public IFieldDecl getFieldDecl() {
        if (fieldVector.isEmpty())
            return null;
        else
            return fieldVector.get(fieldVector.size() - 1);
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        Object obj = instance;
        for (IFieldDecl fieldDecl : fieldVector) {
            obj = fieldDecl.getAccessor().getValue(obj);
            if (obj == null)
                return null;
        }
        return obj;
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        int max = fieldVector.size() - 1;
        Object obj = instance;
        for (int i = 0; i < max; i++) {
            obj = fieldVector.get(i).getAccessor().getValue(obj);
            if (obj == null)
                return;
        }
        IFieldDecl lastField = fieldVector.get(max);
        lastField.getAccessor().setValue(obj, value);
    }

}
