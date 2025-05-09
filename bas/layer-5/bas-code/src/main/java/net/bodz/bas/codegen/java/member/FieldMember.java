package net.bodz.bas.codegen.java.member;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public class FieldMember
        implements
            IMember {

    Field field;

    public FieldMember(Field field) {
        if (field == null)
            throw new NullPointerException("field");
        this.field = field;
    }

    @Override
    public AnnotatedElement toAnnotatedElement() {
        return field;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public int getModifiers() {
        return field.getModifiers();
    }

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public String javaGet() {
        return getName();
    }

    @Override
    public boolean isJavaGetLikeAVarName() {
        return true;
    }

    @Override
    public String javaSet(String javaContent) {
        return getName() + " = " + javaContent;
    }

    @Override
    public String toString() {
        return String.format("field %s::%s", getType().getName(), getName());
    }

}
