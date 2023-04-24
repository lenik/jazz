package net.bodz.bas.site.vhost.registry;

import net.bodz.bas.repr.form.SortOrder;

public class IPv4Registry<T> {

    NumbersRegistry<T> reg;

    public IPv4Registry(SortOrder order) {
        this.reg = new NumbersRegistry<>(order);
    }

    public boolean contains(int[] address) {
        return reg.contains(address);
    }

    public boolean add(int[] address, T value) {
        return reg.add(address, value);
    }

    public boolean containsHex(String[] hex) {
        return reg.containsHex(hex);
    }

    public T findHex(String[] hexes) {
        return reg.findHex(hexes);
    }

    public T find(int[] address) {
        return reg.find(address);
    }

    public boolean set(int[] address, T value) {
        return reg.set(address, value);
    }

    public T remove(int[] address) {
        return reg.remove(address);
    }

    public T getValue() {
        return reg.getValue();
    }

    public void setValue(T value) {
        reg.setValue(value);
    }

}
