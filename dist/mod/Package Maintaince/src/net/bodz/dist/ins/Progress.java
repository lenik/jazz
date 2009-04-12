package net.bodz.dist.ins;

public interface Progress {

    float get();

    void set(float p);

    int getSize();

    void setSize(int size);

    int getIndex();

    void setIndex(int index);

    void incr();

    void addProgressChangeListener(ProgressChangeListener listener);

    void removeProgressChangeListener(ProgressChangeListener listener);

}
