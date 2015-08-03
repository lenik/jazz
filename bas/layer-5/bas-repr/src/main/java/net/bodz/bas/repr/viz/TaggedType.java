package net.bodz.bas.repr.viz;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TaggedType
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Class<?> type;
    private final Set<String> tags;

    public TaggedType(Class<?> type, String... tags) {
        this(type, Arrays.asList(tags));
    }

    public TaggedType(Class<?> type, Collection<String> tags) {
        this.type = type;
        this.tags = new HashSet<>();
        this.tags.addAll(tags);
    }

    public Class<?> getType() {
        return type;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tags.hashCode();
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
        TaggedType other = (TaggedType) obj;
        if (!tags.equals(other.tags))
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
        sb.append(tags);
        return sb.toString();
    }

}
