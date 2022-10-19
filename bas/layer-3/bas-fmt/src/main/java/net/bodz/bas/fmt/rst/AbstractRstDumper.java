package net.bodz.bas.fmt.rst;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.api.IStdDumper;
import net.bodz.bas.fmt.rst.obj.BeanRstHandler;
import net.bodz.bas.fmt.rst.obj.ReflectRstHandler;
import net.bodz.bas.l10n.en.English;
import net.bodz.bas.t.set.FramedMarks;
import net.bodz.bas.t.variant.fn.QuotedStringFn;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;

public abstract class AbstractRstDumper
        implements
            IStdDumper {

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
            throws IOException, FormatException {
        formatObject(obj.getClass(), obj);
    }

    protected abstract void formatObject(Class<?> clazz, Object obj)
            throws IOException, FormatException;

    protected void formatCollectionMember(String name, Class<?> type, Type gtype, Object value)
            throws IOException, FormatException {
        if (value == null)
            return;

        if (value instanceof Collection<?>) {
            String itemName = English.singularOf(name);
            Class<?> itemType = TypeParam.infer1(gtype, Collection.class, 0);
            for (Object item : (Collection<?>) value)
                formatMember(itemName, itemType, item);

        } else if (value instanceof Map<?, ?>) {
            out.beginElement(name);
            for (Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                String key = entry.getKey().toString();
                Object val = entry.getValue();
                if (val == null) {
                    // out._attribute(key, "null");
                    continue;
                }

                Class<?> klass = val.getClass();
                formatCollectionMember(key, klass, klass, val);
            }
            out.endElement();

        } else {
            formatMember(name, type, value);
        }
    }

    protected void formatMember(String name, Class<?> type, Object value)
            throws IOException, FormatException {
        if (value instanceof IRstForm) {
            IRstForm obj = (IRstForm) value;
            if (!marks.addMark(obj))
                return;

            String args[] = {};
            if (obj instanceof IRstOverrides)
                args = ((IRstOverrides) obj).getRstElementArguments();

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
            out.attribute(name, null);
            return;
        }

        switch (typeEnum) {
        case BYTE:
        case SHORT:
        case INT:
        case LONG:
        case FLOAT:
        case DOUBLE:
        case BOOL:
        case ENUM:
            out.attribute(name, value);
            break;

        case CLASS:
            out.attribute(name, QuotedStringFn.quote(((Class<?>) value).getCanonicalName()));
            break;

        case STRING_ARRAY:
            out.attribute(name, QuotedStringFn.quote((String[]) value));
            break;

        case CLASS_ARRAY:
            String[] classNames = TypeArray.of((Class<?>[]) value).getNames();
            out.attribute(name, QuotedStringFn.quote(classNames));
            break;

        case CHAR:
        case STRING:
            out.attribute(name, QuotedStringFn.quote(value.toString()));
            break;

        case OBJECT:
        default:
            IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
            String str;
            if (formatter != null) {
                str = formatter.format(value);
            } else {
                str = value.toString();
            }
            out.attribute(name, QuotedStringFn.quote(str));
        }
    }

    protected static final Set<Class<?>> stopClasses = new HashSet<Class<?>>();
    {
        stopClasses.add(Object.class);
        stopClasses.add(ReflectRstHandler.class);
        stopClasses.add(BeanRstHandler.class);
    }

}
