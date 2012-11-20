package net.bodz.bas.db.stat;

import net.bodz.bas.err.NotImplementedException;

public enum SubCounterMode {

    init,

    setUp,

    sumUp,

    averageUp,

    ;

    <T> ICounter<T> create(ICounter<T> _parent, String name, T initValue) {
        if (_parent == null)
            throw new NullPointerException("parent");

        ICounter<T> parent = _parent;

        switch (this) {
        case init:
            return new Counter<T>(_parent.getDefinition(), name, initValue);

        case setUp:
            return new SetUpCounter<T>(parent, initValue);

        case sumUp:
            return new SumUpCounter<T>(parent, initValue);

        case averageUp:
        default:
            throw new NotImplementedException();
        }
    }

}
