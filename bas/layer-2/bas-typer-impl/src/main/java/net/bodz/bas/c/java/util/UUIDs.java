package net.bodz.bas.c.java.util;

import java.util.Random;
import java.util.UUID;

import net.bodz.bas.meta.decl.NotNull;

public class UUIDs {

    @NotNull
    public static UUID create(@NotNull byte[] data) {
        if (data.length != 16)
            throw new IllegalArgumentException("data must be 16 bytes in length");
        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++)
            msb = (msb << 8) | (data[i] & 0xff);
        for (int i = 8; i < 16; i++)
            lsb = (lsb << 8) | (data[i] & 0xff);
        long mostSigBits = msb;
        long leastSigBits = lsb;
        return new UUID(mostSigBits, leastSigBits);
    }

    @NotNull
    public static UUID random(@NotNull Random random) {
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);
        randomBytes[6] &= 0x0f;  /* clear version        */
        randomBytes[6] |= 0x40;  /* set to version 4     */
        randomBytes[8] &= 0x3f;  /* clear variant        */
        randomBytes[8] |= (byte) 0x80;  /* set to IETF variant  */
        return create(randomBytes);
    }

}
