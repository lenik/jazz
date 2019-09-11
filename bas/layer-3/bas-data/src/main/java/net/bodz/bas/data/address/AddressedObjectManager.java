package net.bodz.bas.data.address;

import java.util.Map.Entry;
import java.util.TreeMap;

public class AddressedObjectManager<T extends IAddressed>
        implements IAddressedObjectManager<T> {

    private final TreeMap<Integer, T> map;

    public AddressedObjectManager() {
        this.map = new TreeMap<Integer, T>();
    }

    @Override
    public T add(T obj) {
        if (obj.size() == 0)
            throw new IllegalArgumentException("Object is zero sized.");

        int start = obj.address();

        Entry<Integer, T> floorEntry = map.floorEntry(start);
        if (floorEntry != null) {
            T floor = floorEntry.getValue();
            int floorStart = floor.address();
            int floorEnd = floorStart + floor.size();
            if (floorEnd > start)
                return floor;
        }

        int end = start + obj.size();
        Integer ceilingStart = map.ceilingKey(start);
        if (ceilingStart != null && ceilingStart < end) {
            T ceiling = map.get(ceilingStart);
            return ceiling;
        }

        map.put(start, obj);
        return null;
    }

    @Override
    public boolean remove(T obj) {
        if (obj == null)
            throw new NullPointerException("obj");

        int address = obj.address();

        Entry<Integer, T> floorEntry = map.floorEntry(address);
        if (floorEntry == null)
            return false;

        Integer floorAddress = floorEntry.getKey();
        T floor = floorEntry.getValue();
        if (floorAddress == address && floor == obj) {
            map.remove(floorAddress);
            return true;
        }

        return false;
    }

    @Override
    public T get(int address) {
        return map.get(address);
    }

    @Override
    public T find(int address) {
        Entry<Integer, T> floorEntry = map.floorEntry(address);
        if (floorEntry != null) {
            T floor = floorEntry.getValue();
            int floorStart = floor.address();
            int floorEnd = floorStart + floor.size();
            if (floorEnd > address)
                return floor;
        }
        return null;
    }

    @Override
    public T findBefore(int address) {
        // before or overlapped.
        Entry<Integer, T> floorEntry = map.floorEntry(address - 1);
        if (floorEntry == null)
            return null;

        T floor = floorEntry.getValue();
        int floorStart = floor.address();
        int floorEnd = floorStart + floor.size();
        if (floorEnd <= address) // not-overlapped.
            return floor;

        // overlapped...
        Entry<Integer, T> exactBeforeEntry = map.floorEntry(floorStart - 1);
        if (exactBeforeEntry == null)
            return null;
        else
            return exactBeforeEntry.getValue();
    }

    @Override
    public T findAfter(int address) {
        Entry<Integer, T> ceilingEntry = map.ceilingEntry(address);
        if (ceilingEntry == null)
            return null;
        else
            return ceilingEntry.getValue();
    }

}
