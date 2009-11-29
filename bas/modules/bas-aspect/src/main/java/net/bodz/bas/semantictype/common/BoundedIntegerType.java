package net.bodz.bas.semantictype.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.aspect.typeinfo.ValidateException;
import net.bodz.bas.semantictype.plain.PlainType;

public class BoundedIntegerType
        extends PlainType<Integer> {

    private final int min;
    private final int max;
    private final NumberFormat format;

    private BoundedIntegerType() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE, null);
    }

    public BoundedIntegerType(int min, int max, String pattern) {
        super(Integer.class, "INTEGER");
        this.min = min;
        this.max = max;
        if (pattern == null)
            format = DecimalFormat.getIntegerInstance();
        else
            format = new DecimalFormat(pattern);
    }

    @Override
    public Object parse(String text, Object parseContext)
            throws ParseException {
        try {
            return format.parse(text);
        } catch (java.text.ParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public void validate(Object object, Object validateContext)
            throws ValidateException {
        int num = (Integer) object;
        if (num < min || num > max)
            throw new ValidateException();
    }

    static final int randomBlockSize = Integer.MAX_VALUE - 1;

    @Override
    public Integer newSampleInstanceExplicit(Object[] parameters)
            throws CreateException {
        Random random = new Random();
        int sample = min;
        int x = min;
        while (x < max) {
            boolean overflow = x + randomBlockSize < x;
            int blockSize = overflow ? max - x + 1 : randomBlockSize;
            int block = random.nextInt(blockSize);
            sample += block;
            x += blockSize;
        }
        return sample;
    }

    static final BoundedIntegerType instance = new BoundedIntegerType();

    public static BoundedIntegerType getInstance() {
        return instance;
    }

}
