package net.bodz.bas.fs;

import net.bodz.bas.primitive.Bits;
import net.bodz.bas.primitive.IntMath;

public class TextOrBinary {

    /**
     * @see CharFeature Part.1, is-text
     */
    static Bits textBits;
    static {
        textBits = new Bits.IntvLE( //
                134231808, -1, -1, -1, -1, -1, -1, -1);
    }

    static int textLookSize = 4096;
    static int textFailSize = (int) 0.90f * textLookSize;

    public static boolean isText(byte[] bytes) {
        int lookSize = Math.min(textLookSize, bytes.length);
        int fail = 0;
        for (int i = 0; i < lookSize; i++) {
            int b = IntMath.unsign(bytes[i]);
            if (!textBits.test(b))
                if (++fail > textFailSize)
                    return false;
        }
        return true;
    }

    public static boolean isBinary(byte[] bytes) {
        return !isText(bytes);
    }

}
