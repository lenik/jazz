package net.bodz.bas.potato.traits;

import java.io.Serializable;
import java.util.Arrays;

import net.bodz.bas.valtype.util.TypeDistance;

abstract class MethodLikeKey
        implements Serializable {

    private static final long serialVersionUID = 1L;

    final String name;
    final Class<?>[] parameterTypes;
    final boolean explicit;

    public MethodLikeKey(String name, Class<?>[] parameterTypes) {
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");

        this.name = name;
        this.parameterTypes = parameterTypes;

        boolean determined = false;
        for (int i = 0; i < parameterTypes.length; i++)
            if (parameterTypes[i] == null) {
                determined = true;
                break;
            }
        this.explicit = !determined;
    }

    public String getName() {
        return name;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    boolean _isMatched(Class<?>... declTypes) {
        if (declTypes.length != parameterTypes.length)
            return false;
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == null)
                continue;
            if (!parameterType.equals(declTypes[i]))
                return false;
        }
        return true;
    }

    int _distance(Class<?>... declTypes) {
        return TypeDistance.dist(parameterTypes, declTypes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (explicit ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(parameterTypes);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodLikeKey other = (MethodLikeKey) obj;
        if (explicit != other.explicit)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(parameterTypes, other.parameterTypes))
            return false;
        return true;
    }

    String getSignature() {
        StringBuffer buf = new StringBuffer(parameterTypes.length * 40);
        if (name != null)
            buf.append(name);
        buf.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i != 0)
                buf.append(", ");
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == null)
                buf.append('?');
            else
                buf.append(parameterType.getName());
        }
        buf.append(')');
        return buf.toString();
    }

}
