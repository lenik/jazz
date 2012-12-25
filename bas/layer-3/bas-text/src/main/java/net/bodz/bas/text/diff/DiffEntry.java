package net.bodz.bas.text.diff;

public class DiffEntry {

    public final int index0;
    public final int index1;
    public final int deleted;
    public final int inserted;

    public DiffEntry(int index0, int index1, int deleted, int inserted) {
        this.index0 = index0;
        this.index1 = index1;
        this.deleted = deleted;
        this.inserted = inserted;
    }

}
