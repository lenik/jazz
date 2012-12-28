package net.bodz.bas.flow.utils;

import java.io.IOException;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.flow.stream.IReceiverEx;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.util.TraitEntryLoader;

public class RecvUtil {

    private static final ClassLocal<IMethod> recvLocal = ClassLocals.createMap(//
            RecvUtil.class.getCanonicalName(), TraitEntryLoader.forClass(IMethod.class));

    public static void recvEx(IReceiverEx exReceiver, Object data)
            throws IOException, ReflectiveOperationException {
        Class<? extends IReceiverEx> clazz = exReceiver.getClass();
        IMethod recv = recvLocal.load(clazz);
        // TODO - unwrap IOException
        recv.invoke(exReceiver, data);
    }

}
