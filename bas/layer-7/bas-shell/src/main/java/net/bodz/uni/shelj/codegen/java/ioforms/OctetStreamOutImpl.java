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
        out.println("public void writeObject(IDataOut out)");
        out.enterln("        throws IOException {");

        int i = 0;
        for (Field field : fields) {
            String name = "this." + field.getName();
            saveField(out, new VarField(field, name), i++);
        }

        out.leaveln("}");
    }

    void saveField(JavaSourceWriter out, VarField field, int index) {
        if (field.type.isArray()) {
            saveArrayField(out, field, index);
            return;
        }

        if (IOctetStreamForm.class.isAssignableFrom(field.type)) {
            out.printf("%s.writeObject(out);\n", field.name);
            return;
        }

        int typeId = TypeKind.getTypeId(field.type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("out.write(%s);\n", field.name);
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("out.writeWord(%s);\n", field.name);
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("out.writeDword(%s);\n", field.name);
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("out.writeQword(%s);\n", field.name);
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("out.writeFloat(%s);\n", field.name);
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("out.writeDouble(%s);\n", field.name);
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("out.writeBool(%s);\n", field.name);
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        saveStringField(out, field, index);
    }

    void saveArrayField(JavaSourceWriter out, VarField field, int index) {
        Class<?> type1 = field.type.getComponentType();
        int typeId = TypeKind.getTypeId(type1);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("out.write(%s);\n", field.name);
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("out.writeWords(%s);\n", field.name);
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("out.writeDwords(%s);\n", field.name);
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("out.writeQwords(%s);\n", field.name);
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("out.writeFloats(%s);\n", field.name);
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("out.writeDoubles(%s);\n", field.name);
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("out.writeBools(%s);\n", field.name);
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
                out.printf("out.writeUtf8Chars(%s);\n", field.name);
                return;
            case 16:
                out.printf("out.writeChars(%s);\n", field.name);
                return;
            }
            String charsetId = charset.name().replace('-', '_');
            out.printf("out.writeChars(%s, Charsets." + charsetId + ");\n", field.name);
            return;
        }

        out.printf("out.writeDword(%s.length);");
        out.printf("for (" + type1 + " a: " + field.name + ") {");
        out.enter();
        VarField itemField = new VarField(type1, 0, field.name + "[i]");
        saveStringField(out, itemField, 0);
        out.leave();
        out.println("}");
    }

    void saveStringField(JavaSourceWriter out, VarField field, int index) {
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
        String charsetParam = "";
        if (charsetId != null)
            charsetParam = ", Charsets." + charsetId;

        int typeId = TypeKind.getTypeId(field.type);
        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8Char(%s);\n", field.name);
                return;
            case 16:
                out.printf("out.writeChar(%s);\n", field.name);
                return;
            }
            out.printf("out.writeChar(%s%s);\n", field.name, charsetParam);
            return;
        }

        String lengthTypeConst = StringLengthType.class.getSimpleName() + "." + lengthType.name;
        if (lengthType.hasCountField) {
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8String(%s, %s);\n", lengthTypeConst, field.name);
                break;
            case 16:
                out.printf("out.writeString(%s, %s);\n", lengthTypeConst, field.name);
                break;
            default:
                out.printf("out.writeString(%s, %s%s);\n", lengthTypeConst, field.name, charsetParam);
            }
            return;
        } else if (lengthType.hasTerminator) {
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8String(%s);\n", field.name);
                break;
            case 16:
                out.printf("out.writeString(%s);\n", field.name);
                break;
            default:
                out.printf("out.writeString(%s%s);\n", field.name, charsetParam);
            }
            out.printf("out.writeChar(%s);\n", lengthType.terminatorJavaLiteral);
            return;
        } else {
            int providedCount;
            String nexpr;
            if (aStringBin != null) {
                providedCount = StringBinUtils.getProvidedCount(aStringBin);
                nexpr = "" + providedCount;
            } else {
                providedCount = 0; // from source...
                nexpr = "" + providedCount;
            }

            if (lengthType.countByChar) {
                switch (unicode) {
                case 8:
                    out.printf("out.writeUtf8StringOfLength(%s, %s);\n", nexpr, field.name);
                    break;
                case 16:
                    out.printf("out.writeStringOfLength(%s, %s);\n", nexpr, field.name);
                    break;
                default:
                    out.printf("out.writeStringOfLength(%s, %s%s);\n", nexpr, field.name, charsetParam);
                }
                return;
            } else {
                switch (unicode) {
                case 8:
                    out.printf("out.writeUtf8StringOfSize(%s, %s);\n", nexpr, field.name);
                    break;
                case 16:
                    out.printf("out.writeStringOfSize(%s, %s);\n", nexpr, field.name);
                    break;
                default:
                    out.printf("out.writeStringOfSize(%s, %s%s);\n", nexpr, field.name, charsetParam);
                }
                return;
            }
        }
    }

}
