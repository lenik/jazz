package net.bodz.uni.shelj.codegen.java.member;

import java.lang.reflect.AnnotatedElement;

public class ContextFieldExprAsAMember
        implements
            IMember {

    IMember context;
    String fieldExpr;
    Class<?> preKnownType;

    public ContextFieldExprAsAMember(IMember context, String fieldExpr, Class<?> preKnownType) {
        if (context == null)
            throw new NullPointerException("context");
        if (fieldExpr == null)
            throw new NullPointerException("fieldExpr");
        if (preKnownType == null)
            throw new NullPointerException("preknownType");
        this.context = context;
        this.fieldExpr = fieldExpr;
        this.preKnownType = preKnownType;
    }

    @Override
    public AnnotatedElement toAnnotatedElement() {
        return null;
    }

    @Override
    public Class<?> getType() {
        return preKnownType;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public String getName() {
        return context.getName() + fieldExpr;
    }

    @Override
    public String javaGet() {
        return context.javaGet() + fieldExpr;
    }

    @Override
    public String javaSet(String javaContent) {
        return context.javaGet() + fieldExpr + " = " + javaContent;
    }

    @Override
    public String toString() {
        return context + " :: field(" + fieldExpr + ")";
    }

}
