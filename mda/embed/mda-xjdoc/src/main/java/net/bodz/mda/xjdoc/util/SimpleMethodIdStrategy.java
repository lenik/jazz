package net.bodz.mda.xjdoc.util;

import java.lang.reflect.Method;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.Type;

public class SimpleMethodIdStrategy
        implements IMethodIdStrategy {

    TypeNameContext typeNameContext;

    public SimpleMethodIdStrategy(TypeNameContext typeNameContext) {
        if (typeNameContext == null)
            throw new NullPointerException("typeNameContext");
        this.typeNameContext = typeNameContext;
    }

    @Override
    public String getMethodId(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        sb.append('(');
        int index = 0;
        for (Class<?> type : method.getParameterTypes()) {
            if (index++ != 0)
                sb.append(",");
            String typeName;
            if (typeNameContext != null)
                typeName = typeNameContext.importType(type);
            else
                typeName = type.getCanonicalName();
            sb.append(typeName);
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String getMethodId(JavaMethod method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        sb.append('(');
        int index = 0;
        for (Type type : method.getParameterTypes()) {
            if (index++ != 0)
                sb.append(",");
            String typeName;
            if (typeNameContext != null)
                typeName = typeNameContext.importType(type);
            else
                typeName = type.getFullyQualifiedName();
            sb.append(typeName);
        }
        sb.append(')');
        return sb.toString();
    }

}
