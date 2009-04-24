package net.bodz.bas.ant;

public class Parameter {

    public Class<?> type = String.class;
    public String   valueText;

    public void setType(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type"); //$NON-NLS-1$
        this.type = type;
    }

    public void setTypeName(String typeName) throws ClassNotFoundException {
        if (typeName == null)
            throw new NullPointerException("typeName"); //$NON-NLS-1$
        this.type = Class.forName(typeName);
    }

    public void setValue(String value) {
        this.valueText = value;
    }

    @Override
    public String toString() {
        return type.getName() + '(' + valueText + ')';
    }

}
