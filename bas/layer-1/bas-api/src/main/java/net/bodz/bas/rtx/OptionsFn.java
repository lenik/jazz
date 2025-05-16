package net.bodz.bas.rtx;

public class OptionsFn {

    public static String dump(IOptions options) {
        StringBuilder buf = new StringBuilder(options.size() * 100);
        for (IOption option : options) {
            if (buf.length() != 0)
                buf.append('\n');
            buf.append(option);
        }
        return buf.toString();
    }

}
