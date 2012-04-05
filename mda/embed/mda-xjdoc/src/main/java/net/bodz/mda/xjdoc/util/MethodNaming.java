package net.bodz.mda.xjdoc.util;

import java.lang.reflect.Method;

public class MethodNaming {

    public static String getMethodId(TypeNameContext context, Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        sb.append('(');
        int index = 0;
        for (Class<?> type : method.getParameterTypes()) {
            if (index++ != 0)
                sb.append(",");
            String typeName;
            if (context != null)
                typeName = context.importType(type);
            else
                typeName = type.getCanonicalName();
            sb.append(typeName);
        }
        sb.append(')');
        return sb.toString();
    }

}
