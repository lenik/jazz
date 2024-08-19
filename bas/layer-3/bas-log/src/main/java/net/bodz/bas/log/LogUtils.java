package net.bodz.bas.log;

import net.bodz.bas.log.message.ArrayJoinMessage;
import net.bodz.bas.log.message.StringFormatMessage;

public class LogUtils {

    public static String nameOf(Class<?> clazz) {
        return clazz.getName();
    }

    public static Object concat(Object... array) {
        return new ArrayJoinMessage(array);
    }

    public static Object format(String fmt, Object... args) {
        return new StringFormatMessage(fmt, args);
    }

}
