package net.bodz.bas.codegen.java.member;

import java.lang.reflect.AnnotatedElement;

public class ContextMemberAsAMember
        implements
            IMember {

    IMember context;
    IMember member;

    public ContextMemberAsAMember(IMember context, IMember member) {
        if (context == null)
            throw new NullPointerException("context");
        if (member == null)
            throw new NullPointerException("member");
        this.context = context;
        this.member = member;
    }

    @Override
    public AnnotatedElement toAnnotatedElement() {
        return member;
    }

    @Override
    public Class<?> getType() {
        return member.getType();
    }

    @Override
    public int getModifiers() {
        return member.getModifiers();
    }

    @Override
    public String getName() {
        return context.getName() + "." + member.getName();
    }

    @Override
    public String javaGet() {
        return context.javaGet() + "." + member.javaGet();
    }

    @Override
    public String javaSet(String javaContent) {
        if (!member.isJavaSetCanHaveContext())
            throw new UnsupportedOperationException(//
                    "can't generate code for settings a member with a context.");
        return context.javaGet() + "." + member.javaSet(javaContent);
    }

    @Override
    public String toString() {
        return context + " :: " + member;
    }

}
