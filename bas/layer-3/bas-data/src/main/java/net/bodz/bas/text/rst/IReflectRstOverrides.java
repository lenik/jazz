package net.bodz.bas.text.rst;

import java.io.IOException;
import java.lang.reflect.Field;

public interface IReflectRstOverrides {

    String[] getElementArguments();

    boolean writeObjectFieldOverride(IRstOutput out, Field field)
            throws IOException;

}
