package net.bodz.bas.log;

public interface TreeConvert<S, T> {

    /** Convert C' := &lt;P', C> */
    T convert(T parent, S child);

}
