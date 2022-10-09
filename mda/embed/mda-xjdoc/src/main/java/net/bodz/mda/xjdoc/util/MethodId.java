package net.bodz.mda.xjdoc.util;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class MethodId
        implements
            Serializable {

    private static final long serialVersionUID = 1L;

    final String methodName;
    final String[] types;
    final int[] dimCounts;

    public MethodId(Method method) {
        this(method.getName(), method.getParameterTypes());
    }

    public MethodId(Constructor<?> ctor) {
        this(ctor.getName(), ctor.getParameterTypes());
    }

    public MethodId(String methodName, Class<?>... parameterTypes) {
        this(methodName, parameterTypes.length);
        for (int index = 0; index < parameterTypes.length; index++)
            setParameterType(index, parameterTypes[index]);
    }

    public MethodId(String methodName, int parameterCount) {
        if (methodName == null)
            throw new NullPointerException("methodName");
        this.methodName = methodName;
        this.types = new String[parameterCount];
        this.dimCounts = new int[parameterCount];
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

    public void setParameterType(int index, Class<?> type) {
        int dims = 0;
        while (type.isArray()) {
            type = type.getComponentType();
            dims++;
        }
        setParameterType(index, type.getCanonicalName(), dims);
    }

    public void setParameterType(int index, String fqcn, int dimCount) {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (fqcn.isEmpty())
            throw new IllegalArgumentException("Parameter typename is empty.");
        types[index] = fqcn;
        dimCounts[index] = dimCount;
    }

    public int getDimensionCount(int index) {
        return dimCounts[index];
    }

    public String getImportedForm(ImportMap importMap) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(methodName);
        sb.append('(');
        for (int index = 0; index < types.length; index++) {
            if (index != 0)
                sb.append(",");

            String fqcn = types[index];
            int dims = dimCounts[index];

            if (importMap != null) {
                String simpleName = importMap.add(fqcn);
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
        hash = hash * 17 + Arrays.hashCode(dimCounts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MethodId))
            return false;
        MethodId o = (MethodId) obj;
        if (!methodName.equals(o.methodName))
            return false;
        if (!Arrays.equals(types, o.types))
            return false;
        if (!Arrays.equals(dimCounts, o.dimCounts))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getImportedForm(null);
    }

}
