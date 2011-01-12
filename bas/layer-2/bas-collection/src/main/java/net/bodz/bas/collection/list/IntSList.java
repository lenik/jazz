package net.bodz.bas.collection.list;

public class IntSList {

    private int start;
    private int[] next;
    private int size;

    public IntSList(int size) {
        next = new int[size];
        for (int i = 0; i < size; i++)
            next[i] = i + 1;
        next[size - 1] = -1;
        this.size = size;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int absoluteOf(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + "/" + size); 
        int absoluteIndex = start;
        while (--index >= 0)
            absoluteIndex = next[absoluteIndex];
        return absoluteIndex;
    }

    public int indexOf(int absoluteIndex) {
        int abs = start;
        int index = 0;
        while (abs != absoluteIndex) {
            if (index >= size)
                return -1;
            abs = next[abs];
            index++;
        }
        return index;
    }

    public void removeAbsolute(int absoluteIndex) {
        int absolutePrev = absoluteIndex;
        while (--absolutePrev >= 0)
            if (next[absolutePrev] == absoluteIndex)
                break;
        if (absolutePrev != -1)
            next[absolutePrev] = next[absoluteIndex];
        else
            start = next[absoluteIndex];
        next[absoluteIndex] = -1; // invalidate
        size--;
    }

    public int remove(int index) {
        int absoluteIndex = absoluteOf(index);
        removeAbsolute(absoluteIndex);
        return absoluteIndex;
    }

    @Override
    public String toString() {
        if (size == 0)
            return ""; 
        StringBuffer buf = null;
        int absoluteIndex = start;
        for (int i = 0; i < size; i++) {
            if (buf == null)
                buf = new StringBuffer();
            else
                buf.append("-"); 
            buf.append(absoluteIndex);
            absoluteIndex = next[absoluteIndex];
        }
        return buf.toString();
    }

}
