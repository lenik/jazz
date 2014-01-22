package net.bodz.lily.model.base;

public class Permission
        extends java.security.Permission {

    private static final long serialVersionUID = 1L;

    public Permission(String name) {
        super(name);
    }

    @Override
    public String getActions() {
        return null;
    }

    @Override
    public boolean implies(java.security.Permission permission) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
