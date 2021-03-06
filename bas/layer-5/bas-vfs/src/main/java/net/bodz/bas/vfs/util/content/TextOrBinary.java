package net.bodz.bas.vfs.util.content;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.c.string.CharFeature;
import net.bodz.bas.t._bit.IBits;
import net.bodz.bas.t._bit.Int_leBits;

public class TextOrBinary {

    /**
     * @see CharFeature Part.1, is-text
     */
    static IBits textBits;
    static {
        textBits = new Int_leBits(0x8003700, //
                -1, -1, -1, -1, -1, -1, -1);
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
