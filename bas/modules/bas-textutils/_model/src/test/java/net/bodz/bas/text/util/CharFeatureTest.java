package net.bodz.bas.text.util;

import java.util.Map.Entry;

import net.bodz.bas.types.Bits;
import net.bodz.bas.types.util.Strings;

public class CharFeatureTest {

    public static void main(String[] args) {
        for (Entry<String, byte[]> e : CharFeature.octf.entrySet()) {
            System.out.print("octf." + e.getKey() + " = "); //$NON-NLS-1$ //$NON-NLS-2$
            byte[] val = e.getValue();
            System.out.println(new String(val));
        }
        for (Entry<String, Bits> e : CharFeature.bitf.entrySet()) {
            System.out.print("bitf." + e.getKey() + " = "); //$NON-NLS-1$ //$NON-NLS-2$
            Bits val = e.getValue();
            int[] iv = val.toIntArray(false);
            System.out.println(Strings.join(",", iv)); //$NON-NLS-1$
        }
    }

}
