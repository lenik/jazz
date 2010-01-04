package net.bodz.bios.util;

import static net.bodz.bas.commons.scripting.util.Members.publicMethods;

import java.io.IOException;

import net.bodz.bas.arch.ClassLocal;
import net.bodz.bas.commons.scripting.util.MethodEx;
import net.bodz.bios.ReceiverEx;

@SuppressWarnings("deprecation")
public class RecvUtil {

    public static MethodEx getMethodEx(Class<?> clazz, String methodName) {
        MethodEx ex = new MethodEx(publicMethods(clazz, methodName), Object.class);
        if (ex.isEmpty())
            return null;
        return ex;
    }

    public static MethodEx getRecvEx(Class<?> clazz) {
        return getMethodEx(clazz, "recv"); //$NON-NLS-1$
    }

    private static final ClassLocal<MethodEx> recvExLocal;
    static {
        recvExLocal = new ClassLocal<MethodEx>();
    }

    public static void recvEx(ReceiverEx exReceiver, Object data) throws IOException {
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
