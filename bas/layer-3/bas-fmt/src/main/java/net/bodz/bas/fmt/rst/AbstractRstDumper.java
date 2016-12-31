package net.bodz.bas.fmt.rst;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.fmt.rst.obj.BeanElementHandler;
import net.bodz.bas.fmt.rst.obj.ReflectElementHandler;
import net.bodz.bas.l10n.en.English;
import net.bodz.bas.t.set.FramedMarks;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;

public abstract class AbstractRstDumper
        implements IRstDumper {

    protected final IRstOutput out;
    protected final FramedMarks marks;

    public AbstractRstDumper(IRstOutput out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
        this.marks = out.getMarks();
    }

    @Override
    public final void dump(Object obj)
            throws IOException {
        formatObject(obj.getClass(), obj);
    }

    protected abstract void formatObject(Class<?> clazz, Object obj)
            throws IOException;

    protected void formatCollectionMember(String name, Class<?> type, Type gtype, Object value)
            throws IOException {
        if (value == null)
            return;

        if (value instanceof Collection<?>) {
            String itemName = English.singularOf(name);
            Class<?> itemType = TypeParam.infer1(gtype, Collection.class, 0);
            for (Object item : (Collection<?>) value)
                formatMember(itemName, itemType, item);
        } else {
            formatMember(name, type, value);
        }
    }

    protected void formatMember(String name, Class<?> type, Object value)
            throws IOException {
        if (value instanceof IRstSerializable) {
            IRstSerializable obj = (IRstSerializable) value;
            if (!marks.add(obj))
                return;

            String args[] = {};
            if (obj instanceof IRstFormat)
                args = ((IRstFormat) obj).getRstElementArguments();

            out.beginElement(name, args);
            marks.enter();
            try {
                obj.writeObject(out);
            } finally {
                marks.leave();
                out.endElement();
            }
            return;
        }

        TypeEnum typeEnum = TypeEnum.forClass(type);
        if (typeEnum == null) // skip unknown type.
            return;

        if (value == null) {
            // out._attribute(name,"null");
            return; // TODO null keyword?
        }

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
            out.attribute(name, ((Enum<?>) value));
            break;

        case OBJECT:
        default:
            IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
            if (formatter != null) {
                String str = formatter.format(value);
                out._attribute(name, str);
            } else {
                // not supported, skipped.
            }
        }
    }

    protected static final Set<Class<?>> stopClasses = new HashSet<>();
    {
        stopClasses.add(Object.class);
        stopClasses.add(ReflectElementHandler.class);
        stopClasses.add(BeanElementHandler.class);
    }

}
