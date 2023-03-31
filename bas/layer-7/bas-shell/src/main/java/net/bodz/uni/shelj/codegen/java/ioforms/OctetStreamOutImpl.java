package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.StringBin;
import net.bodz.bas.io.LengthType;

public class OctetStreamOutImpl
        extends FieldsRelatedSourceBuilder {

    LengthType defaultLengthType = LengthType.terminatedByNul;
    Charset defaultCharset = Charsets.UTF8;

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IDataOut in)");
        out.enterln("        throws IOException {");

        for (Field field : fields) {
            writeFormat(out, field, field.getName());
        }

        out.leaveln("}");
    }

    Object writeFormat(JavaSourceWriter out, Field field, String var) {
        Class<?> type = field.getType();
        if (type.isArray())
            return writeArrayFormat(out, field, var);

        if (IOctetStreamForm.class.isAssignableFrom(type))
            return printf(out, "%s.writeObject(out);\n", var);

        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            return printf(out, "out.write(%s);\n", var);

        case TypeId._short:
        case TypeId.SHORT:
            return printf(out, "out.writeWord(%s);\n", var);

        case TypeId._int:
        case TypeId.INTEGER:
            return printf(out, "out.writeDword(%s);\n", var);

        case TypeId._long:
        case TypeId.LONG:
            return printf(out, "out.writeQword(%s);\n", var);

        case TypeId._float:
        case TypeId.FLOAT:
            return printf(out, "out.writeFloat(%s);\n", var);

        case TypeId._double:
        case TypeId.DOUBLE:
            return printf(out, "out.writeDouble(%s);\n", var);

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return printf(out, "out.writeBool(%s);\n", var);

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        LengthType lengthType = defaultLengthType;
        // int length = -1;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = aStringBin.lenType();
            // length = aStringBin.len();
            charset = Charset.forName(aStringBin.encoding());
        }
        int unicode = 0;
        switch (charset.name().toLowerCase()) {
        case "utf8":
        case "utf-8":
            unicode = 8;
            charset = Charsets.UTF8;
            break;
        case "utf16":
        case "utf16le":
        case "utf16be":
        case "utf-16":
        case "utf-16le":
        case "utf-16be":
            unicode = 16;
            break;
        }

        String lengthTypeExpr = "LengthType." + lengthType.name();
        String charsetId = charset.name().replace('-', '_');

        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                return printf(out, "out.writeUtf8Char(%s);\n", var);
            case 16:
                return printf(out, "out.writeChar(%s);\n", var);
            }
            return printf(out, "out.writeChar(%s, Charsets." + charsetId + ");\n", var);
        }

        if (lengthType.fixedSize) {
            switch (unicode) {
            case 8:
                return printf(out, "out.writeUtf8Chars(%s);\n", var);
            case 16:
                return printf(out, "out.writeChars(%s);\n", var);
            }
            return printf(out, "out.writeChars(%s, Charsets." + charsetId + ");\n", var);
        } else {
            switch (unicode) {
            case 8:
                return printf(out, "out.writeUtf8String(" + lengthTypeExpr + ", %s);\n", var);
            case 16:
                return printf(out, "out.writeString(" + lengthTypeExpr + ", %s);\n", var);
            }
            return printf(out, "out.writeString(" + lengthTypeExpr + ", %s, Charsets." + charsetId + ");\n", var);
        }
    }

    Object writeArrayFormat(JavaSourceWriter out, Field field, String var) {
        Class<?> type = field.getType().getComponentType();
        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            return printf(out, "out.write(%s);\n", var);

        case TypeId._short:
        case TypeId.SHORT:
            return printf(out, "out.writeWords(%s);\n", var);

        case TypeId._int:
        case TypeId.INTEGER:
            return printf(out, "out.writeDwords(%s);\n", var);

        case TypeId._long:
        case TypeId.LONG:
            return printf(out, "out.writeQwords(%s);\n", var);

        case TypeId._float:
        case TypeId.FLOAT:
            return printf(out, "out.writeFloats(%s);\n", var);

        case TypeId._double:
        case TypeId.DOUBLE:
            return printf(out, "out.writeDoubles(%s);\n", var);

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return printf(out, "out.writeBools(%s);\n", var);

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        LengthType lengthType = defaultLengthType;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = aStringBin.lenType();
            charset = Charset.forName(aStringBin.encoding());
        }
        int unicode = 0;
        switch (charset.name().toLowerCase()) {
        case "utf8":
        case "utf-8":
            unicode = 8;
            charset = Charsets.UTF8;
            break;
        case "utf16":
        case "utf16le":
        case "utf16be":
        case "utf-16":
        case "utf-16le":
        case "utf-16be":
            unicode = 16;
            break;
        }

        String lengthTypeExpr = "LengthType." + lengthType.name();
        String charsetId = charset.name().replace('-', '_');

        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                return printf(out, "out.writeUtf8Chars(%s);\n", var);
            case 16:
                return printf(out, "out.writeChars(%s);\n", var);
            }
            return printf(out, "out.writeChars(%s, Charsets." + charsetId + ");\n", var);
        }

        println(out, "out.writeDword(%s.length);");
        println(out, "for (" + type + " a: " + var + ") {");
        switch (unicode) {
        case 8:
            println(out, "    out.writeUtf8String(" + lengthTypeExpr + ", a)");
            break;
        case 16:
            println(out, "    out.writeString(" + lengthTypeExpr + ", a)");
            break;
        default:
            println(out, "    out.writeString(" + lengthTypeExpr + ", a, Charsets." + charsetId + ")");
        }
        println(out, "}");
        return this;
    }

}
