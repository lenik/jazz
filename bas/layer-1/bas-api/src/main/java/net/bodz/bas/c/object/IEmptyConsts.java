package net.bodz.bas.c.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.sugar.IConstants;

public interface IEmptyConsts
        extends IConstants {

    byte[] emptyByteArray = {};
    short[] emptyShortArray = {};
    int[] emptyIntArray = {};
    long[] emptyLongArray = {};
    float[] emptyFloatArray = {};
    double[] emptyDoubleArray = {};
    boolean[] emptyBooleanArray = {};
    char[] emptyCharArray = {};

    Object[] emptyObjectArray = {};
    Class<?>[] emptyClassArray = {};
    String[] emptyStringArray = {};
    Date[] emptyDateArray = {};
    BigInteger[] emptyBigIntegerArray = {};
    BigDecimal[] emptyBigDecimalArray = {};

}
