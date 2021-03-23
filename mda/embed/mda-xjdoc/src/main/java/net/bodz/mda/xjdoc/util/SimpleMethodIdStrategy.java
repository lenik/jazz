package net.bodz.mda.xjdoc.util;

import java.lang.reflect.Method;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

public class SimpleMethodIdStrategy
        implements IMethodIdStrategy {

    ImportMap importMap;

    public SimpleMethodIdStrategy(ImportMap importMap) {
        if (importMap == null)
            throw new NullPointerException("importMap");
        this.importMap = importMap;
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
            if (importMap != null)
                typeName = importMap.add(type);
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
        for (JavaType type : method.getParameterTypes()) {
            if (index++ != 0)
                sb.append(",");
            String typeName;
            if (importMap != null)
                typeName = importMap.add(type);
            else
                typeName = type.getFullyQualifiedName();
            sb.append(typeName);
        }
        sb.append(')');
        return sb.toString();
    }

}
