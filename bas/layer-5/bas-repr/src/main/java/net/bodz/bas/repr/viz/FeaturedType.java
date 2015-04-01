package net.bodz.bas.repr.viz;

import java.io.Serializable;
import java.util.Arrays;

public class FeaturedType
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Class<?> type;
    private final String[] features;

    public FeaturedType(Class<?> type, String... features) {
        this.type = type;
        this.features = features;
    }

    public Class<?> getType() {
        return type;
    }

    public String[] getFeatures() {
        return features;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(features);
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        FeaturedType other = (FeaturedType) obj;
        if (!Arrays.equals(features, other.features))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(Arrays.asList(features));
        return sb.toString();
    }

}
