package net.bodz.bas.t.tree.legacy;

public interface ITreeCallback<T> {

    int OK = 0;
    int CANCEL = 1;
    int BREAK = 2;

    int each(T node, int level);

}
