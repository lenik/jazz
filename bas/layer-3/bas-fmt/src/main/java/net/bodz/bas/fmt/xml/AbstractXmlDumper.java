package net.bodz.bas.fmt.xml;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.api.IStdDumper;
import net.bodz.bas.fmt.xml.obj.BeanXmlLoader;
import net.bodz.bas.fmt.xml.obj.ReflectXmlLoader;
import net.bodz.bas.l10n.en.English;
import net.bodz.bas.t.set.FramedMarks;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;

public abstract class AbstractXmlDumper
        implements
            IStdDumper {

    protected final IXmlOutput out;
    protected final String rootTag = null;
    protected final FramedMarks marks;

    public AbstractXmlDumper(IXmlOutput out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
        this.marks = out.getMarks();
    }

    @Override
    public final void dump(Object obj)
            throws XMLStreamException, FormatException {
        out.beginElement("object");
        out.attribute("type", obj.getClass().getName());
        formatObject(obj.getClass(), obj);
        out.endElement();
    }

    protected abstract void formatObject(Class<?> clazz, Object obj)
            throws XMLStreamException, FormatException;

    protected void formatCollectionMember(String name, Class<?> type, Type gtype, Object value)
            throws XMLStreamException, FormatException {
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
            throws XMLStreamException, FormatException {
        if (value instanceof IXmlSerializable) {
            IXmlSerializable obj = (IXmlSerializable) value;
            if (!marks.add(obj))
                return;

            out.beginElement(name);
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
            out.attribute(name, (Byte) value);
            break;

        case SHORT:
            out.attribute(name, (Short) value);
            break;

        case INT:
            out.attribute(name, (Integer) value);
            break;

        case LONG:
            out.attribute(name, (Long) value);
            break;

        case FLOAT:
            out.attribute(name, (Float) value);
            break;

        case DOUBLE:
            out.attribute(name, (Double) value);
            break;

        case BOOL:
            out.attribute(name, (Boolean) value);
            break;

        case CHAR:
            out.attribute(name, (Character) value);
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
                out.attribute(name, str);
            } else {
                // not supported, skipped.
            }
        }
    }

    protected static final Set<Class<?>> stopClasses = new HashSet<Class<?>>();
    {
        stopClasses.add(Object.class);
        stopClasses.add(ReflectXmlLoader.class);
        stopClasses.add(BeanXmlLoader.class);
    }

}
