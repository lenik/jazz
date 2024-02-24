package net.bodz.bas.esm;

import java.util.Objects;

public final class EsmName
        extends EsmScope {

    public static final String DEFAULT_EXPORT_NAME = null;
    public static final String ALL_NAMES = "*";

    public final EsmSource source;
    public final String name;
    public final String alias;
    public final boolean type;

    public EsmName(EsmSource source, String name) {
        this(source, name, null);
    }

    public EsmName(EsmSource source, String name, String alias) {
        this(source, name, alias, false);
    }

    public EsmName(EsmSource source, String name, String alias, boolean type) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
        this.name = name;
        this.alias = alias;
        this.type = type;
    }

    @Override
    public EsmScopeType getType() {
        return EsmScopeType.NAME;
    }

    public final boolean isDefaultExport() {
        return name == null && alias != null;
    }

    public final boolean isTypeName() {
        return type;
    }

    public boolean isWildcard() {
        return name != null && name.equals(ALL_NAMES);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, name, source, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        EsmName other = (EsmName) obj;
        return Objects.equals(alias, other.alias) && Objects.equals(name, other.name)
                && Objects.equals(source, other.source) && type == other.type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null)
            sb.append("{");

        if (type)
            sb.append("type ");
        if (alias != null) {
            sb.append(alias);
            if (name != null)
                sb.append(" = ");
        }
        if (name != null)
            sb.append(name);

        if (name != null)
            sb.append("}");

        sb.append(" from ");
        sb.append(source);
        return sb.toString();
    }

}
