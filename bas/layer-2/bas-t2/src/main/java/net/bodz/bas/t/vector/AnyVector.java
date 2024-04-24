package net.bodz.bas.t.vector;

import net.bodz.bas.t.variant.conv.BooleanVarConverter;
import net.bodz.bas.t.variant.conv.ByteVarConverter;
import net.bodz.bas.t.variant.conv.CharacterVarConverter;
import net.bodz.bas.t.variant.conv.DoubleVarConverter;
import net.bodz.bas.t.variant.conv.FloatVarConverter;
import net.bodz.bas.t.variant.conv.IntegerVarConverter;
import net.bodz.bas.t.variant.conv.LongVarConverter;
import net.bodz.bas.t.variant.conv.ShortVarConverter;

public interface AnyVector<T>
        extends
            Vector<T> {

    @Override
    default byte byteAt(int index) {
        Byte b = ByteVarConverter.INSTANCE.from(get(index));
        return b == null ? 0 : b.byteValue();
    }

    @Override
    default short shortAt(int index) {
        Short s = ShortVarConverter.INSTANCE.from(get(index));
        return s == null ? 0 : s.shortValue();
    }

    default int intAt(int index) {
        Integer i = IntegerVarConverter.INSTANCE.from(get(index));
        return i == null ? 0 : i.intValue();
    }

    @Override
    default long longAt(int index) {
        Long l = LongVarConverter.INSTANCE.from(get(index));
        return l == null ? 0 : l.longValue();
    }

    @Override
    default float floatAt(int index) {
        Float f = FloatVarConverter.INSTANCE.from(get(index));
        return f == null ? 0 : f.byteValue();
    }

    @Override
    default double doubleAt(int index) {
        Double f = DoubleVarConverter.INSTANCE.from(get(index));
        return f == null ? 0 : f.doubleValue();
    }

    @Override
    default boolean booleanAt(int index) {
        Boolean b = BooleanVarConverter.INSTANCE.from(get(index));
        return b == null ? false : b.booleanValue();
    }

    @Override
    default char charAt(int index) {
        Character chb = CharacterVarConverter.INSTANCE.from(get(index));
        return chb == null ? 0 : chb.charValue();
    }

}
