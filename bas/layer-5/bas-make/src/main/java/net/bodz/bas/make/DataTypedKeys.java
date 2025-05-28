package net.bodz.bas.make;

public class DataTypedKeys {

    public static String format(IDataTypedKey<?, ?> dtKey) {
        Class<?> keyType = dtKey.getKeyType();
        String keyTypeStr = "";
        if (keyType != String.class)
            keyTypeStr = "[" + keyType.getSimpleName() + "]";

        Class<?> dataType = dtKey.getDataType();
        String dataTypeStr = dataType.getSimpleName();

        Object key = dtKey.getKey();
        return String.format("%s %s%s", dataTypeStr, keyTypeStr, key);
    }

    public static String format(IDataTypedKey<?, ?>[] dtKeys) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < dtKeys.length; i++) {
            if (i != 0)
                buf.append(", ");
            String s = format(dtKeys[i]);
            buf.append(s);
        }
        return buf.toString();
    }

}
