package net.bodz.bas.t.pojo;

public interface IPair<K, V> {

    K getFirst();

    void setFirst(K first);

    V getSecond();

    void setSecond(V second);

}
