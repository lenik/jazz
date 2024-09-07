package net.bodz.bas.script.js;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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

        if (value.isDate()) {
            String s;
            try {
                LocalDate localDate = value.asDate();
                s = localDate.toString();
            } catch (Exception e) {
                s = "error: " + e.getMessage();
            }
            out.entry("a-date", s);
        }

        if (value.isDuration()) {
            String s;
            try {
                Duration duration = value.asDuration();
                s = duration.toString();
            } catch (Exception e) {
                s = "error: " + e.getMessage();
            }
            out.entry("a-duration", s);
        }

        if (value.isException()) {
            try {
                value.throwException();
            } catch (Throwable e) {
                out.entry("a-exception", e);
            }
        }

        if (value.isInstant()) {
            String s;
            try {
                Instant instant = value.asInstant();
                s = instant.toString();
            } catch (Exception e) {
                s = "error: " + e.getMessage();
            }
            out.entry("a-instant", s);
        }

        if (value.isMetaObject()) {
            out.entry("metaQualifiedName", value.getMetaQualifiedName());
            out.entry("metaSimpleName", value.getMetaSimpleName());
            try {
                Value metaObject = value.getMetaObject();
                out.key("metaObject");
                dump(metaObject);
            } catch (Exception e) {
                out.entry("metaObject", "error: " + e.getMessage());
            }
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
            try {
                contextPath.enter(name);
                dumpVariant(proxyObject, depth + 1);
            } finally {
                contextPath.leave();
            }
        }

        if (value.isString())
            out.entry("a-string", value.asString());

        if (value.isTime()) {
            String s;
            try {
                LocalTime time = value.asTime();
                s = time.toString();
            } catch (Exception e) {
                s = "error: " + e.getMessage();
            }
            out.entry("a-time", s);
        }

        if (value.isTimeZone()) {
            out.entry("a-timeZone", value.asTimeZone());
        }

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
            if (! fits.isEmpty())
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
                Value element = value.getArrayElement(i);
                contextPath.enter(i);
                dump(element);
                contextPath.leave();
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
                contextPath.enter(key);
                dump(member);
                contextPath.leave();
            }
            out.endObject();
        }

        if (value.isHostObject()) {
            Object hostObject = value.asHostObject();
            out.key("hostObject");
            contextPath.enter("~hostObject");
            dump(hostObject);
            contextPath.leave();
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
