package net.bodz.bas.script.js;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graalvm.polyglot.SourceSection;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.Proxy;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;

public class ValueDumper
        extends BeanJsonDumper {

    public ValueDumper(IJsonOut out) {
        super(out);
    }

    @Override
    protected boolean dumpMembers(Class<?> type, Object obj, int depth)
            throws IOException, FormatException {
        if (type != Value.class)
            return super.dumpMembers(type, obj, depth);

        Value value = (Value) obj;

        if (value.canExecute())
            out.entry("canExecute", true);

        if (value.canInstantiate())
            out.entry("canInstantiate", true);

        if (value.isBoolean())
            out.entry("a-boolean", value.asBoolean());

        if (value.isDate())
            out.entry("a-date", value.asDate());

        if (value.isDuration())
            out.entry("a-duration", value.asDuration());

        if (value.isException()) {
            try {
                value.throwException();
            } catch (Throwable e) {
                out.entry("a-exception", e);
            }
        }

        if (value.isInstant())
            out.entry("a-instant", value.asInstant());

        if (value.isMetaObject()) {
            out.entry("metaQualifiedName", value.getMetaQualifiedName());
            out.entry("metaSimpleName", value.getMetaSimpleName());
            // out.key("metaObject");
            // Value metaObject = value.getMetaObject();
            // dumper.dumpBoxed(metaObject);
        }

        if (value.isNativePointer())
            out.entry("a-nativePointer", value.asNativePointer());

        if (value.isNull())
            out.entry("a-null", true);

        if (value.isNumber())
            out.entry("a-number", value.as(Number.class));

        if (value.isProxyObject()) {
            out.key("proxyObject");
            Proxy proxyObject = value.asProxyObject();

            String name = "~proxyObject";
            markset.enter(name);
            _dumpOnce(true, proxyObject, depth + 1, name);
            markset.leave();
        }

        if (value.isString())
            out.entry("a-string", value.asString());

        if (value.isTime())
            out.entry("a-time", value.asTime());

        if (value.isTimeZone())
            out.entry("a-timeZone", value.asTimeZone());

        List<String> fits = new ArrayList<>();
        {
            if (value.fitsInByte())
                fits.add("byte");
            if (value.fitsInShort())
                fits.add("short");
            if (value.fitsInInt())
                fits.add("int");
            if (value.fitsInLong())
                fits.add("long");
            if (value.fitsInFloat())
                fits.add("float");
            if (value.fitsInDouble())
                fits.add("double");
            if (!fits.isEmpty())
                out.entry("fits", fits.toString());
        }

        if (value.hasArrayElements()) {
            out.key("array");
            out.object();
            long size = value.getArraySize();
            out.entry("size", size);
            out.key("elements");
            out.array();
            for (long i = 0; i < size; i++) {
                String name = "[" + i + "]";
                Value element = value.getArrayElement(i);
                dumpBoxedWithName(name, element);
            }
            out.endArray();
            out.endObject();
        }

        if (value.hasMembers()) {
            out.key("members");
            out.object();
            for (String key : value.getMemberKeys()) {
                out.key(key);
                Value member = value.getMember(key);
                dumpBoxedWithName(key, member);
            }
            out.endObject();
        }

        if (value.isHostObject()) {
            Object hostObject = value.asHostObject();
            out.key("hostObject");
            dumpBoxedWithName("~hostObject", hostObject);
        }

        SourceSection sourceLocation = value.getSourceLocation();
        if (sourceLocation != null) {
            out.key("sourceLocation");
            out.object();
            out.entry("start", //
                    sourceLocation.getStartLine() + ":" + sourceLocation.getStartColumn() + "/"
                            + sourceLocation.getCharIndex());
            out.entry("end", sourceLocation.getEndLine() + ":" + sourceLocation.getEndColumn() + "/"
                    + sourceLocation.getCharEndIndex());
            out.entry("length", sourceLocation.getCharLength());
            out.entry("chars", sourceLocation.getCharacters());
            out.endObject();
        }
        return true;
    }

}
