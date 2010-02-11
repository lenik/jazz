package net.bodz.bas.bean;

public class ClassAnaylize {

    class X {
    }

    class Y
            extends X {
    }

    class Z
            extends Y {
    }

    void f(X a, Y b) {
    }

    void f(Z a, X b) {
    }

    ICacheMapStrategy<Class<?>, Object> cache;

}
