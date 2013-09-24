package net.bodz.bas.text.rst;

import java.io.IOException;

import net.bodz.bas.io.ICharOut;

public interface IRstSerializable {

    void writeObject(IRstOutput out)
            throws IOException;

    IElementHandler getElementHandler();

    class fn {

        public static void dump(IRstSerializable obj, ICharOut out)
                throws IOException {
            ReflectRstDumper.getInstance().dump(RstOutputImpl.from(out), obj);
        }

    }

}
