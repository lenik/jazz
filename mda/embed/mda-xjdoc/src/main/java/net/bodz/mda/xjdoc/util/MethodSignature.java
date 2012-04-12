package net.bodz.mda.xjdoc.util;

import java.io.Serializable;
import java.util.Arrays;

public final class MethodSignature
        implements Serializable {

    private static final long serialVersionUID = 1L;

    final String methodName;
    final String[] types;
    final int[] dimss;

    public MethodSignature(String methodName, int parameterCount) {
        if (methodName == null)
            throw new NullPointerException("methodName");
        this.methodName = methodName;
        this.types = new String[parameterCount];
        this.dimss = new int[parameterCount];
    }

    public void setParameterType(int index, String fqcn, int dim) {
        types[index] = fqcn;
        dimss[index] = dim;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getParameterCount() {
        return types.length;
    }

    public String getParameterType(int index) {
        return types[index];
    }

    public int getDimensions(int index) {
        return dimss[index];
    }

    public String getMethodId(TypeNameContext typeNameContext) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(methodName);
        sb.append('(');
        for (int index = 0; index < types.length; index++) {
            if (index != 0)
                sb.append(",");

            String fqcn = types[index];
            int dims = dimss[index];

            if (typeNameContext != null) {
                String simpleName = typeNameContext.importTypeName(fqcn);
                sb.append(simpleName);
            } else {
                sb.append(fqcn);
            }

            for (int dim = 0; dim < dims; dim++)
                sb.append("[]");
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = methodName.hashCode();
        hash = hash * 17 + Arrays.hashCode(types);
        hash = hash * 17 + Arrays.hashCode(dimss);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MethodSignature))
            return false;
        MethodSignature o = (MethodSignature) obj;
        if (!methodName.equals(o.methodName))
            return false;
        if (!Arrays.equals(types, o.types))
            return false;
        if (!Arrays.equals(dimss, o.dimss))
            return false;
        return true;
    }

}
