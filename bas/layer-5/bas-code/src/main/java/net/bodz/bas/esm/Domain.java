package net.bodz.bas.esm;

import java.util.Objects;

import net.bodz.bas.t.tuple.Split;

public class Domain {

    public final DomainType type;
    public final String key;

    public Domain(DomainType type, String key) {
        this.type = type;
        this.key = key;
    }

    static final Domain ALL = new Domain(DomainType.ALL, "");

    public static Domain all() {
        return ALL;
    }

    public static Domain ofPackage(String packageName) {
        return new Domain(DomainType.PACKAGE, packageName);
    }

    public static Domain startsWith(String prefix) {
        return new Domain(DomainType.PREFIX, prefix);
    }

    public static Domain matches(String any) {
        return new Domain(DomainType.MATCH, any);
    }

    public static Domain parse(String spec) {
        if (spec == null)
            return null;

        if (spec.endsWith("*")) {
            String prefix = spec.substring(0, spec.length() - 1);
            return startsWith(prefix);
        }

        if (spec.endsWith(":")) {
            String packageName = spec.substring(0, spec.length() - 1);
            if (packageName.endsWith(":"))
                packageName = packageName.substring(0, packageName.length() - 1);
            return ofPackage(packageName);
        }

        if (spec.startsWith("=")) {
            String packageName = spec.substring(1);
            if (packageName.startsWith("="))
                packageName = packageName.substring(1);
            return matches(packageName);
        }

        if (spec.isEmpty())
            return all();

        Split packageName = Split.packageName(spec);
        String simpleName = packageName.b;

        assert simpleName != null;
        boolean nameStartsWithLower = Character.isLowerCase(simpleName.charAt(0));
        if (nameStartsWithLower)
            return ofPackage(spec);
        else
            return matches(spec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, key);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Domain other = (Domain) obj;
        return type == other.type && Objects.equals(key, other.key);
    }

    @Override
    public String toString() {
        switch (type) {
        case ALL:
            return "*";
        case PACKAGE:
            return key + "::";
        case PREFIX:
            return key + "*";
        case MATCH:
            return "=" + key;
        default:
            return type + " " + key;
        }
    }

}
