package net.bodz.bas.ant;

public class TypedParameter {

    Class<?> type;
    String   valueText;

    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setTypeName(String typeName) throws ClassNotFoundException {
        this.type = Class.forName(typeName);
    }

    public void setValue(String value) {
        this.valueText = value;
    }

}
