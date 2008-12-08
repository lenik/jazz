package net.bodz.bas.files;

import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;

public interface RecordBuilder<T> {

    void reset();

    T accept() throws ControlContinue, ControlBreak;

}
