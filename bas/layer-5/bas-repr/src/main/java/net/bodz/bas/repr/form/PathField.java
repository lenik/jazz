package net.bodz.bas.repr.form;

import java.util.List;

import net.bodz.bas.potato.element.IPropertyAccessor;

public class PathField
        implements IPropertyAccessor {

    private final String path;
    private final List<IFieldDecl> fieldDecls;

    public PathField(String path, List<IFieldDecl> fieldDecls) {
        this.path = path;
        this.fieldDecls = fieldDecls;
    }

    public String getPath() {
        return path;
    }

    public IFieldDecl getFieldDecl() {
        if (fieldDecls.isEmpty())
            return null;
        else
            return fieldDecls.get(fieldDecls.size() - 1);
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        Object obj = instance;
        for (IFieldDecl fieldDecl : fieldDecls) {
            obj = fieldDecl.getAccessor().getValue(obj);
            if (obj == null)
                return null;
        }
        return obj;
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        int max = fieldDecls.size() - 1;
        Object obj = instance;
        for (int i = 0; i < max; i++) {
            obj = fieldDecls.get(i).getAccessor().getValue(obj);
            if (obj == null)
                return;
        }
        IFieldDecl lastField = fieldDecls.get(max);
        lastField.getAccessor().setValue(obj, value);
    }

}
