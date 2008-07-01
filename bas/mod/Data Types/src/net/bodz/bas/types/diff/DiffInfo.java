package net.bodz.bas.types.diff;

public class DiffInfo {

    public final int index0;
    public final int index1;
    public final int deleted;
    public final int inserted;

    public DiffInfo(int index0, int index1, int deleted, int inserted) {
        this.index0 = index0;
        this.index1 = index1;
        this.deleted = deleted;
        this.inserted = inserted;
    }

}
