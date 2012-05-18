package net.bodz.bas.flow.util;

import java.io.IOException;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.flow.IReceiverEx;
import net.bodz.bas.potato.traits.IMethod;
import net.bodz.bas.trait.Traits;

public class RecvUtil {

    private static final ClassLocal<IMethod> recvLocal = ClassLocals.createMap(//
            RecvUtil.class.getCanonicalName(), entryLoader);

    public static void recvEx(IReceiverEx exReceiver, Object data)
            throws IOException, ReflectiveOperationException {
        Class<? extends IReceiverEx> clazz = exReceiver.getClass();
        IMethod recv = recvLocal.get(clazz);
        if (recv == null) {
            // new MethodKey()
            recv = Traits.getTrait(clazz, IMethod.class);
            recvLocal.put(clazz, recv);
        }
        // TODO - unwrap IOException
        recv.invoke(exReceiver, data);
    }

}
