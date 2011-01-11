package net.bodz.bas.flow.util;

import java.io.IOException;
import java.lang.reflect.Method;

import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.flow.IReceiverEx;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.reflect.query.MethodSelection;
import net.bodz.bas.reflect.query.ReflectQuery;

public class RecvUtil {

    public static Method getRecvEx(Class<?> clazz) {
        MethodSelection recvMethods = ReflectQuery.selectMethods(clazz).withName("recv");

    }

    private static final ClassLocal<MethodEx> recvExLocal;
    static {
        recvExLocal = new ClassLocal<MethodEx>();
    }

    public static void recvEx(IReceiverEx exReceiver, Object data)
            throws IOException, ReflectiveOperationException {
        Class<? extends IReceiverEx> clazz = exReceiver.getClass();
        MethodEx recvEx = recvExLocal.get(clazz);
        if (recvEx == null) {
            recvEx = getRecvEx(clazz);
            recvExLocal.put(clazz, recvEx);
        }
        // TODO - unwrap IOException
        recvEx.invoke(exReceiver, data);
    }

}
