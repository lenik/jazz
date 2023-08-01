package net.bodz.bas.t.catalog;

public interface IMutableCell
        extends
            ICell {

    void setData(Object data);

    void remove();

    boolean isDirty();

    void clearDirty();

}
