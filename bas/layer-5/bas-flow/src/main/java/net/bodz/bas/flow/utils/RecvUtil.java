package net.bodz.bas.flow.utils;

import java.io.IOException;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.flow.stream.IReceiverEx;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.typer.Typers;

public class RecvUtil {

    private static final LazyTypeMap<IMethod> clsRecvMethod = TypeMapRegistry.createMap(//
            RecvUtil.class.getCanonicalName(), new RecvMethodEntryLoader());

    public static void recvEx(IReceiverEx exReceiver, Object data)
            throws IOException, ReflectiveOperationException {
        Class<? extends IReceiverEx> clazz = exReceiver.getClass();
        IMethod recvMethod = clsRecvMethod.getOrLoad(clazz);
        // TODO - unwrap IOException
        recvMethod.invoke(exReceiver, data);
    }

}

class RecvMethodEntryLoader
        implements IMapEntryLoader<Class<?>, IMethod> {

    @Override
    public IMethod loadValue(Class<?> key)
            throws LazyLoadException {
        IType type = Typers.getTyper(key, IType.class);

        IMethod recvMethod = type.getMethod("recv", Object.class);
        if (recvMethod != null)
            return recvMethod;

        throw new LazyLoadException(String.format("Class %s doesn't have a recv method.", //
                key.getName()));
    }

}