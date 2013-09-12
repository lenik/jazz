package net.bodz.bas.data.address;

public class AddressedRef
        implements IAddressed {

    private final int address;
    private final int size;
    private final Object object;

    public AddressedRef(int address, int size, Object object) {
        this.address = address;
        this.size = size;
        this.object = object;
    }

    @Override
    public int address() {
        return address;
    }

    @Override
    public int size() {
        return size;
    }

    public Object getObject() {
        return object;
    }

}
