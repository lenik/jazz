package net.bodz.bas.program.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.control.Control;
import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.typer.std.ParserUtil;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class MethodOption
        extends AbstractOption {

    private final IType type;
    private final Method method;
    private Class<?>[] parameterTypes;

    public MethodOption(IType type, Method method, MethodDoc doc) {
        super("method:" + new MethodId(method).toString(), //
                method.getName(), method.getGenericReturnType(), doc);
        this.type = type;
        this.method = method;
        this.parameterTypes = method.getParameterTypes();
    }

    @Override
    public int getParameterCount() {
        return parameterTypes.length;
    }

    @Override
    public Object parseValue(Object context, String... parameterStrs)
            throws ParseException {
        Object[] args = new Object[parameterStrs.length];
        for (int i = 0; i < parameterStrs.length; i++) {
            Class<?> type = parameterTypes[i];
            String str = parameterStrs[i];
            Object value = ParserUtil.parse(type, str);
            args[i] = value;
        }
        return args;
    }

    @Override
    public IProperty property() {
        IElementDoc doc = getXjdoc();
        return new InvocationAsProperty(type, method, doc);
    }

}

class InvocationAsProperty
        extends AbstractProperty {

    private final Method method;
    private Object[] args;
    private Object returnValue;

    public InvocationAsProperty(IType declaringType, Method method, IElementDoc doc) {
        super(declaringType, method.getName(), doc);
        this.method = method;
    }

    @Override
    public Class<?> getPropertyClass() {
        return Object[].class;
    }

    @Override
    public Type getPropertyGenericType() {
        return Object[].class;
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        return args;
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        this.args = (Object[]) value;

        int nparam = method.getParameterCount();
        Object[] params = new Object[nparam];
        if (args != null) {
            int com = Math.min(args.length, nparam);
            for (int i = 0; i < com; i++) {
                params[i] = args[i];
            }
        }

        this.returnValue = Control.invoke(method, instance, params);
    }

    public Object getReturnValue() {
        return returnValue;
    }

    @Override
    public Annotation[] getAnnotations() {
        return method.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return method.getDeclaredAnnotations();
    }

}