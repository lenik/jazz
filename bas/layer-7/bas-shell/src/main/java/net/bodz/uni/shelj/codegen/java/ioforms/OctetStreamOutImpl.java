package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.StringBin;
import net.bodz.bas.data.struct.StringBinUtils;
import net.bodz.bas.io.StringLengthType;

public class OctetStreamOutImpl
        extends FieldsRelatedSourceBuilder {

    StringLengthType defaultLengthType = StringLengthType.terminatedByNull;
    Charset defaultCharset = Charsets.UTF_8;

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IDataOut in)");
        out.enterln("        throws IOException {");

        int i = 0;
        for (Field field : fields) {
            saveField(out, field, i++);
        }

        out.leaveln("}");
    }

    void saveField(JavaSourceWriter out, Field field, int index) {
        Class<?> type = field.getType();
        String fieldName = field.getName();
        if (type.isArray()) {
            saveArrayField(out, field, index);
            return;
        }

        if (IOctetStreamForm.class.isAssignableFrom(type)) {
            out.printf("%s.writeObject(out);\n", fieldName);
            return;
        }

        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("out.write(%s);\n", fieldName);
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("out.writeWord(%s);\n", fieldName);
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("out.writeDword(%s);\n", fieldName);
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("out.writeQword(%s);\n", fieldName);
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("out.writeFloat(%s);\n", fieldName);
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("out.writeDouble(%s);\n", fieldName);
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("out.writeBool(%s);\n", fieldName);
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        // saveStringField
    }

    void saveArrayField(JavaSourceWriter out, Field field, int index) {
        Class<?> type1 = field.getType().getComponentType();
        String fieldName = field.getName();
        int typeId = TypeKind.getTypeId(type1);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("out.write(%s);\n", fieldName);
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("out.writeWords(%s);\n", fieldName);
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("out.writeDwords(%s);\n", fieldName);
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("out.writeQwords(%s);\n", fieldName);
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("out.writeFloats(%s);\n", fieldName);
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("out.writeDoubles(%s);\n", fieldName);
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("out.writeBools(%s);\n", fieldName);
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        StringLengthType lengthType = defaultLengthType;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = StringBinUtils.toStringLengthType(aStringBin);
            if (!aStringBin.encoding().isEmpty())
                charset = Charset.forName(aStringBin.encoding());
        }
        int unicode = 0;
        switch (charset.name()) {
        case "UTF-8":
            unicode = 8;
            charset = Charsets.UTF_8;
            break;
        case "UTF-16":
            unicode = 16;
            break;
        }

        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8Chars(%s);\n", fieldName);
                return;
            case 16:
                out.printf("out.writeChars(%s);\n", fieldName);
                return;
            }
            String charsetId = charset.name().replace('-', '_');
            out.printf("out.writeChars(%s, Charsets." + charsetId + ");\n", fieldName);
            return;
        }

        out.printf("out.writeDword(%s.length);");
        out.printf("for (" + type1 + " a: " + fieldName + ") {");
        out.enter();
        saveStringField(out, field, type1, fieldName + "[i]", 0);
        out.leave();
        out.println("}");
    }

    void saveStringField(JavaSourceWriter out, AnnotatedElement field, Class<?> type, String var, int index) {
        StringLengthType lengthType = StringBinUtils.defaultStringLengthType;

        Charset charset = null;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = StringBinUtils.toStringLengthType(aStringBin);
            String encoding = aStringBin.encoding();
            if (!encoding.isEmpty())
                charset = Charset.forName(encoding);
        }

        int unicode = 0;
        if (charset != null) {
            String name = charset.name();
            if (name.equals("UTF-8"))
                unicode = 8;
            else if (name.startsWith("UTF-16"))
                unicode = 16;
            else if (name.startsWith("UTF-32"))
                unicode = 32;
        }

        String charsetId = charset == null ? null : charset.name().replace('-', '_');
        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8Char(%s);\n", var);
                return;
            case 16:
                out.printf("out.writeChar(%s);\n", var);
                return;
            }
            out.printf("out.writeChar(%s, Charsets." + charsetId + ");\n", var);
            return;
        }

        String lengthTypeConst = StringLengthType.class.getSimpleName() + "." + lengthType.name;
        if (lengthType.hasCountField) {
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8String(" + lengthTypeConst + ", %s);\n", var);
                return;
            case 16:
                out.printf("out.writeString(" + lengthTypeConst + ", %s);\n", var);
                return;
            }
            out.printf("out.writeString(" + lengthTypeConst + ", %s, Charsets." + charsetId + ");\n", var);
            return;
        } else {
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8Chars(%s);\n", var);
                return;
            case 16:
                out.printf("out.writeChars(%s);\n", var);
                return;
            }
            out.printf("out.writeChars(%s, Charsets." + charsetId + ");\n", var);
            return;
        }
    }

}
