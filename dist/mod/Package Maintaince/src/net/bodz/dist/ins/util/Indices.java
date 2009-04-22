package net.bodz.dist.ins.util;

import net.bodz.bas.lang.err.OutOfDomainException;

/**
 * @test IndicesTest
 */
public class Indices {

    /**
     * @param begin
     *            inclusive
     * @param end
     *            exclusive
     */
    public static int[] inverse(int begin, int end, int[] indicesOrdered) {
        int count = end - begin;
        int[] inverse = new int[count - indicesOrdered.length];
        int nstart = begin;
        int r = 0; // inverse[r]
        for (int s = 0; s <= indicesOrdered.length; s++) {
            int sbegin;
            if (s == indicesOrdered.length)
                sbegin = end;
            else {
                sbegin = indicesOrdered[s];
                if (sbegin < begin)
                    throw new OutOfDomainException("sbegin", sbegin, begin);
                if (sbegin >= end)
                    throw new OutOfDomainException("sbegin", sbegin, end);
            }
            for (int i = nstart; i < sbegin; i++)
                inverse[r++] = i;
            nstart = sbegin + 1;
        }
        return inverse;
    }

}
