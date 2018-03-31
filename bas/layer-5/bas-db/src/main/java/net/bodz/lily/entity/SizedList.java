package net.bodz.lily.entity;

import java.util.ArrayList;
import java.util.List;

public class SizedList<T>
        extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    int size = -1;

    public SizedList() {
        this(new ArrayList<T>());
    }

    public SizedList(List<T> _orig) {
        super(_orig);
    }

    public int getSize() {
        if (isEmpty())
            return size == -1 ? 0 : size;
        else
            return size();
    }

    public void setSize(int size) {
        this.size = size;
    }

}
