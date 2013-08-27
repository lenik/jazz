package net.bodz.bas.flow.utils;

import java.io.IOException;

import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.flow.stream.IReceiverEx;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.util.TyperEntryLoader;

public class RecvUtil {

    private static final LazyTypeMap<IMethod> clsRecvMethod = TypeMapRegistry.createMap(//
            RecvUtil.class.getCanonicalName(), TyperEntryLoader.forClass(IMethod.class));

    public static void recvEx(IReceiverEx exReceiver, Object data)
            throws IOException, ReflectiveOperationException {
        Class<? extends IReceiverEx> clazz = exReceiver.getClass();
        IMethod recvMethod = clsRecvMethod.getOrLoad(clazz);
        // TODO - unwrap IOException
        recvMethod.invoke(exReceiver, data);
    }

}
