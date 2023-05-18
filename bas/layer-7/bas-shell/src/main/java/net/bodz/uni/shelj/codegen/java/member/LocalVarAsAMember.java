package net.bodz.uni.shelj.codegen.java.member;

import java.lang.reflect.AnnotatedElement;

public class LocalVarAsAMember
        implements
            IMember {

    Class<?> type;
    String name;

    public LocalVarAsAMember(Class<?> type, String name) {
        if (type == null)
            throw new NullPointerException("type");
        if (name == null)
            throw new NullPointerException("name");
        this.type = type;
        this.name = name;
    }

    @Override
    public AnnotatedElement toAnnotatedElement() {
        return null;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String javaGet() {
        return name;
    }

    @Override
    public String javaSet(String javaContent) {
        return name + " = " + javaContent;
    }

    @Override
    public String toString() {
        return String.format("var %s: %s", name, type.getName());
    }

}
