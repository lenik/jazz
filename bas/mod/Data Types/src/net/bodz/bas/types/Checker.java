package net.bodz.bas.types;

import net.bodz.bas.lang.err.CheckException;

public interface Checker {

    void check(Object val) throws CheckException;

}
