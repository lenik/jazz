package net.bodz.bas.fmt.rst;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.rst.bean.BeanElementHandler;
import net.bodz.bas.fmt.rst.reflect.ReflectElementHandler;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.l10n.en.English;

public abstract class AbstractRstDumper
        implements IRstDumper {

    protected final Set<Class<?>> stopClasses = new HashSet<>();
    {
        stopClasses.add(Object.class);
        stopClasses.add(ReflectElementHandler.class);
        stopClasses.add(BeanElementHandler.class);
    }

    @Override
    public String dump(Object obj) {
        BCharOut buf = new BCharOut();
        IRstOutput out = RstOutputImpl.from(buf);
        try {
            dump(out, obj);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

    @Override
    public void dump(IRstOutput out, Object obj)
            throws IOException {
        _dump(out, obj, obj.getClass());
    }

    protected abstract void _dump(IRstOutput out, Object obj, Class<?> clazz)
            throws IOException;

    protected void encode(IRstOutput out, String name, Class<?> type, Type gtype, Object value)
            throws IOException {
        if (value == null)
            return;

        if (value instanceof Collection<?>) {
            String name1 = English.singularOf(name);
            Class<?> type1 = TypeParam.infer1(gtype, Collection.class, 0);
            for (Object item : (Collection<?>) value)
                encode(out, name1, type1, item);
        } else {
            encode(out, name, type, value);
        }
    }

    protected void encode(IRstOutput out, String name, Class<?> type, Object value)
            throws IOException {
        if (value instanceof IRstSerializable) {
            IRstSerializable object = (IRstSerializable) value;
            String args[] = {};
            if (object instanceof IRstFormat)
                args = ((IRstFormat) object).getRstElementArguments();

            out.beginElement(name, args);
            object.writeObject(out);
            out.endElement();
            return;
        }

        TypeEnum typeEnum = TypeEnum.forClass(type);
        if (typeEnum == null) // skip unknown type.
            return;

        if (value == null)
            return; // TODO null keyword?

        switch (typeEnum) {
        case BYTE:
            out.attribute(name, (byte) value);
            break;

        case SHORT:
            out.attribute(name, (short) value);
            break;

        case INT:
            out.attribute(name, (int) value);
            break;

        case LONG:
            out.attribute(name, (long) value);
            break;

        case FLOAT:
            out.attribute(name, (float) value);
            break;

        case DOUBLE:
            out.attribute(name, (double) value);
            break;

        case BOOL:
            out.attribute(name, (boolean) value);
            break;

        case CHAR:
            out.attribute(name, (char) value);
            break;

        case STRING:
            out.attribute(name, (String) value);
            break;

        case CLASS:
            out.attribute(name, ((Class<?>) value).getCanonicalName());
            break;

        case BYTE_ARRAY:
            out.attribute(name, (byte[]) value);
            break;

        case SHORT_ARRAY:
            out.attribute(name, (short[]) value);
            break;

        case INT_ARRAY:
            out.attribute(name, (int[]) value);
            break;

        case LONG_ARRAY:
            out.attribute(name, (long[]) value);
            break;

        case FLOAT_ARRAY:
            out.attribute(name, (float[]) value);
            break;

        case DOUBLE_ARRAY:
            out.attribute(name, (double[]) value);
            break;

        case BOOL_ARRAY:
            out.attribute(name, (boolean[]) value);
            break;

        case CHAR_ARRAY:
            out.attribute(name, (char[]) value);
            break;

        case STRING_ARRAY:
            out.attribute(name, (String[]) value);
            break;

        case CLASS_ARRAY:
            out.attribute(name, TypeArray.of((Class<?>[]) value).getNames());
            break;

        case ENUM:
            out.attribute(name, ((Enum<?>) value).name());
            break;

        case OBJECT:
        default:
            out.attribute(name, value.toString());
        }
    }

}
