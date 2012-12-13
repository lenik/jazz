package net.bodz.bas.repr.position;

public class InParent
        extends AbstractObjectOccurrence {

    private Object parent;
    private String key;

    public InParent(Object parent, String key) {
        if (parent == null)
            throw new NullPointerException("parent");
        if (key == null)
            throw new NullPointerException("key");
        this.parent = parent;
        this.key = key;
    }

    @Override
    public Object getContext() {
        return parent;
    }

    @Override
    public String getPath() {
        return key;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
