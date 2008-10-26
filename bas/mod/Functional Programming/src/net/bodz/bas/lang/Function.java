package net.bodz.bas.lang;

public interface Function {

    Object eval(Object... params) throws EvalException;

}
