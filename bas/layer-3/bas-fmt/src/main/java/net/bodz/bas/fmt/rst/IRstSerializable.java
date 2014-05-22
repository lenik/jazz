package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.io.BTreeOut;
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

        public static String dump(IRstSerializable obj, String indent) {
            BTreeOut buf = new BTreeOut();
            buf.getTextIndention().setCurrentLinePrefix(indent);
            try {
                dump(obj, buf);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return buf.toString();
        }

    }

}
