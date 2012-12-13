package net.bodz.bas.t.tree.legacy;

public class LookupChain {

    private String location;
    private Object object;
    private LookupChain inner;

    public LookupChain(String location, Object object, LookupChain inner) {
        this.location = location;
        this.object = object;
        this.inner = inner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public LookupChain getInner() {
        return inner;
    }

    public void setInner(LookupChain inner) {
        this.inner = inner;
    }

    private void dumpFullLocation(StringBuilder buf) {
        if (location == null) {
            assert inner != null;
            inner.dumpFullLocation(buf);
        } else {
            buf.append(location);
            if (inner != null) {
                buf.append('/');
                inner.dumpFullLocation(buf);
            }
        }
    }

    public String join() {
        StringBuilder buf = new StringBuilder();
        dumpFullLocation(buf);
        return buf.toString();
    }

    @Override
    public String toString() {
        return join();
    }

}
