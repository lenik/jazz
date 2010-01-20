package net.bodz.bas.flow.util;

import java.io.IOException;

import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.commons.scripting.util.MethodEx;
import net.bodz.bas.flow.ReceiverEx;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.reflect.util.Members;

@SuppressWarnings("deprecation")
public class RecvUtil {

    public static MethodEx getMethodEx(Class<?> clazz, String methodName) {
        MethodEx ex = new MethodEx(Members.publicMethods(clazz, methodName), Object.class);
        if (ex.isEmpty())
            return null;
        return ex;
    }

    public static MethodEx getRecvEx(Class<?> clazz) {
        return getMethodEx(clazz, "recv");
    }

    private static final ClassLocal<MethodEx> recvExLocal;
    static {
        recvExLocal = new ClassLocal<MethodEx>();
    }

    public static void recvEx(ReceiverEx exReceiver, Object data)
            throws IOException, ReflectiveOperationException {
        Class<? extends ReceiverEx> clazz = exReceiver.getClass();
        MethodEx recvEx = recvExLocal.get(clazz);
        if (recvEx == null) {
            recvEx = getRecvEx(clazz);
            recvExLocal.put(clazz, recvEx);
        }
        // TODO - unwrap IOException
        recvEx.invoke(exReceiver, data);
    }

}
