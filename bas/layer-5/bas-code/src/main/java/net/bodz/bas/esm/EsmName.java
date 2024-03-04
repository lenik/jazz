package net.bodz.bas.esm;

import java.util.Objects;

public final class EsmName
        extends EsmScope {

    public static final String DEFAULT_EXPORT_NAME = null;
    public static final String ALL_NAMES = "*";

    public final EsmSource source;
    public final String name;
    public final String alias;
    public final boolean typeOnly;

    public EsmName(String name) {
        this.source = null;
        this.name = name;
        this.alias = null;
        this.typeOnly = false;
    }

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
        this.typeOnly = type;
    }

    @Override
    public EsmScopeType getType() {
        return EsmScopeType.NAME;
    }

    public final boolean isDefaultExport() {
        return name == null && alias != null;
    }

    public String getActualName() {
        return getActualName(false);
    }

    public String getActualName(boolean checkBaseName) {
        if (alias != null)
            return alias;
        if (name != null)
            return name;
        if (checkBaseName && source.getBaseName() != null) {
            String baseName = source.getBaseName();
            int lastDot = baseName.lastIndexOf('.');
            String name = lastDot == -1 ? baseName : baseName.substring(0, lastDot);
            return name;
        }
        return null;
    }

    public final boolean isTypeOnly() {
        return typeOnly;
    }

    public EsmName toTypeOnly() {
        if (typeOnly == true)
            return this;
        else
            return new EsmName(source, name, alias, true);
    }

    public EsmName toValue() {
        if (typeOnly == false)
            return this;
        else
            return new EsmName(source, name, alias, false);
    }

    public boolean isWildcard() {
        return name != null && name.equals(ALL_NAMES);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, name, source, typeOnly);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        EsmName other = (EsmName) obj;
        return Objects.equals(alias, other.alias) //
                && Objects.equals(name, other.name) //
                && Objects.equals(source, other.source) //
                && typeOnly == other.typeOnly;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null)
            sb.append("{");

        if (typeOnly)
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
