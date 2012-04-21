package net.bodz.swt.util;

import java.util.Comparator;

import org.eclipse.swt.graphics.RGB;

public class RGBComparator
        implements Comparator<RGB> {

    @Override
    public int compare(RGB a, RGB b) {
        if (a == b)
            return 0;
        if (a == null)
            return -1;
        if (b == null)
            return -1;
        int c = a.red - b.red;
        if (c != 0)
            return c;
        c = a.green - b.green;
        if (c != 0)
            return c;
        c = a.blue - b.blue;
        return c;
    }

}
