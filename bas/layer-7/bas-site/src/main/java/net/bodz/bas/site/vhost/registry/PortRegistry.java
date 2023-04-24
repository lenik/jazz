package net.bodz.bas.site.vhost.registry;

public class PortRegistry<T>
        extends NumberRegistry<T>
        implements
            IPortRegistry<T> {

    @Override
    public boolean containsPort(int port) {
        return contains(port);
    }

    @Override
    public T findPort(int port) {
        return find(port);
    }

    @Override
    public void setPort(int port, T obj) {
        set(port, obj);
    }

    @Override
    public void setPortRange(int from, int to, T obj) {
        setFromToRange(from, to, obj);
    }

    @Override
    public boolean addPort(int port, T obj) {
        return add(port, obj);
    }

    @Override
    public boolean addPortRange(int from, int to, T obj) {
        return addFromToRange(from, to, obj);
    }

    @Override
    public T removePort(int port) {
        return remove(port);
    }

    @Override
    public T removePortRange(int from, int to) {
        return removeFromToRange(from, to);
    }

}
